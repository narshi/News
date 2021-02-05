package com.newsapp.myapplication.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.reciproci.myapplication.R;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, NewsActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
