package com.example.musicplayer;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name, ArrayList<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }
}