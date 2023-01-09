package com.example.jamminbaru.ControllerCreator;

import static com.example.jamminbaru.creator.LoginCreator.creator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jamminbaru.R;
import com.example.jamminbaru.creator.LoginCreator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileCreator extends AppCompatActivity {
    ImageButton home;
    TextView usernameTag;
    Button logoutDirectLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String displayUsername = creator.getPhoneTxt();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creator);

        usernameTag = findViewById(R.id.usernameTag);
        usernameTag.setText(displayUsername);

        logoutDirectLogin = (Button) findViewById(R.id.logoutDirectLogin);
        logoutDirectLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ProfileCreator.this, LoginCreator.class);
                startActivity(intent);
            }
        });

        home = (ImageButton) findViewById(R.id.homeLogo3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileCreator.this, HomeCreator.class);
                startActivity(intent);
            }
        });
    }
}