package com.example.gestioncinema.dao.repository;

import com.example.gestioncinema.dao.entities.Cinema;
import com.example.gestioncinema.dao.entities.Place;
import com.example.gestioncinema.dao.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;
import java.util.Optional;

@RepositoryRestController
public interface PlaceRepository extends JpaRepository<Place,Long> {
  List<Place> findPlaceBySalle(Salle salle);
}
