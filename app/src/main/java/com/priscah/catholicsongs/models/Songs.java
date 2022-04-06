package com.priscah.catholicsongs.models;

import java.util.Objects;

public class Songs {
    private String song;
    private String album;
    private String choir;
    private String lyrics;
    private int id;

    public Songs(String song, String album, String choir, String lyrics, int id) {
        this.song = song;
        this.album = album;
        this.choir = choir;
        this.lyrics = lyrics;
        this.id = id;

    }
    public String getsong() {
        return song;
    }

    public void setsong(String song) {
        this.song = song;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getChoir() {
        return choir;
    }

    public void setChoir(String choir) {
        this.choir = choir;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Songs)) return false;
        Songs songs = (Songs) o;
        return id == songs.id &&
                Objects.equals(song, songs.song) &&
                Objects.equals(album, songs.album) &&
                Objects.equals(choir, songs.choir) &&
                Objects.equals(lyrics, songs.lyrics);

    }

    @Override
    public int hashCode() {
        return Objects.hash(song, album, choir, lyrics, id);
    }
}
