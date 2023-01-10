package com.example.jamminbaru.ControllerUser;

import static com.example.jamminbaru.user.LoginUser.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jamminbaru.Lagu.LaguAll;
import com.example.jamminbaru.Lagu.LaguAdapter;
import com.example.jamminbaru.R;
import com.example.jamminbaru.Lagu.lagumodel;
import com.example.jamminbaru.Lagu.onClickLagu;

import java.util.ArrayList;
import java.util.Locale;

public class HomeUser extends AppCompatActivity implements onClickLagu {

    ArrayList<lagumodel> lagumodels = new ArrayList<>();
    int[]  images ={R.drawable.cover1,R.drawable.cover2,R.drawable.cover3,R.drawable.cover4,R.drawable.cover5,R.drawable.cover6,R.drawable.cover7,R.drawable.cover8,R.drawable.cover9,R.drawable.cover10
    ,R.drawable.cover11,R.drawable.cover12,R.drawable.cover13,R.drawable.cover14,R.drawable.cover15,R.drawable.cover16,R.drawable.cover17,R.drawable.cover18,R.drawable.cover19,R.drawable.cover20,R.drawable.cover21,R.drawable.cover23};
    String[] url ={"https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Paramore_%20This%20Is%20Why%20%5BOFFICIAL%20VIDEO%5D.mp3?alt=media&token=38a37bde-40f8-4739-a96c-90f9e5d02a38",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Bury%20The%20Light%20-%20Approaching%20Storm%20Mix%20(Added%20rain%20_%20alternate%20intro).mp3?alt=media&token=704112b3-d922-47c9-b244-db5c1fdf4b13",
    "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Ariis%20-%20GOOFY%20PHONK.mp3?alt=media&token=9ed3ad8f-f1e8-47f8-b842-0c62a6793282"
            ,"https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Christina%20Perri%20-%20A%20Thousand%20Years%20(Lyrics).mp3?alt=media&token=0b7bb62a-67b6-413a-96c6-9e4373e48455",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Coldplay%20-%20Viva%20La%20Vida%20(Lyrics).mp3?alt=media&token=dd524832-86dd-48d6-af6c-0aab4146b594",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Corpse%20-%20Agoraphobic.mp3?alt=media&token=b317578a-fc59-4fe6-85c4-47f5d7d54587",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Darren%20Korb%20-%20Good%20Riddance.mp3?alt=media&token=7333c33a-848b-4937-8ada-7e8b20e340b4",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/DECO27%20-%20Ghost%20Rule.mp3?alt=media&token=72e6b4f8-8b54-4877-9d69-743a3f3afbb3",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Diego%20Luna%20-%20I%20Love%20You%20Too%20Much.mp3?alt=media&token=0285e467-c77d-4808-a1c3-100d03bfdb8e",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Imagine%20Dragons%20-%20Enemy.mp3?alt=media&token=9ae10124-4cf2-4d14-a41f-d536c81e743e",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Joji%20-%20Glimpse%20Of%20Us.mp3?alt=media&token=f7991237-fbf3-42f8-9d31-d9779ab185e1",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/LilyPichu%20-%20Dreamy%20Night.mp3?alt=media&token=679637a3-c830-4c48-8e61-f741ee67933c",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/My%20Chemical%20Romance%20-%20I%20Don't%20Love%20You.mp3?alt=media&token=06800c9e-7804-47c1-a0c7-944edfbed8d6",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/PinocchioP%20-%20I'm%20glad%20you're%20evil%20too.mp3?alt=media&token=1e3b184a-c1b6-4f37-853f-695b176bb1ec",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Radiohead%20-%20Creep.mp3?alt=media&token=7ba0c5a9-dc6f-40a4-b973-7c806289e0cd",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Rex%20Orange%20County%20-%20THE%20SHADE%20(Lyrics).mp3?alt=media&token=2dfb8ad0-1194-4ebc-9b71-28b4db6c4c8b",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Rosa%20Linn%20-%20SNAP%20(Lyrics).mp3?alt=media&token=7fabd670-5215-4d99-9f8b-a02a494dda4a",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Seafret%20-%20Atlantis%20.mp3?alt=media&token=02fcb41f-e8c9-4963-b9e6-15ccdb5376d6",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Stephen%20Sanchez%20-%20Until%20I%20Found%20You%20(Lyrics).mp3?alt=media&token=cabf8070-1f93-4bb1-818e-1af12fbee47e",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Taylor%20Swift%20-%20Anti-Hero%20.mp3?alt=media&token=88f89ca5-10db-4468-b74b-45e5ea4f71d9",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/wowaka%20-%20Unhappy%20Refrain%20ft.%20Hatsune%20Miku.mp3?alt=media&token=0e127e5e-62be-4eb9-86c4-ee520b57e5b4",
            "https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Kobo%20KanaeruOfficial%E9%AB%AD%E7%94%B7dism%20%20Pretender%20WEKA%20remix.mp3?alt=media&token=1c4b4b11-2789-4154-a7cc-d2aca77d5910"};

