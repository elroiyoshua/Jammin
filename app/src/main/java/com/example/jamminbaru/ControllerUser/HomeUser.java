package com.example.jamminbaru.ControllerUser;

import static com.example.jamminbaru.user.LoginUser.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jamminbaru.Model.ModelUser;
import com.example.jamminbaru.R;
import com.example.jamminbaru.User;
import com.example.jamminbaru.home2;
import com.example.jamminbaru.lagu1;
import com.example.jamminbaru.lagu2;
import com.example.jamminbaru.lagu3;
import com.example.jamminbaru.user.LoginUser;

public class HomeUser extends AppCompatActivity {
    private ImageButton playlist;
    private ImageButton profile;
    private TextView usernamedisplayTagHome;
//    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        String usernameTxt = getIntent().getStringExtra("usernameTag");
//        String usernameTemp = usernameTxt;
//        user.setPhoneTxt(usernameTxt);

        String displayUsername = user.getPhoneTxt();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usernamedisplayTagHome = findViewById(R.id.usernamedisplayTagHome);
        usernamedisplayTagHome.setText(displayUsername);

        profile = (ImageButton) findViewById(R.id.profileLogo);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeUser.this, ProfileUser.class);
//                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

        playlist = (ImageButton) findViewById(R.id.playlistLogo);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeUser.this, PlaylistUser.class);
                //intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });


    }

    public void GoLagu1(View v){
        Intent intent =new Intent(this, lagu1.class);
        startActivity(intent);
        finish();
    }

    public void GoLagu2(View v){
        Intent intent =new Intent(this, lagu2.class);
        startActivity(intent);
        finish();
    }

    public void GoLagu3(View v){
        Intent intent =new Intent(this, lagu3.class);
        startActivity(intent);
        finish();

    }

    public void sementara(View v){
        Intent intent =new Intent(this, home2.class);
        startActivity(intent);
        finish();
    }
//
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
//        Intent intent =new Intent(this,Profile.class);
//        intent.putExtra("usernameTag", usernameTxt);
//        startActivity(intent);
//
//    }
}