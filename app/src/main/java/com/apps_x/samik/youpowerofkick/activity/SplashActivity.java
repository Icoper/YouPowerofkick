package com.apps_x.samik.youpowerofkick.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;


public class SplashActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        updateData();
        startGameActivity();
    }

    private void startGameActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateData() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
