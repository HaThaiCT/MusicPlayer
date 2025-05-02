package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.logging.Handler;

public class PlayerActivity extends AppCompatActivity {
    TextView tvNowPlaying;
    SeekBar seekBar;
    Button btnPlayPause, btnNext, btnPrev;

    static MediaPlayer mediaPlayer;
    static ArrayList<Song> songList;
    static int currentIndex = 0;

    Handler handler = new Handler();
    Runnable updateSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        tvNowPlaying = findViewById(R.id.tvNowPlaying);
        seekBar = findViewById(R.id.seekBar);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        currentIndex = getIntent().getByteExtra("songIndex", 0);
        playSong(currentIndex);
    }

    btnPlayPause.setOnClickListener(v -> {

    });

}
