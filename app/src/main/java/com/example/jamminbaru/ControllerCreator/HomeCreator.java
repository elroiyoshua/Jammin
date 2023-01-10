package com.example.jamminbaru.ControllerCreator;

import static com.example.jamminbaru.creator.LoginCreator.creator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamminbaru.Model.ModelHomeCreator;
import com.example.jamminbaru.Model.ModelProfileCreator;
import com.example.jamminbaru.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class HomeCreator extends AppCompatActivity {
    ImageButton profile;
    TextView usernamedisplayTagHome, chooseSong;
    Button upload_btn, delete_btn;
    
    private Uri audioUri;
    private ImageView imageView_Song;
    private final String displayUsername = creator.getPhoneTxt();
    private final DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Creator").child(displayUsername).child("Song");
    private final StorageReference reference = FirebaseStorage.getInstance().getReference().child("Creator").child(displayUsername).child("Song");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_creator);

        id_homeCreator();

        usernamedisplayTagHome.setText(displayUsername);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeCreator.this, ProfileCreator.class);
                startActivity(intent);
            }
        });

        chooseSong = findViewById(R.id.chooseSong);
        chooseSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("audio/*");
                startActivityForResult(galleryIntent , 2);
            }
        });

        upload_btn = findViewById(R.id.upload_btn);
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(audioUri != null){
                    uploadToFirebase(audioUri);
                }else{
                    Toast.makeText(HomeCreator.this, "Please Select Your Sound", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete_btn = findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            root.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    imageView_Song.setImageDrawable(ContextCompat.getDrawable(HomeCreator.this, R.drawable.ic_baseline_close_24));
                                    Toast.makeText(HomeCreator.this, "Delete Song Successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(HomeCreator.this, "No Song Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        getHomeCreatorInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null){
            audioUri = data.getData();
            //imageView.setImageURI(audioUri);
            imageView_Song.setImageDrawable(ContextCompat.getDrawable(HomeCreator.this, R.drawable.ic_baseline_audio_file_24));
            Toast.makeText(this, "Song is Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void getHomeCreatorInfo(){
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.getChildrenCount() > 0){
                    if (snapshot.hasChild("soundUrl")){
                        // link image foto ditemukan, tampilkan gambar
                        //String image = snapshot.child("imageUrl").getValue().toString();
                        //Picasso.get().load(image).into(imageView);
                        imageView_Song.setImageDrawable(ContextCompat.getDrawable(HomeCreator.this, R.drawable.ic_baseline_audio_file_24));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void uploadToFirebase(Uri uri){
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String downloadUrl = uri.toString();
                        ModelHomeCreator model = new ModelHomeCreator(downloadUrl);
                        root.setValue(model);
                        //progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(HomeCreator.this, "Uploaded Successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                //progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(HomeCreator.this, "Uploading Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    public void id_homeCreator(){
        imageView_Song = findViewById(R.id.imageView_Song);
        usernamedisplayTagHome = findViewById(R.id.usernamedisplayTagHome);
        profile = findViewById(R.id.profileLogo);
    }
}