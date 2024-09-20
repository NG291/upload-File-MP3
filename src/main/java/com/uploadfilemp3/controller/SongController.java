package com.uploadfilemp3.controller;

import com.uploadfilemp3.model.Song;
import com.uploadfilemp3.model.SongForm;
import com.uploadfilemp3.service.ISongService;
import com.uploadfilemp3.service.SongService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private ISongService songService = new SongService();
    @GetMapping("/list")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Song> songs = songService.findAll();
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView ShowForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("songForm", new SongForm());
        return modelAndView;
    }
    @Value("${file-upload}")
    private String fileUpload;
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute SongForm songForm) throws IOException {
        MultipartFile multipartFile = songForm.getPath();
        String fileName = multipartFile.getOriginalFilename();
        FileCopyUtils.copy(songForm.getPath().getBytes(), new File(fileUpload + fileName));
        Song song = new Song(songForm.getId(), songForm.getName(),songForm.getSinger(),songForm.getCategory(),fileName);
        songService.save(song);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("song",songForm);
        modelAndView.addObject("message", "Created new product successfully !");
        return modelAndView;
    }

}
