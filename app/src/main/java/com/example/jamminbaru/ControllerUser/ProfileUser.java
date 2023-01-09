package com.example.jamminbaru.ControllerUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamminbaru.Model.ModelProfileUser;
import com.example.jamminbaru.R;
import com.example.jamminbaru.user.LoginUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileUser extends AppCompatActivity {
    ImageButton playlist;
    ImageButton home;
    TextView usernameTag;
    Button logoutDirectLogin;

    private Button upload_btn;
    private ImageView imageView;
    private ProgressBar progressBar;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Profile_Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://jamminapp-f2693-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String usernameTxt = getIntent().getStringExtra("usernameTag");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        usernameTag = findViewById(R.id.usernameTag);
        logoutDirectLogin = (Button) findViewById(R.id.logoutDirectLogin);
        upload_btn = findViewById(R.id.upload_btn);
        imageView = findViewById(R.id.profileimg);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);
            }
        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri != null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(ProfileUser.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutDirectLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ProfileUser.this, LoginUser.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

        home = (ImageButton) findViewById(R.id.homeLogo3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileUser.this, HomeUser.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

        playlist = (ImageButton) findViewById(R.id.playlistLogo3);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileUser.this, PlaylistUser.class);
                intent.putExtra("usernameTag", usernameTxt);
                startActivity(intent);
            }
        });

         usernameTag.setText(usernameTxt);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void uploadToFirebase(Uri uri){
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        ModelProfileUser model = new ModelProfileUser(uri.toString());
                        String modelId = root.push().getKey();
                        root.child(modelId).setValue(model);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(ProfileUser.this, "Uploaded Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(ProfileUser.this, "Uploading Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
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