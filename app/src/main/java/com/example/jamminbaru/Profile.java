package com.example.jamminbaru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private TextView usernameTag;
    private TextView membershipTag;
    private Button logoutDirectLogin;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jamminapp-f2693-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String phoneTxt = getIntent().getStringExtra("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        usernameTag = findViewById(R.id.usernameTag);
        membershipTag = findViewById(R.id.membershipTag);
        logoutDirectLogin = (Button) findViewById(R.id.logoutDirectLogin);
        logoutDirectLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Profile.this, Login.class);
                intent.putExtra("username", phoneTxt);
                startActivity(intent);
            }
        });


    }
    public void GoPlaylist(View v){
        Intent intent =new Intent(this,Playlist.class);
        startActivity(intent);
        finish();

    }

    public void GoHome(View v){
        Intent intent =new Intent(this,Home.class);
        startActivity(intent);
        finish();

    }

}