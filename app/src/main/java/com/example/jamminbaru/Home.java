package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void GoPlaylist(View v){
        Intent intent =new Intent(this,Playlist.class);
        startActivity(intent);
        finish();

    }

    public void GoProfile(View v){
        Intent intent =new Intent(this,Profile.class);
        startActivity(intent);
        finish();

    }
    public void GoLagu1(View v){
        Intent intent =new Intent(this,Lagu1.class);
        startActivity(intent);
        finish();

    }
    public void GoLagu2(View v){
        Intent intent =new Intent(this,lagu2.class);
        startActivity(intent);
        finish();

    }
    public void GoLagu3(View v){
        Intent intent =new Intent(this,lagu3.class);
        startActivity(intent);
        finish();

    }
}