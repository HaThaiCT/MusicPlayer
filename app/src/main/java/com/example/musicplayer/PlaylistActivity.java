package com.example.musicplayer;

import static androidx.core.app.ActivityCompat.recreate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {

    ListView lvPlaylists;
    Button btnCreate;
    ArrayList<Playlist> playlists;
    SharedPreferences sharedPreferences;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        lvPlaylists = findViewById(R.id.lvPlaylists);
        btnCreate = findViewById(R.id.btnCreatePlaylist);
        sharedPreferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        gson = new Gson();

        playlists = loadPlaylists();
        PlaylistAdapter adapter = new PlaylistAdapter(this, playlists);
        lvPlaylists.setAdapter(adapter);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaylistActivity.this, CreatePlaylistActivity.class);
                startActivity(intent);
                savePlaylists();
                Toast.makeText(PlaylistActivity.this, "Đã tạo playlist", Toast.LENGTH_SHORT).show();
                recreate(); // refresh màn hình
            }
        });

        lvPlaylists.setOnItemClickListener((parent, view, position, id) -> {
            Playlist selected = playlists.get(position);
            Intent intent = new Intent(PlaylistActivity.this, PlaylistDetailActivity.class);
            intent.putExtra("playlistName", selected.getName());
            intent.putExtra("playlistSongs", selected.getSongs());
            startActivity(intent);
        });
    }



    private void savePlaylists() {
        String json = gson.toJson(playlists);
        sharedPreferences.edit().putString("playlists", json).apply();
    }

    private ArrayList<Playlist> loadPlaylists() {
        String json = sharedPreferences.getString("playlists", null);
        Type type = new TypeToken<ArrayList<Playlist>>() {}.getType();
        return (json != null) ? gson.fromJson(json, type) : new ArrayList<>();
    }
}
