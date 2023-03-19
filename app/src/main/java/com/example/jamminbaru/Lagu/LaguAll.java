package com.example.jamminbaru.Lagu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
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
import com.example.jamminbaru.R;

public class LaguAll extends AppCompatActivity {
    private ImageView playpause;
    private TextView waktu,waktutotal;
    private SeekBar seekbar, seekBarVolume;
    AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagu_all);
        IDlaguall();
        mediaPlayer = new MediaPlayer();
        String judul = getIntent().getStringExtra("JUDUL");
        String band = getIntent().getStringExtra("BAND");
        int image = getIntent().getIntExtra("IMAGES",0);
        TextView judullagu = findViewById(R.id.maintitle);
        TextView bandlagu = findViewById(R.id.mainband);
        ImageView gambarlagu = findViewById(R.id.mainimageview);

        judullagu.setText(judul);
        bandlagu.setText(band);
        gambarlagu.setImageResource(image);
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

        seekBarVolume = findViewById(R.id.seekBarVolume);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBarVolume.setMax(maxVol);
        seekBarVolume.setProgress(currVol);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

    public void IDlaguall(){
        playpause = findViewById(R.id.playpause);
        waktu = findViewById(R.id.waktu);
        waktutotal = findViewById(R.id.waktutotal);
        seekbar = findViewById(R.id.seekbar);
    }
}