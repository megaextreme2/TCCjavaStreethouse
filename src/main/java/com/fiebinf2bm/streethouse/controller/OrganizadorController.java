package com.fiebinf2bm.streethouse.controller;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.model.Evento;
import com.fiebinf2bm.streethouse.model.Organizador;
import com.fiebinf2bm.streethouse.services.EventoService;
import com.fiebinf2bm.streethouse.services.OrganizadorService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/organizador")
public class OrganizadorController {
    private final OrganizadorService organizadorService;

    public OrganizadorController(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }
    @GetMapping("/organizador")
    public ResponseEntity<List<Organizador>> listarTodosOrganizadores(){
        return ResponseEntity.ok().body(organizadorService.listarTodosOrganizadores());
    }
    @PostMapping("/organizador")
    @Transactional
    public ResponseEntity<Organizador> salvarOrganizadores(@RequestBody Organizador organizador){
        organizador.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/organizador").toUriString());
        return ResponseEntity.created(uri).body(organizadorService.salvarOrganizadores(organizador));
    }
    @GetMapping("/organizador/{id}")
    public  ResponseEntity<Organizador> listarOrganizadoresPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(organizadorService.listarOrganizadoresPorId(Long.parseLong(id)));
        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @DeleteMapping("/organizador/{id}")
    public ResponseEntity<Object> deletarOrganizador(@PathVariable(value = "id") String id){
        try{
            if(organizadorService.deletarOrganizador(Long.parseLong(id))) {
                return ResponseEntity.ok().body("organizador com o id " + id + " excluida com sucesso");}

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da admin com o id" + id);

    }
    @PutMapping("/organizador/{id}")
    @Transactional
    public ResponseEntity<Organizador> atualizarOrganizador(@RequestBody Organizador organizador, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(organizadorService.atualizarOrganizador(organizador , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @PutMapping("/deletlogic/organizador/{id}")
    @Transactional
    public ResponseEntity<Organizador> deletarLogicOrganizador(@RequestBody Organizador organizador, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(organizadorService.deletarLogicOrganizador(organizador, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}
