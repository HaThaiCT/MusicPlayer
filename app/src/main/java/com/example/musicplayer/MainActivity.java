package com.example.musicplayer;

import android.content.Intent;
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
                PlayerActivity.songList = songs;
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra("songIndex", position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


