package com.ahmad.attendedjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splash extends AppCompatActivity {
int splashtime=50000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(splashtime/100);
                }catch (InterruptedException e){
                    e.printStackTrace();

                }
                startActivity(new Intent(splash.this, login.class));
            }
        }).start();
    }
}