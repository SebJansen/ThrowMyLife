package com.example.seb.throwmylife.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seb.throwmylife.R;
import com.example.seb.throwmylife.fragments.SaveScoreQuestionDialogFragment;

public class PlayActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    TextView labelSpeed;
    int achievedScore;
    long timeStarted;
    long timeInactive;
    boolean hasStarted;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int THRESHOLD = 50;

    private SaveScoreQuestionDialogFragment fragmentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // fetch the system's SensorManager
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // get a reference to the accelerometer
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // register the accelerometer, at default poll rate
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // Save last x value to compare and see if phone has descended or ascended
            float y = event.values[1];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = y - last_y / diffTime * 10000;

                if (speed > THRESHOLD) {
                    hasStarted = true;
                    incrementDisplayedScore(speed);

                    if (timeStarted == 0) {
                        timeStarted = System.currentTimeMillis();
                    }
                } else {
                    timeInactive += 100;

                    if (timeInactive >= 2000 && hasStarted) {
                        Toast.makeText(PlayActivity.this, "Achieved " + achievedScore + " points!", Toast.LENGTH_LONG).show();
                        senSensorManager.unregisterListener(this);

                        SaveScoreQuestionDialogFragment fragmentQuestion = new SaveScoreQuestionDialogFragment();
                        fragmentQuestion.setScore(achievedScore);
                        fragmentQuestion.setActivity(PlayActivity.this);
                        fragmentQuestion.show(getFragmentManager(), "fragmentQuestion");
                    }
                }

                last_y = y;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    private void incrementDisplayedScore(float speed) {
        achievedScore += Math.round(speed);
        labelSpeed = (TextView) findViewById(R.id.textPlayPoints);
        labelSpeed.setText(Integer.toString(achievedScore));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        fragmentQuestion.onActivityResult(requestCode, resultCode, data);
    }

}
