package com.apps_x.samik.youpowerofkick.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.apps_x.samik.youpowerofkick.R;

import info.hoang8f.widget.FButton;

/**
 * Created by icoper on 09.07.17.
 */

public class ShowResultActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String POWER_COUNT_INTENT = "power_intent";

    private FButton toHomeBtn;
    private TextView resultTextView;
    private int userPowerCount;
    private FButton replayBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.rotate, R.anim.alpha);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {
        toHomeBtn = (FButton) findViewById(R.id.ra_to_home_btn);
        toHomeBtn.setOnClickListener(this);
        replayBtn = (FButton) findViewById(R.id.ra_replay_btn);
        replayBtn.setOnClickListener(this);

        resultTextView = (TextView) findViewById(R.id.ra_count_kick_tv);
        Intent intent = getIntent();
        userPowerCount = intent.getIntExtra(POWER_COUNT_INTENT, 0);
        resultTextView.setText(String.valueOf(userPowerCount));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.ra_to_home_btn:
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.rotate, R.anim.alpha);
                break;
            case R.id.ra_replay_btn:
                startActivity(new Intent(this, GameActivity.class));
                overridePendingTransition(R.anim.rotate, R.anim.alpha);
                break;
        }
    }
}
