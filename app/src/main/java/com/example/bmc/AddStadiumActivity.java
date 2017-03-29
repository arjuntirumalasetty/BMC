package com.example.bmc;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import adapters.PagerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AddStadiumActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, View.OnClickListener,View.OnFocusChangeListener {
    ViewPager viewPager;
    int hour,min,hourFinal,minFinal;
    @Bind(R.id.grouptimmingsfrom)
    EditText groupTimmingsFrom;
    @Bind(R.id.grouptimmingsto)
    EditText groupTimmingsTo;
    @Bind(R.id.individualtimmingsfrom)
    EditText individualTimmingsFrom;
    @Bind(R.id.individualtimmingsto)
    EditText individualTimmingsTo;
    @Bind(R.id.stadiumName)
            EditText stadiumName;
    @Bind(R.id.sportName)
            EditText sportName;
    @Bind(R.id.noOfCourts)
            EditText noOfCourts;
    EditText activateTextView;
    Button submitStadium;
    @Bind(R.id.btn_tap_to_pin)
    Button pinMyLocation_Btn;
    @Bind(R.id.add_btn_submit)
    Button baddStadium;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stadium);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        groupTimmingsFrom.setOnClickListener(this);
        groupTimmingsTo.setOnClickListener(this);
        individualTimmingsFrom.setOnClickListener(this);
        individualTimmingsTo.setOnClickListener(this);
        groupTimmingsFrom.setOnFocusChangeListener(this);
        groupTimmingsTo.setOnFocusChangeListener(this);
        individualTimmingsFrom.setOnFocusChangeListener(this);
        individualTimmingsTo.setOnFocusChangeListener(this);
        stadiumName.setOnFocusChangeListener(this);
        sportName.setOnFocusChangeListener(this);
        noOfCourts.setOnFocusChangeListener(this);
        pinMyLocation_Btn.setOnClickListener(this);
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
        }else if(v.getId() == R.id.add_btn_submit){
            //Validate and submit
        }else{
            activateTextView = (EditText) findViewById(v.getId());
            Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR);
            min = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog =  new TimePickerDialog(this,this,hour,min, DateFormat.is24HourFormat(this));
            timePickerDialog.show();
        }
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
