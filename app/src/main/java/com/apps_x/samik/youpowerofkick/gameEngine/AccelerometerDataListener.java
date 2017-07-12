package com.apps_x.samik.youpowerofkick.gameEngine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.apps_x.samik.youpowerofkick.R;
import com.apps_x.samik.youpowerofkick.activity.ShowResultActivity;
import com.apps_x.samik.youpowerofkick.dataBase.SharedPrefWorker;

import java.util.ArrayList;
import java.util.List;


class AccelerometerDataListener {
    private static final String LOG_TAG = "AccelerometerDataListener";
    private static final String POWER_COUNT_INTENT = "power_intent";
    private static final int WRITE_THRESHOLD = 15;

    final float alpha = (float) 0.8;
    private static boolean recordDataIsStart = false;
    private int sumLinear;
    private static ArrayList<Integer> sumList;
    private int finalSum;
    private SensorEventListener sensorEventListener;
    private Context contextApp;
    private boolean stopWork;

    public AccelerometerDataListener(Context contextApp, SensorEventListener eventListener) {
        this.contextApp = contextApp;
        this.sensorEventListener = eventListener;
    }

    public boolean updateDataArray(float valueX, float valueY, float valueZ) {

        float gravity[] = new float[3];
        float linear_acceleration[] = new float[3];

        // отфильтровываем отрицателные значения акселерометра
        if (valueX < 0) {
            valueX = 0;
        } else if (valueY < 0) {
            valueY = 0;
        } else if (valueZ < 0) {
            valueZ = 0;
        }
        gravity[0] = alpha * gravity[0] + (1 - alpha) * valueX;
        gravity[1] = alpha * gravity[1] + (1 - alpha) * valueY;
        gravity[2] = alpha * gravity[2] + (1 - alpha) * valueZ;

        linear_acceleration[0] = valueX - gravity[0];
        linear_acceleration[1] = valueY - gravity[1];
        linear_acceleration[2] = valueZ - gravity[2];

        sumLinear = 0;
        for (float f : linear_acceleration) {
            sumLinear += f;
        }

        Log.d(LOG_TAG, "sumLinear = " + sumLinear);

        if (!recordDataIsStart) {
            checkSum(sumLinear);
        } else {
            startRecord(sumLinear);
        }

        return stopWork;

    }

    private void checkSum(int sumLinear) {

        if (sumLinear >= WRITE_THRESHOLD) {
            recordDataIsStart = true;
        }
    }

    private void startRecord(int sum) {

        if (sumList == null) {
            sumList = new ArrayList<>();
        }

        Log.d(LOG_TAG, "arrSize = " + sumList.size());
        sumList.add(sum);

        if (sumList.size() == 50) {

            recordDataIsStart = false;
            for (int i : sumList) {
                finalSum += i;
            }
            stopRecord();

        }


    }

    private void stopRecord() {
        stopWork = true;

        // show result
        Intent intent = new Intent(contextApp, ShowResultActivity.class);
        intent.putExtra(POWER_COUNT_INTENT, finalSum);
        // check user score.
        SharedPrefWorker prefWorker = new SharedPrefWorker(contextApp);
        if (prefWorker.getUserBest() < finalSum){
            prefWorker.saveUserBest(finalSum);
        }
        // set default data
        sumLinear = 0;
        sumList = null;
        finalSum = 0;


        contextApp.startActivity(intent);



    }


}



