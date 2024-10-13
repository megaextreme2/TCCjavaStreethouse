package com.fiebinf2bm.streethouse.controller;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.model.Evento;
import com.fiebinf2bm.streethouse.services.EventoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }
    @GetMapping("/evento")
    public ResponseEntity<List<Evento>> listarTodosEventos(){
        return ResponseEntity.ok().body(eventoService.listarTodosEventos());
    }
    @PostMapping("/evento")
    @Transactional
    public ResponseEntity<Evento> salvarEventos(@RequestBody Evento evento){
        evento.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/evento").toUriString());
        return ResponseEntity.created(uri).body(eventoService.salvarEventos(evento));
    }
    @GetMapping("/evento/{id}")
    public  ResponseEntity<Evento> listarEventosPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(eventoService.listarEventosPorId(Long.parseLong(id)));
        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @DeleteMapping("/evento/{id}")
    public ResponseEntity<Object> deletarEvento(@PathVariable(value = "id") String id){
        try{
            if(eventoService.deletarEvento(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Evento com o id " + id + " excluida com sucesso");}

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da admin com o id" + id);

    }
    @PutMapping("/evento/{id}")
    @Transactional
    public ResponseEntity<Evento> atualizarEvento(@RequestBody Evento evento, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(eventoService.atualizarEvento(evento , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @PutMapping("/deletlogic/evento/{id}")
    @Transactional
    public ResponseEntity<Evento> deletarLogicEvento(@RequestBody Evento evento, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(eventoService.deletarLogicEvento(evento, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}
