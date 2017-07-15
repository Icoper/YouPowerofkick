package com.apps_x.samik.youpowerofkick.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apps_x.samik.youpowerofkick.R;
import com.apps_x.samik.youpowerofkick.dataBase.SharedPrefWorker;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPrefWorker sharedPrefWorker;
    private TextView bestGameScoreTV;
    private TextView bestUserScoreTV;
    private FButton startGameBtn;
    private FButton scoreBtn;
    private FButton settingsBtn;
    private FButton guideBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.rotate, R.anim.alpha);
        initialize();
        setupUI();
    }

    private void setupUI() {
        bestGameScoreTV.setText(getString(R.string.top_user_best_score) +
                " " +
                String.valueOf(sharedPrefWorker.getTopBest()));

        bestUserScoreTV.setText(getString(R.string.user_best_score) +
                " " +
                String.valueOf(sharedPrefWorker.getUserBest()));
    }

    private void initialize() {
        sharedPrefWorker = new SharedPrefWorker(this);

        bestGameScoreTV = (TextView) findViewById(R.id.ma_best_score);
        bestUserScoreTV = (TextView) findViewById(R.id.ma_you_score);
        startGameBtn = (FButton) findViewById(R.id.ma_start_game);

        startGameBtn.setOnClickListener(this);
        scoreBtn = (FButton) findViewById(R.id.ma_scores);
        scoreBtn.setOnClickListener(this);
        settingsBtn = (FButton) findViewById(R.id.ma_settings);
        settingsBtn.setOnClickListener(this);
        guideBtn = (FButton) findViewById(R.id.ma_guide);
        guideBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ma_start_game) {
            startActivity(new Intent(MainActivity.this, GameActivity.class));
            overridePendingTransition(R.anim.rotate, R.anim.alpha);
            // TODO
        } else if (id == R.id.ma_scores) {
            // TODO
        } else if (id == R.id.ma_settings) {
            // TODO
        } else if (id == R.id.ma_guide) {
            // TODO
        }
    }
}
