package com.uploadfilemp3.service;

import com.uploadfilemp3.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();
    void save(Song song);
}
