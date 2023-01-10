package com.example.jamminbaru.creator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamminbaru.ControllerCreator.HomeCreator;
import com.example.jamminbaru.Model.ModelCreator;
import com.example.jamminbaru.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginCreator extends AppCompatActivity {

    TextView loginAsUser;
    EditText username, password;
    Button login_Btn;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jamminapp-f2693-default-rtdb.firebaseio.com/");

    public static ModelCreator creator = new ModelCreator("default");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_creator);

        id_LoginCreator();

        loginAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginCreator.this, com.example.jamminbaru.user.LoginUser.class);
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
                    databaseReference.child("Creator").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernameTxt)){

                                final String getPassword = snapshot.child(usernameTxt).child("Password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(LoginCreator.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                    creator.setPhoneTxt(usernameTxt);
                                    //creator.getPhoneTxt();
                                    Intent intent = new Intent(LoginCreator.this, HomeCreator.class);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(LoginCreator.this, "Username or Password Incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LoginCreator.this, "Username or Password Incorrect", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, RegisterCreator.class);
        startActivity(intent);
        finish();
    }

    public void GoHome(View v){
        Intent intent = new Intent(this, HomeCreator.class);
        startActivity(intent);
        finish();
    }

    public void id_LoginCreator(){
        username = findViewById(R.id.usernameCreator);
        password = findViewById(R.id.passwordCreator);
        login_Btn = findViewById(R.id.loginbuttonCreator);
        loginAsUser = findViewById(R.id.loginAsUser);
    }

}