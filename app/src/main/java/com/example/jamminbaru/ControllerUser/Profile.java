package com.example.jamminbaru.ControllerUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jamminbaru.R;
import com.example.jamminbaru.user.LoginUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {
    private ImageButton playlist;
    private ImageButton home;
    private TextView usernameTag;
    private Button logoutDirectLogin;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jamminapp-f2693-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String usernameTxt = getIntent().getStringExtra("usernameTag");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameTag = findViewById(R.id.usernameTag);
        logoutDirectLogin = (Button) findViewById(R.id.logoutDirectLogin);

        logoutDirectLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Profile.this, LoginUser.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

        home = (ImageButton) findViewById(R.id.homeLogo3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Home.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

        playlist = (ImageButton) findViewById(R.id.playlistLogo3);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Playlist.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

         usernameTag.setText(usernameTxt);
    }
//    public void GoPlaylist(View v){
//        Intent intent =new Intent(this,Playlist.class);
//        startActivity(intent);
//        finish();
//
//    }
//
//    public void GoHome(View v){
//        Intent intent =new Intent(this,Home.class);
//        startActivity(intent);
//        finish();
//
//    }

}