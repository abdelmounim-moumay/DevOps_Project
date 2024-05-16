package com.example.gestioncinema.web;

import com.example.gestioncinema.dao.entities.Film;
import com.example.gestioncinema.dao.entities.Ticket;
import com.example.gestioncinema.dao.entities.Ville;
import com.example.gestioncinema.dao.repository.FilmRepository;
import com.example.gestioncinema.dao.repository.TicketRepository;
import com.example.gestioncinema.dao.repository.VilleRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VilleRepository villeRepository;
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
        model.addAttribute("ListFilms",films);
        return "index";
    }
    @GetMapping("/Reservation{id}")
    public String Reservation(Model model, @PathVariable (name = "id")Long id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty())
            return "/error";
        List<Ville>villes = villeRepository.findAll();
        model.addAttribute("ville", villes);
        model.addAttribute("Film",film.get());
        return "reservation";
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){


            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClients());
            ticket.setReserver(true);
            ticketRepository.save(ticket);




    }

}
@Data
class TicketForm{
    private String nomClients;
    private List<Long> tickets = new ArrayList<>();
}