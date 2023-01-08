package com.example.jamminbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LaguAll extends AppCompatActivity{
    private ImageView playpause,prev,next;
    private TextView waktu,waktutotal;
    private SeekBar seekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagu_all);
        playpause = findViewById(R.id.playpause);
        prev = (ImageView) findViewById(R.id.prev);
        next = (ImageView) findViewById(R.id.next);
        waktu = findViewById(R.id.waktu);
        waktutotal = findViewById(R.id.waktutotal);
        seekbar = findViewById(R.id.seekbar);
        mediaPlayer = new MediaPlayer();
//        String judul = getIntent().getStringExtra("JUDUL");
//        String band = getIntent().getStringExtra("BAND");
//
//        int image = getIntent().getIntExtra("IMAGES",0);
//        TextView judullagu = findViewById(R.id.maintitle);
//        TextView bandlagu = findViewById(R.id.mainband);
//        ImageView gambarlagu = findViewById(R.id.mainimageview);
//
//        judullagu.setText(judul);
//        bandlagu.setText(band);
//        gambarlagu.setImageResource(image);
        setResourcesSong();
        seekbar.setMax(100);
        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    playpause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                }else{
                    mediaPlayer.start();
                    playpause.setImageResource(R.drawable.ic_baseline_pause_24);
                    updateSeekBar();
                }
            }
        });
        prepareMediaPlayer();
//        savedwaktu = 0;

        seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (savedwaktu == 0){
//                    SeekBar seekBar = (SeekBar) view;
//                    int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
//                    mediaPlayer.seekTo(playPosition);
//                    waktu.setText(milisecondstotimer(mediaPlayer.getCurrentPosition()));
//                    return false;
//                }else{
//                    SeekBar seekBar = (SeekBar) view;
//                    int playPosition = (savedwaktu / 100) * seekBar.getProgress();
//                    mediaPlayer.seekTo(playPosition);
//                    waktu.setText(milisecondstotimer(mediaPlayer.getCurrentPosition()));
//                    return false;
//                }
                SeekBar seekBar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                waktu.setText(milisecondstotimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                seekbar.setSecondaryProgress(i);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                seekbar.setProgress(0);
                playpause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                waktu.setText(R.string.zero);
                waktutotal.setText(R.string.zero);
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });

    }
    public void GoHome(View v){

        Intent intent =new Intent(this,home2.class);
        startActivity(intent);
        mediaPlayer.pause();

        finish();

    }
    public  void setResourcesSong(){
        String judul = getIntent().getStringExtra("JUDUL");
        String band = getIntent().getStringExtra("BAND");

        int image = getIntent().getIntExtra("IMAGES",0);
        TextView judullagu = findViewById(R.id.maintitle);
        TextView bandlagu = findViewById(R.id.mainband);
        ImageView gambarlagu = findViewById(R.id.mainimageview);

        judullagu.setText(judul);
        bandlagu.setText(band);
        gambarlagu.setImageResource(image);
        next.setOnClickListener(v->playNext());
        prev.setOnClickListener(v->playPrev());
    }

    private  void   playNext(){
        if(MyMediaPlayer.currentindex == 9){
            return;
        }
        MyMediaPlayer.currentindex += 1;
        mediaPlayer.reset();
        setResourcesSong();
    }
    private void playPrev(){
        if(MyMediaPlayer.currentindex == 0){
            return;
        }
        MyMediaPlayer.currentindex -= 1;
        mediaPlayer.reset();
        setResourcesSong();
    }
    private void prepareMediaPlayer(){
        String url = getIntent().getStringExtra("URL");
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            waktutotal.setText(milisecondstotimer(mediaPlayer.getDuration()));
        }catch (Exception exception){
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private  Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long waktu1 = mediaPlayer.getCurrentPosition();
            waktu.setText(milisecondstotimer(waktu1));
        }
    };
    private void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            seekbar.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration())*100));
            handler.postDelayed(updater,1000);
        }

    }

    private String milisecondstotimer(long milisecond){
        String timerstring = "";
        String secondstring = "";

        int hour = (int)(milisecond/(1000*60*60));
        int minute = (int)(milisecond%(1000*60*60))/(1000*60);
        int second = (int)(milisecond%(1000*60*60))%(1000*60)/1000;

        if(hour > 0){
            timerstring = hour +":";
        }
        if(second <10){
            secondstring = "0" +second;
        }else{
            secondstring = ""+second;
        }
        timerstring  = timerstring + minute + ":" + secondstring;
        return  timerstring;
    }
}