package com.ftninformatika.zadatak10.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.ftninformatika.zadatak10.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean splash_scr = sharedPreferences.getBoolean("splashscreen_show", true);

        String time = sharedPreferences.getString("splashscreen_timeout", "1");

        int splashTimeOut;

        if (splash_scr)
            splashTimeOut = Integer.parseInt(time) * 3000;
        else
            splashTimeOut = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, splashTimeOut);

    }
}
