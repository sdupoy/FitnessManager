package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Simon on 4/30/2016.
 */
public class DateDetail extends Activity {

    private SqlHelper db;
    private TextView dateOfDate;
    private TextView stepsOfDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_date);

        db = SqlHelper.getInstance(getApplicationContext());

        dateOfDate = (TextView) findViewById(R.id.dateOfDay);
        stepsOfDay = (TextView) findViewById(R.id.stepsOfDay);
    }
}
