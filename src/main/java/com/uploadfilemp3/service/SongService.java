package com.uploadfilemp3.service;

import com.uploadfilemp3.model.Song;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class SongService implements ISongService {
    private final List<Song> songs;

    public SongService() {
        this.songs = new ArrayList<>();
    }

    @Override
    public List<Song> findAll() {
        return songs;
    }

    @Override
    public void save(Song song) {
        songs.add(song);
    }
}
