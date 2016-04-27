package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by lisiling on 4/24/16.
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.ActivitySport;

public class BActivity extends Activity{

    private SqlHelper db;

    private Calendar calendar;
    private TextView dateSelected;
    private int year, month, day;

    private EditText durationHours;
    private EditText durationMinutes;
    private EditText durationSeconds;
    private RatingBar feedbackRatingBar;
    private Button addSportActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        dateSelected = (TextView) findViewById(R.id.dateSelected);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        durationHours = (EditText) findViewById(R.id.durationHours);
        durationMinutes = (EditText) findViewById(R.id.durationMinutes);
        durationSeconds = (EditText) findViewById(R.id.durationSeconds);
        feedbackRatingBar = (RatingBar) findViewById(R.id.feedbackRatingBar);

        db = SqlHelper.getInstance(getApplicationContext());

        addSportActivity = (Button) findViewById(R.id.addSportActivity);


        addSportActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (durationHours.getText().length() == 0 && durationMinutes.getText().length() == 0 && durationSeconds.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid duration", Toast.LENGTH_LONG).show();
                    return;
                } // else if (duration validator: minutes and seconds must be lower than 60)
                else if ((Integer.parseInt(durationMinutes.getText().toString()) > 59) || (Integer.parseInt(durationSeconds.getText().toString()) > 59) ){
                    Toast.makeText(getApplicationContext(), "Please enter a valid duration", Toast.LENGTH_LONG).show();
                    return;
                } // else if (duration validator: hours, minutes and seconds must not be all equal to 0)
                else if ((Integer.parseInt(durationHours.getText().toString()) == 0) && (Integer.parseInt(durationMinutes.getText().toString()) == 0) && (Integer.parseInt(durationSeconds.getText().toString()) == 0) ){
                    Toast.makeText(getApplicationContext(), "Please enter a valid duration", Toast.LENGTH_LONG).show();
                    return;
                } // else if it's ok
                else {
                    ActivitySport activity = new ActivitySport();
                    activity.setDate(dateSelected.getText().toString());
                    activity.setIdUser(1);
                    activity.setDurationHours((Integer.parseInt(durationHours.getText().toString())));
                    activity.setDurationMinutes((Integer.parseInt(durationMinutes.getText().toString())));
                    activity.setDurationSeconds((Integer.parseInt(durationSeconds.getText().toString())));
                    activity.setJustCreated(1);
                    activity.setFeedback(Float.toString(feedbackRatingBar.getRating()));
                    EditText distTitle = (EditText) findViewById(R.id.distTitleInput);
                    activity.setTitle(distTitle.getText().toString());
                    Log.d("Date: ", activity.getDate());
                    Log.d("Duration hours: ", String.valueOf(activity.getDurationHours()));
                    Log.d("Duration minutes: ", String.valueOf(activity.getDurationMinutes()));
                    Log.d("Duration seconds: ", String.valueOf(activity.getDurationSeconds()));
                    Log.d("just created: ", Integer.toString(activity.getJustCreated()));
                    Log.d("feedback: ",  activity.getFeedback());
                    Log.d("title: ",  activity.getTitle());

                    RadioButton distance = (RadioButton) findViewById(R.id.distance);
                    RadioButton workout = (RadioButton) findViewById(R.id.workout);
                    if(distance.isChecked()){
                        activity.setType("Distance");
                        Log.d("Type: ", activity.getType());
                        //Store the activity into db !
                        db.addActivity(activity);
                        // Start the new activity to add distance
                        Intent i = new Intent(getApplicationContext(), AddDistanceActivity.class);
                        startActivity(i);

                    } else {
                        activity.setType("Workout");
                        Log.d("Type: ", activity.getType());
                        // Store the activity into db !
                        db.addActivity(activity);
                        Intent i = new Intent(getApplicationContext(), AddWorkoutActivity.class);
                        startActivity(i);


                    }

                }

            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        StringBuilder date = new StringBuilder();
        date.append(year).append("-");
        if(month<10){
            date.append("0");
        }
        date.append(month).append("-");
        if(day<10){
            date.append("0");
        }
        date.append(day);
        dateSelected.setText(date);
    }

}
