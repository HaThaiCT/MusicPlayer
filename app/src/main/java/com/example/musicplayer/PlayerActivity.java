package com.example.musicplayer;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


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

        // Nhận index từ MainActivity
        currentIndex = getIntent().getIntExtra("songIndex", 0);

        playSong(currentIndex);

        btnPlayPause.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnPlayPause.setText("▶");
            } else {
                mediaPlayer.start();
                btnPlayPause.setText("⏸");
            }
        });

        btnNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % songList.size();
            playSong(currentIndex);
        });

        btnPrev.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + songList.size()) % songList.size();
            playSong(currentIndex);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) mediaPlayer.seekTo(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        updateSeekBar = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 500);
                }
            }
        };
        handler.postDelayed(updateSeekBar, 500);
    }

    private void playSong(int index) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        Song song = songList.get(index);
        mediaPlayer = MediaPlayer.create(this, song.getResourceId());
        mediaPlayer.start();
        tvNowPlaying.setText("Đang phát: " + song.getTitle() + " - " + song.getArtist());
        btnPlayPause.setText("⏸");

        seekBar.setMax(mediaPlayer.getDuration());
        handler.post(updateSeekBar);

        mediaPlayer.setOnCompletionListener(mp -> {
            currentIndex = (currentIndex + 1) % songList.size();
            playSong(currentIndex);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateSeekBar);
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}