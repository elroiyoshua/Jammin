package com.example.jamminbaru.ControllerUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.jamminbaru.R;

public class Playlist extends AppCompatActivity {
    private ImageButton home;
    private ImageButton profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String usernameTxt = getIntent().getStringExtra("usernameTag");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        profile = (ImageButton) findViewById(R.id.profileLogo2);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Playlist.this, Profile.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

        home = (ImageButton) findViewById(R.id.homeLogo2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Playlist.this, Home.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });
    }

//    public void GoHome(View v){
//        Intent intent =new Intent(this,Home.class);
//        startActivity(intent);
//        finish();
//
//    }
//
//    public void GoProfile(View v){
//        Intent intent =new Intent(this,Profile.class);
//        startActivity(intent);
//        finish();
//
//    }

}