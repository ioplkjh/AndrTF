package ru.allmoyki.may.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ru.allmoyki.may.R;
import ru.allmoyki.may.preferences.MyPreferences;

/**
 * Created by Boichuk Dmitriy on 31.08.2015.
 */
public class SplashScreenActivity extends Activity {
    private static int SPLASH_SCREEN_TIMEOUT = 1500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (MyPreferences.getUserPushToken(getApplicationContext()) == null || MyPreferences.getAutoLogin(SplashScreenActivity.this)==false) {
                    intent = new Intent(SplashScreenActivity.this, CarWashLoginActivity.class);
                    MyPreferences.seUserPushToken(SplashScreenActivity.this,null);
                } else {
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}