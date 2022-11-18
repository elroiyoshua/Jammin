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

public class SignUp extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jamminapp-f2693-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText edit_phone = findViewById(R.id.phoneText);
        final EditText edit_email = findViewById(R.id.emailText);
        final EditText edit_password = findViewById(R.id.passwordText);
        final EditText edit_confirmPassword = findViewById(R.id.passwordConfirmText);

        Button register_btn = findViewById(R.id.register_btn);
//        DAOUser dao = new DAOUser();
//
//        register_btn.setOnClickListener(view -> {
//            User usr = new User(edit_email.getText().toString(), edit_password.getText().toString(), edit_confirmPassword.getText().toString());
//            dao.add(usr).addOnSuccessListener(suc->{
//                Toast.makeText(this, "Akun Berhasil Registrasi", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, Login.class);
//                startActivity(intent);
//            }).addOnFailureListener(er->{
//                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTxt = edit_phone.getText().toString();
                final String emailTxt = edit_email.getText().toString();
                final String passwordTxt = edit_password.getText().toString();
                final String passwordConfirmTxt = edit_confirmPassword.getText().toString();


                if(emailTxt.isEmpty() || passwordTxt.isEmpty() || passwordConfirmTxt.isEmpty()){
                    Toast.makeText(SignUp.this, "Harap Isi Semua Data", Toast.LENGTH_SHORT).show();
                }else if(!passwordTxt.equals(passwordConfirmTxt)){
                    Toast.makeText(SignUp.this, "Password Yang Diinput Tidak Sama", Toast.LENGTH_SHORT).show();
                }else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //Apakah user terdaftar
                            if(snapshot.hasChild(phoneTxt)){
                                Toast.makeText(SignUp.this, "User Sudah Pernah Terdaftar", Toast.LENGTH_SHORT).show();
                            }
//                            else if(snapshot.hasChild(emailTxt)){
//                                Toast.makeText(SignUp.this, "Email Sudah Pernah Terdaftar", Toast.LENGTH_SHORT).show();
//                            }
                            else{
                                databaseReference.child("users").child(phoneTxt).child("Email").setValue(emailTxt);
                                databaseReference.child("users").child(phoneTxt).child("Password").setValue(passwordTxt);
                                databaseReference.child("users").child(phoneTxt).child("Confirm Password").setValue(passwordConfirmTxt);

                                Toast.makeText(SignUp.this, "Pengguna Berhasil Terdaftar", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, Login.class));
                                finish();
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
    public void GoLogin(View v){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}