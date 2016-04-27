package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by lisiling on 4/24/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.iit.sat.itmd555.fp.fitnessmanager.model.Step;

public class AActivity extends Activity implements SensorEventListener {

    private final float NOISE = (float) 2.5; // maybe increase the noise?
    private boolean mInitialized; // used for initializing sensor only once

    private Calendar calendar;
    private int year, month, day;

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;
    private TextView count;
    private int stepsCount = 0;

    private double mLastX;
    private double mLastY;
    private double mLastZ;

    private SqlHelper db;
    private Step currentStep;
    private List<Step> steps;

    private ListView listContent;
    private StepsAdapter customAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);
        count = (TextView) findViewById(R.id.textView1);

        // Initialize Accelerometer sensor
        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        startSensor();


        db = SqlHelper.getInstance(getApplicationContext());
/*
        for(int i=1; i<10; i++){
            Step step = new Step();
            step.setIdUser(1);
            String date = "2016-04-0" + String.valueOf(i);
            step.setStepsDate(date);
            step.setNbOfSteps(i*i);
            db.addSteps(step);
        }
*/
        listContent = (ListView) findViewById(R.id.activityList);
        steps = db.getAllStepsByUser(1);

        listContent.destroyDrawingCache();
        listContent.setVisibility(ListView.INVISIBLE);
        listContent.setVisibility(ListView.VISIBLE);
        customAdapter = new StepsAdapter(this, steps);
        listContent.setAdapter(customAdapter);

        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int position,
                                    long arg3) {
                TextView date = (TextView) findViewById(R.id.dateActivity);
                Log.d("Date: ", steps.get(position).toString());
                Log.d("position: ", String.valueOf(position));

                // if details availabel !!!
                //Intent i = new Intent(AActivity.this, ViewDateDetails.class);
                //i.putExtra("date", steps.get(position).getStepsDate());
                //startActivity(i);
            }
        });

        listContent.setLongClickable(true);
        listContent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, long id) {
                Log.d("long clicked","pos: " + pos);
                AlertDialog diaBox = AskOption(steps.get(pos));
                diaBox.show();
                return true;
            }
        });
        checkingCurrentStep();
        stepsCount = currentStep.getNbOfSteps();
        count.setText(String.valueOf(currentStep.getNbOfSteps()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkingCurrentStep();
    }

    private void startSensor() {
        mLastX = 0.0;
        mLastY = 0.0;
        mLastZ = 0.0;
        mSensorManager.registerListener((SensorEventListener) this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // event object contains values of acceleration, read those
        double x = event.values[0];
        double y = event.values[1];
        double z = event.values[2];
        final double alpha = 0.8; // constant for our filter below

        double[] gravity = {0,0,0};

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

// Remove the gravity contribution with the high-pass filter.
        x = event.values[0] - gravity[0];
        y = event.values[1] - gravity[1];
        z = event.values[2] - gravity[2];

        if (mInitialized) {
            // sensor is already initialized, and we have previously read values.
            // take difference of past and current values and decide which
            // axis acceleration was detected by comparing values

            double deltaX = Math.abs(mLastX - x);
            double deltaY = Math.abs(mLastY - y);
            double deltaZ = Math.abs(mLastZ - z);
            if (deltaX < NOISE)
                deltaX = (float) 0.0;
            if (deltaY < NOISE)
                deltaY = (float) 0.0;
            if (deltaZ < NOISE)
                deltaZ = (float) 0.0;
            mLastX = x;
            mLastY = y;
            mLastZ = z;

            if (deltaX > deltaY) {
                // Horizontal shake
            } else if (deltaY > deltaX) {
                // Vertical shake
            } else if ((deltaZ > deltaX) && (deltaZ > deltaY)) {
                // Z shake
                stepsCount = stepsCount + 1;
                if (stepsCount > 0) {
                    count.setText(String.valueOf(stepsCount));
                    currentStep.setNbOfSteps(stepsCount);
                }
            }
        } else {
            // sensor is used for the first time, initialize the last read values
            mLastX = x;
            mLastY = y;
            mLastZ = z;
            mInitialized = true;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(db.getStepsByDateAndUser(1, currentStep.getStepsDate())==null){
            db.addSteps(currentStep);
        } else {
            db.updateStep(db.getStepsByDateAndUser(currentStep.getIdUser(), currentStep.getStepsDate()), currentStep.getStepsDate(), currentStep.getNbOfSteps());
        }
    }

    private AlertDialog AskOption(final Step step)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)

                .setTitle("Delete")
                .setMessage("Do you want to delete all details of this day ?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //db.deleteWorkoutActivity();
                        db.deleteStep(step.getId());
                        dialog.dismiss();
                        steps = db.getAllStepsByUser(1);
                        listContent.destroyDrawingCache();
                        customAdapter = new StepsAdapter(getApplicationContext(), steps);
                        listContent.setAdapter(customAdapter);
                        listContent.setVisibility(ListView.INVISIBLE);
                        listContent.setVisibility(ListView.VISIBLE);
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    protected void checkingCurrentStep(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        date.replace('-','/');
        Log.d("date step: ", date);
        currentStep = new Step();
        currentStep.setIdUser(1);
        currentStep.setStepsDate(date);
        if(db.getStepsByDateAndUser(currentStep.getIdUser(), currentStep.getStepsDate())!=null){
            currentStep.setNbOfSteps(db.getNbStepsByDateAndUser(currentStep.getIdUser(), currentStep.getStepsDate()));
        }
        steps = db.getAllStepsByUser(1);
        listContent.destroyDrawingCache();
        customAdapter = new StepsAdapter(this, steps);
        listContent.setAdapter(customAdapter);
        listContent.setVisibility(ListView.INVISIBLE);
        listContent.setVisibility(ListView.VISIBLE);

    }
}
