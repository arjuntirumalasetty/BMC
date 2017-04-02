package com.example.bmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.bmc.globalvariable.GlobalClass;
import com.rey.material.widget.Button;
import com.rey.material.widget.TextView;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import businessojects.CoachDetails;
import businessojects.StadiumDetails;
import butterknife.Bind;
import butterknife.ButterKnife;
import cache.UICacheImpl;
import cache.UiCache;
import handlers.ProfileHandler;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private UiCache uiCache = UICacheImpl.getInstance(this);
    private ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    private Button addButton;
    @Bind(R.id.fab_plus)
    FloatingActionButton fab_plus;
    android.widget.Button submitStadiumDetails;
    Animation fabOpen, fabClose, fabClockWise, fabAntiClockWise;
    private boolean isFabOpen = false;
    @Bind(R.id.coach_name)
    EditText coachName;
    @Bind(R.id.age)
    EditText age;
    @Bind(R.id.experience_years)
    EditText experienceInYears;
    @Bind(R.id.experience_months)
    EditText experienceInMonths;
    @Bind(R.id.coach_phone_no)
    EditText coachPhoneNo;
    @Bind(R.id.coach_email)
    EditText coachEmail;
    private CoachDetails coachDetails;
    GlobalClass globalVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<StadiumDetails> stadiumDetailsList = new ArrayList<StadiumDetails>();
        try {
            super.onCreate(savedInstanceState);
            globalVariable = (GlobalClass) getApplicationContext();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            setContentView(R.layout.activity_profile);
            ProfileHandler.setStadiumList(stadiumDetailsList);
            ButterKnife.bind(this);
            CoachDetails existingCoachDetails = ProfileHandler.findCoachDetails(uiCache);
            if(existingCoachDetails!=null){
                addContentToView(existingCoachDetails);
            }
            //    ProfileHandler.fillData();
            if(null != ProfileHandler.getStadiumNames()&& null != ProfileHandler.getStadiumDetailsMap()){
                expandableListView = (ExpandableListView) findViewById(R.id.stadium_list);
                listAdapter = new adapters.ExpandableListAdapter(this, ProfileHandler.getStadiumNames(), ProfileHandler.getStadiumDetailsMap());
                expandableListView.setAdapter(listAdapter);
            }
            initialiseFabFloatingFunction();
            enableProfileEditText(false);
            fab_plus.setOnClickListener(this);
            Button editButton = (Button) findViewById(R.id.profile_edit);
            Button profileSaveButton = (Button) findViewById(R.id.coach_profile_save);
            editButton.setOnClickListener(this);
            profileSaveButton.setOnClickListener(this);
            coachName.setOnFocusChangeListener(this);
            age.setOnFocusChangeListener(this);
            experienceInYears.setOnFocusChangeListener(this);
            experienceInMonths.setOnFocusChangeListener(this);
            coachPhoneNo.setOnFocusChangeListener(this);
            coachEmail.setOnFocusChangeListener(this);
            profileSaveButton.setOnClickListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void addContentToView(CoachDetails coachDetails) {
        try{

            if(StringUtils.hasText(coachDetails.getCoachName())){
                coachName.setText(coachDetails.getCoachName());
            }
            if(StringUtils.hasText(String.valueOf(coachDetails.getAge()))){
                age.setText(coachDetails.getAge()+"");
            }
            if(StringUtils.hasText(String.valueOf(coachDetails.getExperienceInYears()))){
                experienceInYears.setText(coachDetails.getExperienceInYears()+"");
            }
            if(StringUtils.hasText(String.valueOf(coachDetails.getExperienceInMonths()))){
                experienceInMonths.setText(coachDetails.getExperienceInMonths()+"");
            }
            if(StringUtils.hasText(String.valueOf(coachDetails.getCoachPhoneNo()))){
                coachPhoneNo.setText(coachDetails.getCoachPhoneNo()+"");
            }
            if(StringUtils.hasText(coachDetails.getCoachEmail())){
                coachEmail.setText(coachDetails.getCoachEmail());
            }
        }catch (Exception e){
            Log.i("Exe in addContentView",e.getMessage());
        }
    }
    private void enableProfileEditText(boolean enable) {
        coachName.setEnabled(enable);
        age.setEnabled(enable);
        experienceInYears.setEnabled(enable);
        experienceInMonths.setEnabled(enable);
        coachPhoneNo.setEnabled(enable);
        coachEmail.setEnabled(enable);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_plus:
                Log.i("Open Add", "Button was clicked");
                openAddStadiumActivity();
                break;
            case R.id.profile_edit:
                enableProfileEditText(true);
                break;
            case R.id.coach_profile_save:
                createProfileDetails();
                try {
                    ProfileHandler.persistProfileDetails(coachDetails, globalVariable,uiCache);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void createProfileDetails() {
        CoachDetails coachDetails = new CoachDetails();
        coachDetails.setCoachName(coachName.getText().toString());
        coachDetails.setAge(Integer.parseInt(age.getText().toString()));
        coachDetails.setExperienceInYears(Integer.parseInt(experienceInYears.getText().toString()));
        coachDetails.setExperienceInMonths(Integer.parseInt(experienceInMonths.getText().toString()));
        coachDetails.setCoachPhoneNo(coachPhoneNo.getText().toString());
        coachDetails.setCoachEmail(coachEmail.getText().toString());
         this.coachDetails = coachDetails;
    }

    private void updateStadiumDetails() {
        try {
            TextView stadiumName = (TextView) findViewById(R.id.stadiumName);
            TextView noOfCourts = (TextView) findViewById(R.id.noOfCourts);
            TextView sportName = (TextView) findViewById(R.id.sportName);
            StadiumDetails sd = new StadiumDetails();
            sd.setStadiumName(stadiumName.getText().toString());
            sd.setSportName(sportName.getText().toString());
            sd.setNumberOfCourts(noOfCourts.getText().toString());
        } catch (Exception e) {
            Log.e("Exception update Sadium",e.getMessage());
        }
    }

    private void initialiseFabFloatingFunction() {
        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabClockWise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock_wise);
        fabAntiClockWise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock_wise);
    }

    private void openAddStadiumActivity() {
        Intent stadiumDetailsIntent = new Intent(this, AddStadiumActivity.class);
        startActivity(stadiumDetailsIntent);
        overridePendingTransition(R.animator.activity_open_scale, R.animator.activity_close_translate);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        try {

            switch (v.getId()) {
                case R.id.coach_name:
                    if (hasFocus) {
                        if (coachName.getText().length() <= 0) {
                            coachName.setError("Coach Name should not blank");
                        }
                    }
                    break;
                case R.id.coach_phone_no:
                    if (hasFocus) {
                        if (!StringUtils.hasText(coachPhoneNo.getText().toString())) {
                            coachPhoneNo.setError("Phone no should not blank");
                        }
                    }
                    break;
                case R.id.experience_months:
                    if (hasFocus) {
                        if (!StringUtils.hasText(coachPhoneNo.getText().toString())) {
                            coachPhoneNo.setError("No of months should not blank");
                        }
                    }
                case R.id.experience_years:
                    if (hasFocus) {
                        if (!StringUtils.hasText(experienceInYears.getText().toString())) {
                            experienceInYears.setError("No of years should not blank");
                        }
                    }
                    break;
                case R.id.coach_email:
                    if (hasFocus) {
                        if (!StringUtils.hasText(coachEmail.getText().toString())) {
                            coachEmail.setError("Email should not blank");
                        }
                        if (StringUtils.hasText(coachEmail.getText().toString()) && !coachEmail.getText().toString().contains("@") || !(coachEmail.getText().toString().contains(".com") || coachEmail.getText().toString().contains(".COM"))) {
                            coachEmail.setError("Email is incorrect");
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e("profile validator", e.getMessage());
        }
    }
}

