package com.example.musicplayer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreatePlaylistActivity extends AppCompatActivity {

    EditText edtPlaylistName;
    ListView lvSongs;
    Button btnSave;
    ArrayList<Song> allSongs;
    ArrayList<Playlist> existingPlaylists;
    SharedPreferences sharedPreferences;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);

        edtPlaylistName = findViewById(R.id.edtPlaylistName);
        lvSongs = findViewById(R.id.lvSongsToAdd);
        btnSave = findViewById(R.id.btnSavePlaylist);

        allSongs = new ArrayList<>();
        allSongs = MainActivity.songs;

        SongSelectAdapter songAdapter = new SongSelectAdapter(this, allSongs);
        lvSongs.setAdapter(songAdapter);

        gson = new Gson();
        sharedPreferences = getSharedPreferences("MY_APP", MODE_PRIVATE);
        existingPlaylists = loadPlaylists();

        btnSave.setOnClickListener(v -> {
            String name = edtPlaylistName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên playlist", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<Song> selectedSongs = new ArrayList<>();
            selectedSongs = songAdapter.getSelectedSongs();


            if (selectedSongs.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất 1 bài hát", Toast.LENGTH_SHORT).show();
                return;
            }

            existingPlaylists.add(new Playlist(name, selectedSongs));
            savePlaylists();
            Toast.makeText(this, "Đã lưu playlist", Toast.LENGTH_SHORT).show();
            finish(); // quay lại PlaylistActivity
        });
    }

    private void savePlaylists() {
        String json = gson.toJson(existingPlaylists);
        sharedPreferences.edit().putString("playlists", json).apply();
    }

    private ArrayList<Playlist> loadPlaylists() {
        String json = sharedPreferences.getString("playlists", null);
        Type type = new TypeToken<ArrayList<Playlist>>() {}.getType();
        return (json != null) ? gson.fromJson(json, type) : new ArrayList<>();
    }
}

