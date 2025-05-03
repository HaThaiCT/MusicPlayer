package com.example.musicplayer;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String artist;
    private int resourceId; // ID bài nhạc trong thư mục raw

    public Song(String title, String artist, int resourceId) {
        this.title = title;
        this.artist = artist;
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getResourceId() {
        return resourceId;
    }
}