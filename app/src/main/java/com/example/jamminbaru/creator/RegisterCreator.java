package com.example.jamminbaru.creator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jamminbaru.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterCreator extends AppCompatActivity {
    EditText edit_phone, edit_email, edit_password, edit_confirmPassword;
    Button register_btn;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jamminapp-f2693-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_creator);

        id_RegisterCreator();

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTxt = edit_phone.getText().toString();
                final String emailTxt = edit_email.getText().toString();
                final String passwordTxt = edit_password.getText().toString();
                final String passwordConfirmTxt = edit_confirmPassword.getText().toString();

                if (phoneTxt.isEmpty() && emailTxt.isEmpty() && passwordTxt.isEmpty() && passwordConfirmTxt.isEmpty()) {
                    // All fields are empty
                    edit_phone.setError("Enter Your Username");
                    edit_email.setError("Enter Your Email");
                    edit_password.setError("Enter Your Password");
                    edit_confirmPassword.setError("Enter Your Confirm Password");
                } else if (phoneTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty() || passwordConfirmTxt.isEmpty()) {
                    // Some fields are empty
                    if (phoneTxt.isEmpty()) {
                        edit_phone.setError("Enter Your Username");
                    }
                    if (emailTxt.isEmpty()) {
                        edit_email.setError("Enter Your Email");
                    }
                    if (passwordTxt.isEmpty()) {
                        edit_password.setError("Enter Your Password");
                    }
                    if (passwordConfirmTxt.isEmpty()) {
                        edit_confirmPassword.setError("Enter Your Confirm Password");
                    }
                }else{
                    if (passwordTxt.length() < 8) {
                        edit_password.setError("Password too short");
                    } else if (!phoneTxt.matches("^\\S*$")) {
                        edit_phone.setError("Username doesn't contain Space");
                    } else if (!emailTxt.matches("^\\S*$")) {
                        edit_email.setError("Email doesn't contain Space");
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) {
                        edit_email.setError("Please Input Valid Email");
                    } else if (!passwordTxt.equals(passwordConfirmTxt)) {
                        edit_confirmPassword.setError("Password didn't Match");
                    } else {
                        databaseReference.child("Creator").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //Apakah user terdaftar
                                if (snapshot.hasChild(phoneTxt)) {
                                    Toast.makeText(RegisterCreator.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                                } else {
                                    databaseReference.child("Creator").child(phoneTxt).child("Email").setValue(emailTxt);
                                    databaseReference.child("Creator").child(phoneTxt).child("Password").setValue(passwordTxt);
                                    databaseReference.child("Creator").child(phoneTxt).child("Confirm Password").setValue(passwordConfirmTxt);

                                    Toast.makeText(RegisterCreator.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterCreator.this, LoginCreator.class));
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
        });
    }

    public void GoLogin(View v){
        Intent intent = new Intent(this, LoginCreator.class);
        startActivity(intent);
        finish();
    }

    public void id_RegisterCreator(){
        edit_phone = findViewById(R.id.phoneTextCreator);
        edit_email = findViewById(R.id.emailTextCreator);
        edit_password = findViewById(R.id.passwordTextCreator);
        edit_confirmPassword = findViewById(R.id.passwordConfirmTextCreator);
        register_btn = findViewById(R.id.register_btnCreator);
    }
}