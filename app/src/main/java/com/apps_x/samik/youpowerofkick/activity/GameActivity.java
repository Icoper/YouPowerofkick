package com.apps_x.samik.youpowerofkick.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.apps_x.samik.youpowerofkick.R;
import com.apps_x.samik.youpowerofkick.dataBase.SharedPrefWorker;
import com.apps_x.samik.youpowerofkick.gameEngine.SensorManagerWorker;

import info.hoang8f.widget.FButton;
import pl.droidsonroids.gif.GifImageView;

public class GameActivity extends AppCompatActivity {
    private FButton startBtn;
    private TextView impactText;
    private TextView bestUserCountText;
    private GifImageView imageGif;
    private float userPower;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
    }

    private void initialize() {
        SharedPrefWorker prefWorker = new SharedPrefWorker(this);
        imageGif = (GifImageView) findViewById(R.id.ga_wait_gif);
        impactText = (TextView) findViewById(R.id.ga_text_impact_tv);

        // init best user count
        bestUserCountText = (TextView) findViewById(R.id.ga_count_best_user_tv);
        bestUserCountText.setText(getString(R.string.user_best_score) +
                " " +
                String.valueOf(prefWorker.getUserBest()));

        bestUserCountText.setVisibility(View.VISIBLE);
        imageGif.setVisibility(View.INVISIBLE);
        impactText.setVisibility(View.INVISIBLE);

        startBtn = (FButton) findViewById(R.id.ga_user_ready_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageGif.setVisibility(View.VISIBLE);
                impactText.setVisibility(View.VISIBLE);
                startBtn.setVisibility(View.INVISIBLE);
                bestUserCountText.setVisibility(View.INVISIBLE);
                new SensorManagerWorker(GameActivity.this).startRecording();
            }
        });

    }

}
