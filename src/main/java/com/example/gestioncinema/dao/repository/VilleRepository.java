package com.example.gestioncinema.dao.repository;

import com.example.gestioncinema.dao.entities.Cinema;
import com.example.gestioncinema.dao.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestController
@CrossOrigin("*")
public interface VilleRepository extends JpaRepository<Ville,Long> {
}