    onClickLagu onClickLagu;

    ImageButton rec1, rec2, rec3;
    RecyclerView recyclerView;
    private SearchView searchView;
    LaguAdapter searchAdapter;
    TextView usernameTag;
    final private String displayUsername = user.getPhoneTxt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        IDallhome();
        setuplagumodels();
        LaguAdapter adapter =  new LaguAdapter(this,lagumodels,this);
        searchAdapter = new LaguAdapter(this  ,lagumodels,this);
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

        rec1 = (ImageButton) findViewById(R.id.imageButton2);
        rec2 = (ImageButton) findViewById(R.id.imageButton3);
        rec3 = (ImageButton) findViewById(R.id.imageButton4);


        rec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeUser.this, LaguAll.class);
                intent.putExtra("JUDUL", "This Is Why");
                intent.putExtra("BAND", "Paramore");
                intent.putExtra("IMAGES", R.drawable.cover1);
                intent.putExtra("URL","https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Paramore_%20This%20Is%20Why%20%5BOFFICIAL%20VIDEO%5D.mp3?alt=media&token=38a37bde-40f8-4739-a96c-90f9e5d02a38");
                startActivity(intent);
            }
        });

        rec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeUser.this, LaguAll.class);
                intent.putExtra("JUDUL", "Approaching Storm");
                intent.putExtra("BAND", "Burry The Light");
                intent.putExtra("IMAGES", R.drawable.cover2);
                intent.putExtra("URL","https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Bury%20The%20Light%20-%20Approaching%20Storm%20Mix%20(Added%20rain%20_%20alternate%20intro).mp3?alt=media&token=704112b3-d922-47c9-b244-db5c1fdf4b13");
                startActivity(intent);
            }
        });

        rec3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeUser.this, LaguAll.class);
                intent.putExtra("JUDUL", "Goofy Phonk");
                intent.putExtra("BAND", "Ariis");
                intent.putExtra("IMAGES", R.drawable.cover3);
                intent.putExtra("URL","https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Ariis%20-%20GOOFY%20PHONK.mp3?alt=media&token=9ed3ad8f-f1e8-47f8-b842-0c62a6793282");
                startActivity(intent);
            }
        });
    }
    private void filterlist(String text){
        ArrayList<lagumodel> filteredlist = new ArrayList<>();
        if(text.isEmpty()){
            filteredlist =lagumodels;

        }else  {
            for(lagumodel lagu : lagumodels){
                if(lagu.getJudul().toLowerCase().contains(text.toLowerCase())||(lagu.getBand().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))){
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
        Intent intent = new Intent(HomeUser.this, LaguAll.class);
        intent.putExtra("JUDUL",lagu.getJudul());
        intent.putExtra("BAND",lagu.getBand());
        intent.putExtra("IMAGES",lagu.getImage());
        intent.putExtra("URL",lagu.getUrl());

        startActivity(intent);

    }
    public void IDallhome(){
        usernameTag = findViewById(R.id.usernamedisplayTagHome);
        usernameTag.setText(displayUsername);
        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchview);
    }

    public void GoProfile(View v){
        Intent intent =new Intent(this, ProfileUser.class);
        startActivity(intent);
        finish();

    }
}