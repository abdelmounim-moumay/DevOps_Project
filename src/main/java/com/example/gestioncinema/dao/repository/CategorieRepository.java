package com.example.gestioncinema.dao.repository;

import com.example.gestioncinema.dao.entities.Categorie;
import com.example.gestioncinema.dao.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
@RepositoryRestController
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
