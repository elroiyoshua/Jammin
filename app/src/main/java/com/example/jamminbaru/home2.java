package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class home2 extends AppCompatActivity implements recyclerviewinterface{

    ArrayList<lagumodel> lagumodels = new ArrayList<>();
    int[]  images ={R.drawable.cover1,R.drawable.cover2,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3};
    String[] url ={"https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Paramore_%20This%20Is%20Why%20%5BOFFICIAL%20VIDEO%5D.mp3?alt=media&token=38a37bde-40f8-4739-a96c-90f9e5d02a38",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Bury%20The%20Light%20-%20Approaching%20Storm%20Mix%20(Added%20rain%20_%20alternate%20intro).mp3?alt=media&token=704112b3-d922-47c9-b244-db5c1fdf4b13",
    "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Ariis%20-%20GOOFY%20PHONK.mp3?alt=media&token=9ed3ad8f-f1e8-47f8-b842-0c62a6793282",
    "shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6"};

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        recyclerView = findViewById(R.id.recyclerview);
        setuplagumodels();
        MyAdapter adapter =  new MyAdapter(this,lagumodels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setuplagumodels(){
        String[] judullagu = getResources().getStringArray(R.array.Judul);
        String[] bandlagu = getResources().getStringArray(R.array.Band);
        for (int i = 0;i < judullagu.length;i++){
            lagumodels.add(new lagumodel(judullagu[i],bandlagu[i],images[i],url[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(home2.this, LaguAll.class);
        intent.putExtra("JUDUL",lagumodels.get(position).getJudul());
        intent.putExtra("BAND",lagumodels.get(position).getBand());
        intent.putExtra("IMAGES",lagumodels.get(position).getImage());
        intent.putExtra("URL",lagumodels.get(position).getUrl());

        startActivity(intent);

    }
}