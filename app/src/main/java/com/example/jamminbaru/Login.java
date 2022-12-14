package com.example.jamminbaru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jamminapp-f2693-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final Button login_Btn = findViewById(R.id.loginbutton);

        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                final String emailTxt = email.getText().toString();
//                final String passwordTxt = password.getText().toString();

                final String usernameTxt = username.getText().toString();
                final String passwordTxt = password.getText().toString();

                if(usernameTxt.isEmpty()){
                    username.setError("Enter Username");
                }
                if(passwordTxt.isEmpty()){
                    password.setError("Enter Password");
                }else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernameTxt)){

                                final String getPassword = snapshot.child(usernameTxt).child("Password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(Login.this, "Berhasil Login", Toast.LENGTH_SHORT).show();

//                                    startActivity(new Intent(Login.this, Home.class));
//                                    finish();

                                    Intent intent = new Intent(Login.this, Home.class);
                                    intent.putExtra("usernameTag", usernameTxt);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(Login.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(Login.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }
    public void GoHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }
}