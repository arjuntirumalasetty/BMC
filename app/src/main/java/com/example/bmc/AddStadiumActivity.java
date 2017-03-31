package com.example.bmc;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import adapters.PagerAdapter;
import businessojects.StadiumDetails;
import businessojects.Timings;
import butterknife.Bind;
import butterknife.ButterKnife;
import handlers.StadiumHandler;

public class AddStadiumActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, View.OnClickListener,View.OnFocusChangeListener {
    ViewPager viewPager;
    int hour,min,hourFinal,minFinal;
	//Edit text initialization
	@Bind(R.id.stadiumName)
    EditText stadiumName;
	@Bind(R.id.sportName)
    EditText sportName;
	@Bind(R.id.noOfCourts)
    EditText noOfCourts;
	@Bind(R.id.individualtimmingsfrom)
    EditText individualTimmingsFrom;
    @Bind(R.id.individualtimmingsto)
    EditText individualTimmingsTo;
	@Bind(R.id.grouptimmingsfrom)
    EditText groupTimmingsFrom;
    @Bind(R.id.grouptimmingsto)
    EditText groupTimmingsTo;
	EditText activateTextView;
	// TextIputLayout initialization
    Button submitStadium;
    @Bind(R.id.stadium_name)
    TextInputLayout istadiumName;
    @Bind(R.id.sport_name)
    TextInputLayout iSportName;
    @Bind(R.id.no_of_courts)
    TextInputLayout inoofsports;
    @Bind(R.id.individual_timmings_from)
    TextInputLayout iIndividualTimingsFrom;
    @Bind(R.id.individual_timmings_to)
    TextInputLayout iIndividualTimingsTo;
    @Bind(R.id.group_timmings_from)
    TextInputLayout iGroupTimingFrom;
    @Bind(R.id.group_timmings_to)
    TextInputLayout iGroupTimingsTo;
    @Bind(R.id.btn_tap_to_pin)
    Button pinMyLocation_Btn;
    AppCompatButton baddStadium;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stadium);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        stadiumName.setOnFocusChangeListener(this);
        sportName.setOnFocusChangeListener(this);
        noOfCourts.setOnFocusChangeListener(this);
        pinMyLocation_Btn.setOnClickListener(this);
        istadiumName.setOnFocusChangeListener(this);
        baddStadium = (AppCompatButton) findViewById(R.id.add_stadium_btn);
        baddStadium.setOnClickListener(this);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
      hourFinal = hourOfDay;
        minFinal = minute;
        activateTextView.setText(hourOfDay+":"+min);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_tap_to_pin){
            openMaps();
        }else if(v.getId() == R.id.add_stadium_btn){
            if(finalValidate()){
                StadiumDetails newStadiumDetails = new StadiumDetails();
                newStadiumDetails.setStadiumName(stadiumName.getText().toString());
                newStadiumDetails.setSportName(sportName.getText().toString());
                newStadiumDetails.setIndividualTimings(new Timings(individualTimmingsFrom.getText().toString(),individualTimmingsTo.getText().toString()));
                newStadiumDetails.setGroupTimings(new Timings(groupTimmingsFrom.getText().toString(),groupTimmingsTo.getText().toString()));
                newStadiumDetails.setNumberOfCourts(noOfCourts.getText().toString());
                StadiumHandler.submitStadiumDetails(newStadiumDetails);
            }
        }else{
            activateTextView = (EditText) findViewById(v.getId());
            Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR);
            min = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog =  new TimePickerDialog(this,this,hour,min, DateFormat.is24HourFormat(this));
            timePickerDialog.show();
        }
    }

    private boolean finalValidate() {
        if(null != stadiumn.getEditText().getText().toString() && !"".equals(stadiumn.getEditText().getText().toString()))
        {
            stadiumName.setError("stadium name should not blank");
            return false;
        }else{
            Log.i("Stadium Name ",stadiumn.getEditText().getText().toString());
        }
            if(null != stadiumName.getText() && !"".equals(stadiumName.getText())) {
                stadiumName.setError("stadium name should not blank");
                return false;
            }if(null != sportName.getText() && !"".equals(sportName.getText())) {
                sportName.setError("sport name should not blank");
                return false;
            }if(null != noOfCourts.getText() && !"".equals(noOfCourts.getText())) {
                noOfCourts.setError("No of sports should not blank");
            return false;
            }if(null != groupTimmingsFrom.getText() && !"".equals(groupTimmingsFrom.getText())){
                groupTimmingsFrom.setError("time should not blank");
            return false;
            }
                if(null != groupTimmingsTo.getText() && !"".equals(groupTimmingsTo.getText())) {
                groupTimmingsTo.setError("time should not blank");
                    return false;
                }if(null != individualTimmingsFrom.getText() && !"".equals(individualTimmingsFrom.getText())) {
                individualTimmingsFrom.setError("time should not blank");
                return false;
                }if(null != individualTimmingsTo.getText() && !"".equals(individualTimmingsTo.getText())) {
                individualTimmingsTo.setError("time should not blank");
            return false;
                }
        return true;
    }

    private void openMaps() {
        Intent mapView = new Intent(this, MapsActivity.class);
        mapView.putExtra("ENABLE_PIN","enable");
        startActivity(mapView);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()){
                case R.id.stadium_name:
                    if(hasFocus){
                        if(null != stadiumName.getText() && !"".equals(stadiumName.getText())){
                            stadiumName.setError("stadium name should not blank");
                        }
                    }
                    break;
                case R.id.sportName:
                    if(hasFocus){
                        if(null != sportName.getText() && !"".equals(sportName.getText())){
                            sportName.setError("sport name should not blank");
                        }
                    }
                    break;
                case R.id.noOfCourts:
                    if(hasFocus){
                        if(null != noOfCourts.getText() && !"".equals(noOfCourts.getText())){
                            noOfCourts.setError("No of sports should not blank");
                        }
                    }
                    break;
                case R.id.grouptimmingsfrom:
                    if(hasFocus){
                        if(null != groupTimmingsFrom.getText() && !"".equals(groupTimmingsFrom.getText())){
                            groupTimmingsFrom.setError("time should not blank");
                        }
                    }
                    break;
                case R.id.grouptimmingsto:
                    if(hasFocus){
                        if(null != groupTimmingsTo.getText() && !"".equals(groupTimmingsTo.getText())){
                            groupTimmingsTo.setError("time should not blank");
                        }
                    }
                    break;
                case R.id.individualtimmingsfrom:
                    if(hasFocus){
                        if(null != individualTimmingsFrom.getText() && !"".equals(individualTimmingsFrom.getText())){
                            individualTimmingsFrom.setError("time should not blank");
                        }
                    }
                    break;
                case R.id.individualtimmingsto:
                    if(hasFocus){
                        if(null != individualTimmingsTo.getText() && !"".equals(individualTimmingsTo.getText())){
                            individualTimmingsTo.setError("time should not blank");
                        }
                    }
                    break;

            }

    }
	 
}
