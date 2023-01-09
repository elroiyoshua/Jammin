package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class home2 extends AppCompatActivity implements recyclerviewinterface{



    ArrayList<lagumodel> lagumodels = new ArrayList<>();
    int[]  images ={R.drawable.cover1,R.drawable.cover2,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3,R.drawable.cover3};
    String[] url ={"https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Paramore_%20This%20Is%20Why%20%5BOFFICIAL%20VIDEO%5D.mp3?alt=media&token=38a37bde-40f8-4739-a96c-90f9e5d02a38",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Bury%20The%20Light%20-%20Approaching%20Storm%20Mix%20(Added%20rain%20_%20alternate%20intro).mp3?alt=media&token=704112b3-d922-47c9-b244-db5c1fdf4b13",
    "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Ariis%20-%20GOOFY%20PHONK.mp3?alt=media&token=9ed3ad8f-f1e8-47f8-b842-0c62a6793282",
    "shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6","shorturl.at/nzCH6"};

    recyclerviewinterface recyclerviewinterface;

    RecyclerView recyclerView;
    private SearchView searchView;
    MyAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchview);
        setuplagumodels();
        MyAdapter adapter =  new MyAdapter(this,lagumodels,this);
        searchAdapter = new MyAdapter(this  ,lagumodels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });
    }
    private void filterlist(String text){
        ArrayList<lagumodel> filteredlist = new ArrayList<>();
        if(text.isEmpty()){
            filteredlist =lagumodels;

        }else  {
            for(lagumodel lagu : lagumodels){
                if(lagu.getJudul().toLowerCase().contains(text.toLowerCase())){
                    filteredlist.add(lagu);
                }
            }
        }
        if (filteredlist.isEmpty()){
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
        }else{
            searchAdapter.setFilteredlist(filteredlist);
            searchAdapter.notifyDataSetChanged();
        }
    }
    private void setuplagumodels(){
        String[] judullagu = getResources().getStringArray(R.array.Judul);
        String[] bandlagu = getResources().getStringArray(R.array.Band);
        for (int i = 0;i < judullagu.length;i++){
            lagumodels.add(new lagumodel(judullagu[i],bandlagu[i],url[i],images[i]));
        }
    }

    @Override
    public void onItemClick(lagumodel lagu) {
        Intent intent = new Intent(home2.this, LaguAll.class);
        intent.putExtra("JUDUL",lagu.getJudul());
        intent.putExtra("BAND",lagu.getBand());
        intent.putExtra("IMAGES",lagu.getImage());
        intent.putExtra("URL",lagu.getUrl());

        startActivity(intent);

    }
}