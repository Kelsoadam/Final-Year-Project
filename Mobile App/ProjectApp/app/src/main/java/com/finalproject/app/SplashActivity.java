package com.finalproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView textView = findViewById(R.id.textSplashScreen);
        textView.animate().translationX(1500).setDuration(1000).setStartDelay(5000);

        Thread thread = new Thread() {

            public void run() {
                try {
                    Thread.sleep(4000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashActivity.this,
                            DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        thread.start();
    }
}