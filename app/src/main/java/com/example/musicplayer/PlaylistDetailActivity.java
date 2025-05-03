package com.example.musicplayer;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PlaylistDetailActivity extends AppCompatActivity {

    TextView tvTitle;
    ListView lvSongs;
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_detail);

        tvTitle = findViewById(R.id.tvPlaylistTitle);
        lvSongs = findViewById(R.id.lvSongsInPlaylist);

        // Nhận dữ liệu từ PlaylistActivity
        String title = getIntent().getStringExtra("playlistName");
        songs = (ArrayList<Song>) getIntent().getSerializableExtra("playlistSongs");

        tvTitle.setText("Playlist: " + title);

        SongAdapter songAdapter = new SongAdapter(this, songs);
        lvSongs.setAdapter(songAdapter);

        lvSongs.setOnItemClickListener((parent, view, position, id) -> {
            PlayerActivity.songList = songs;
            Intent intent = new Intent(PlaylistDetailActivity.this, PlayerActivity.class);
            intent.putExtra("songIndex", position);
            startActivity(intent);
        });
    }
}