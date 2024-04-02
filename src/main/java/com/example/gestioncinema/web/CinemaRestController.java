package com.example.gestioncinema.web;

import com.example.gestioncinema.dao.entities.Film;
import com.example.gestioncinema.dao.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @GetMapping(path ="/imageFilm{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable (name = "id")Long id) throws Exception{
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        File file= new File(System.getProperty("user.home")+"/cinema/images"+photoName);
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);       //return fichier d'octer


    }

}
