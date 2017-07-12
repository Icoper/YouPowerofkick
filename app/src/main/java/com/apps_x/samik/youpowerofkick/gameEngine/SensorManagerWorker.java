package com.apps_x.samik.youpowerofkick.gameEngine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import static android.content.Context.SENSOR_SERVICE;


public class SensorManagerWorker implements SensorEventListener {
    private static final String LOG_TAG = "SensorManagerWorker";

    private static SensorManager sensorManager;
    private Context context;
    private Sensor sensorAccelerometer;
    private SensorEventListener sensorEventListener = this;

    public SensorManagerWorker() {
    }

    public SensorManagerWorker(Context context) {
        this.context = context;
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
    }


    /**
     * This method is called ones when the user starts the service.
     * In this method, we initialize the sensors, and subscribe them to the listener.
     */
    public void startRecording() {
        try {
            sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        regAccelSensorListener();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(LOG_TAG, "onSensorChanged");

        final AccelerometerDataListener dataListener = new AccelerometerDataListener(context, sensorEventListener);
        // We register and send each sensor reading in ArrayAdapterCustom
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(LOG_TAG, "event - accelerometer");

            // if updateDataArray returned 'true' - stop work;
            if (dataListener.updateDataArray(
                    event.values[0],
                    event.values[1],
                    event.values[2])) {

                unregAccelSensorListener();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }

    private void regAccelSensorListener() {
        sensorManager.registerListener(this, sensorAccelerometer, sensorManager.SENSOR_DELAY_GAME);
    }

    public void unregAccelSensorListener() {
        sensorManager.unregisterListener(this, sensorAccelerometer);
    }
}
