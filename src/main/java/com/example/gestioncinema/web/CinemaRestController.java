package com.example.gestioncinema.web;

import com.example.gestioncinema.dao.entities.*;
import com.example.gestioncinema.dao.repository.*;
import com.example.gestioncinema.service.CinemaService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    Random random = new Random();

    @GetMapping(path ="/imageFilm{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public String image(@PathVariable (name = "id")Long id) throws Exception{
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        File file= new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
        Path path= Paths.get(file.toURI());
        return "";       //return fichier d'octer


    }
    @GetMapping("/index")
    public String ListFilms(Model model) {
        List<Film> films = filmRepository.findAll();
        List<Categorie> categories = categorieRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("ListFilms",films);
        return "index";
    }

    @GetMapping("/search")
    public String searchFilms(@RequestParam("query") String query, Model model) {
        List<Film> films = cinemaService.searchFilms(query);
        model.addAttribute("ListFilms", films);
        return "index"; // ou la vue appropriée pour afficher les résultats
    }
    @GetMapping("/Reservation/film={id}")
    public String Reservation(Model model, @PathVariable (name = "id")Long id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty())
            return "/error";
        List<Ville>villes = villeRepository.findAll();
        List< Cinema> cinemaList = cinemaRepository.findAll();
        List<Salle> salles = salleRepository.findAll();
        model.addAttribute("salles", salles);
        model.addAttribute("cinemas",cinemaList);
        model.addAttribute("ville", villes);
        model.addAttribute("Film",film.get());
        int prix = random.nextBoolean() ? 70 : 80; // Choisit 70 ou 80 aléatoirement
        model.addAttribute("prix", prix);
        return "reservation";
    }


    @PostMapping("/Reservation/film={filmId}")
    @Transactional
    public String payerTickets(@RequestParam(name="villeId") Long villeId,
                               @PathVariable(name="filmId") Long filmId,
                               @RequestParam(name="cinemaId") Long cinemaId,
                               @RequestParam(name="salleId") Long salleId,
                               @RequestParam(name="name") String name) {
        Optional<Ville> ville = villeRepository.findById(villeId);
        Optional<Film> film = filmRepository.findById(filmId);
        Optional<Cinema> cinema = cinemaRepository.findById(cinemaId);
        Optional<Salle> salle = salleRepository.findById(salleId);

        if (film.isEmpty() || ville.isEmpty() || salle.isEmpty() || cinema.isEmpty()) {
            return "redirect:/index";
        }

        List<Place> places = placeRepository.findPlaceBySalle(salle.get());
        Random random = new Random();
        int prix = random.nextBoolean() ? 70 : 80; // Choisit 70 ou 80 aléatoirement
        for (Place place : places) {
            if (!place.isReserve()) {
                Ticket ticket = new Ticket();
                ticket.setPrix(prix);
                ticket.setNomClient(name);
                ticket.setReserver(true);
                ticket.setPlace(place);
                ticketRepository.save(ticket);
                return "redirect:/index";
            }
        }
        System.out.println("Aucune place disponible");
        return "redirect:/index";
    }
    @GetMapping("/horaires")
    public String afficherHoraires(Model model) {
        // Récupérer la liste des séances depuis la base de données
        List<Seance> seances = seanceRepository.findAll();

        // Ajouter la liste des séances à l'objet Model pour l'affichage dans la vue
        model.addAttribute("seances", seances);

        // Retourner le nom de la vue
        return "horaires";
    }





}

@Data
class TicketForm{
    private String nomClients;
    private List<Long> tickets = new ArrayList<>();
}