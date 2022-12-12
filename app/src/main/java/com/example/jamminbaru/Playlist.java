package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Playlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
    }
    public void GoHome(View v){
        Intent intent =new Intent(this,Home.class);
        startActivity(intent);
        finish();

    }

    public void GoProfile(View v){
        Intent intent =new Intent(this,Profile.class);
        startActivity(intent);
        finish();

    }

}