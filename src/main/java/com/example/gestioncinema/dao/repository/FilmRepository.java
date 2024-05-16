package com.example.gestioncinema.dao.repository;

import com.example.gestioncinema.dao.entities.Cinema;
import com.example.gestioncinema.dao.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface FilmRepository extends JpaRepository<Film,Long> {
    List<Film> findByTitreContainingIgnoreCase(String titre);
}
