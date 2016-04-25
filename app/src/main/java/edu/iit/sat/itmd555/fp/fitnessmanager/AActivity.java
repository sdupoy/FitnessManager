package edu.iit.sat.itmd555.fp.fitnessmanager;

/**
 * Created by lisiling on 4/24/16.
 */

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class AActivity extends Activity implements SensorEventListener {

    private final float NOISE = (float) 2.5; // maybe increase the noise?
    private boolean mInitialized; // used for initializing sensor only once

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;
    private TextView count;
    private int stepsCount = 0;

    private double mLastX;
    private double mLastY;
    private double mLastZ;

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
}
