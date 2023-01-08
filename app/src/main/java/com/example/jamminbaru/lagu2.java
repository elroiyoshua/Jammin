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

import com.example.jamminbaru.ControllerUser.HomeUser;

public class lagu2 extends AppCompatActivity {
    private ImageView playpause;
    private TextView waktu,waktutotal;
    private SeekBar seekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagu2);
        playpause = findViewById(R.id.playpause);
        waktu = findViewById(R.id.waktu);
        waktutotal = findViewById(R.id.waktutotal);
        seekbar = findViewById(R.id.seekbar);
        mediaPlayer = new MediaPlayer();

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

        seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SeekBar seekBar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration()/100)*seekBar.getProgress();
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
        Intent intent =new Intent(this, HomeUser.class);
        startActivity(intent);
        mediaPlayer.pause();
        finish();

    }

    private void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/jamminapp-f2693.appspot.com/o/Bury%20The%20Light%20-%20Approaching%20Storm%20Mix%20(Added%20rain%20_%20alternate%20intro).mp3?alt=media&token=704112b3-d922-47c9-b244-db5c1fdf4b13");
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