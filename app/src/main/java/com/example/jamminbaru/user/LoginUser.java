package com.example.jamminbaru.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamminbaru.ControllerUser.HomeUser;
import com.example.jamminbaru.Model.ModelUser;
import com.example.jamminbaru.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginUser extends AppCompatActivity {

    TextView loginAsCreator;
    EditText username, password;
    Button login_Btn;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jamminapp-f2693-default-rtdb.firebaseio.com/");

    public static ModelUser user = new ModelUser("default");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        id_LoginUser();

        loginAsCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginUser.this, com.example.jamminbaru.creator.LoginCreator.class);
                startActivity(intent);
            }
        });

        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usernameTxt = username.getText().toString();
                final String passwordTxt = password.getText().toString();

                if(usernameTxt.isEmpty()){
                    username.setError("Enter Username");
                }if(passwordTxt.isEmpty()) {
                    password.setError("Enter Password");
                }else if(usernameTxt.isEmpty()){
                    username.setError("Enter Username");
                }else{
                    databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernameTxt)){

                                final String getPassword = snapshot.child(usernameTxt).child("Password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(LoginUser.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                    user.setPhoneTxt(usernameTxt);
                                    //user.getPhoneTxt();
                                    Intent intent = new Intent(LoginUser.this, HomeUser.class);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(LoginUser.this, "Username or Password Incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LoginUser.this, "Username or Password Incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
    }

    public void GoSignup(View v){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
        finish();
    }

    public void GoHome(View v){
        Intent intent = new Intent(this, HomeUser.class);
        startActivity(intent);
        finish();
    }

    public void id_LoginUser(){
        username = findViewById(R.id.usernameUser);
        password = findViewById(R.id.passwordUser);
        login_Btn = findViewById(R.id.loginbuttonUser);
        loginAsCreator = findViewById(R.id.loginAsCreator);
    }

}