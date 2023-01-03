package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class home2 extends AppCompatActivity {
    String s1[],s2[];
    RecyclerView recyclerView;
    int images[] ={R.drawable.cover1,R.drawable.cover2,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        recyclerView = findViewById(R.id.recyclerview);

        s1 = getResources().getStringArray(R.array.Judul);
        s2 = getResources().getStringArray(R.array.Band);
        MyAdapter myAdapter = new MyAdapter(this,s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}