package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText edit_email = findViewById(R.id.emailText);
        final EditText edit_password = findViewById(R.id.passwordText);
        final EditText edit_confirmPassword = findViewById(R.id.passwordConfirmText);
        Button btn = findViewById(R.id.register_btn);
        DAOUser dao = new DAOUser();
        btn.setOnClickListener(view -> {
            User usr = new User(edit_email.getText().toString(), edit_password.getText().toString(), edit_confirmPassword.getText().toString());
            dao.add(usr).addOnSuccessListener(suc->{
                Toast.makeText(this, "Akun Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }).addOnFailureListener(er->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
    public void GoLogin(View v){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}