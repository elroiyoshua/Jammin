package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private ImageButton playlist;
    private ImageButton profile;
    private TextView usernamedisplayTagHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String usernameTxt = getIntent().getStringExtra("usernameTag");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usernamedisplayTagHome = findViewById(R.id.usernamedisplayTagHome);

        profile = (ImageButton) findViewById(R.id.profileLogo);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Profile.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

        playlist = (ImageButton) findViewById(R.id.playlistLogo);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Playlist.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

        usernamedisplayTagHome.setText(usernameTxt);
    }

//    public void GoPlaylist(View v){
//        Intent intent =new Intent(this,Playlist.class);
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
////        Intent intent =new Intent(this,Profile.class);
////        intent.putExtra("usernameTag", usernameTxt);
////        startActivity(intent);
//
//    }
}