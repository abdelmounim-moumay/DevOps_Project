package com.example.gestioncinema.web;

import com.example.gestioncinema.dao.entities.Film;
import com.example.gestioncinema.dao.entities.Ticket;
import com.example.gestioncinema.dao.repository.FilmRepository;
import com.example.gestioncinema.dao.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping(path ="/imageFilm{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable (name = "id")Long id) throws Exception{
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        File file= new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
        Path path= Paths.get(file.toURI());
        return Files.readAllBytes(path);       //return fichier d'octer


    }
    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> ticketList= new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket->{
            //System.out.println(idTicket);
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClients());
            ticket.setReserver(true);
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;

    }

}
@Data
class TicketForm{
    private String nomClients;
    private List<Long> tickets = new ArrayList<>();
}