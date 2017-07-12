package com.apps_x.samik.youpowerofkick.dataBase;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by icoper on 10.07.17.
 */

public class SharedPrefWorker {
    private static final String APP_PREFERENCES = "you_power";
    private static final String APP_PREFERENCES_USER_BEST = "user_best";
    private static final String APP_PREFERENCES_TOP_BEST = "top_best";


    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPrefWorker(Context context) {
        this.context = context;
    }

    public void saveUserBest(int result) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(APP_PREFERENCES_USER_BEST, result);
        editor.apply();
    }

    public int getUserBest() {
        int result = 0;
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(APP_PREFERENCES_USER_BEST)) {

            result = sharedPreferences.getInt(APP_PREFERENCES_USER_BEST, 0);
        }

        return result;
    }

    public void saveTopBest(int result) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(APP_PREFERENCES_TOP_BEST, result);
        editor.apply();
    }

    public int getTopBest() {
        int result = 0;
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(APP_PREFERENCES_TOP_BEST)) {

            result = sharedPreferences.getInt(APP_PREFERENCES_TOP_BEST, 0);
        }

        return result;
    }
}
