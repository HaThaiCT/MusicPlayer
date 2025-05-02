package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSongs;
    ArrayList<Song> songs;
    SongAdapter adapter;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSongs = findViewById(R.id.lvSongs);
        songs = new ArrayList<>();
        songs.add(new Song("Nơi này có anh", "Sơn Tùng MTP", R.raw.noinaycoanh));
        songs.add(new Song("Trời giấu trời mang đi", "Amee", R.raw.troigiautroimangdi));
        songs.add(new Song("Đừng làm trái tim anh đau", "Sơn Tùng MTP", R.raw.dunglamtraitimanhdau));
        songs.add(new Song("Sao anh chưa về nhà", "Amee", R.raw.saoanhchuavenha));
        songs.add(new Song("Nếu lúc đó", "Tlinh", R.raw.neulucdo));
        songs.add(new Song("Em mới là người yêu anh", "Min", R.raw.emmoilanguoiyeuanh));
        adapter = new SongAdapter(this, songs);
        lvSongs.setAdapter(adapter);

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playSong(position);
            }
        });
    }
    private void playSong(int position) {
        // Neu dang phat bai cu thi dung lai
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        // Tao mediaPlayer voi bai hat duoc chon
        Song selectedSong = songs.get(position);
        mediaPlayer = MediaPlayer.create(MainActivity.this, selectedSong.getResourceId());
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Don dep tai nguyen
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}