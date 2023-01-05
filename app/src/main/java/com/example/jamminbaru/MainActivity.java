package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import com.example.jamminbaru.creator.LoginCreator;
import com.example.jamminbaru.creator.RegisterCreator;
import com.example.jamminbaru.user.LoginUser;
import com.example.jamminbaru.user.RegisterUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginUser.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}