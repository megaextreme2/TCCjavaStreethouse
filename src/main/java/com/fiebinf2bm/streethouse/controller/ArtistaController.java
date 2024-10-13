package com.fiebinf2bm.streethouse.controller;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.model.Artista;
import com.fiebinf2bm.streethouse.services.ArtistaService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Artista")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }
    @GetMapping("/artista")
    public ResponseEntity<List<Artista>> listarTodosArtistas(){
        return ResponseEntity.ok().body(artistaService.listarTodosArtistas());
    }
    @PostMapping("/artista")
    @Transactional
    public ResponseEntity<Artista> salvarArtistas(@RequestBody Artista artista){
        artista.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/artista").toUriString());
        return ResponseEntity.created(uri).body(artistaService.salvarArtistas(artista));
    }
    @GetMapping("/artista/{id}")
    public  ResponseEntity<Artista> listarArtistasPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(artistaService.listarArtistasPorId(Long.parseLong(id)));
        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @DeleteMapping("/artista/{id}")
    public ResponseEntity<Object> deletarArtista(@PathVariable(value = "id") String id){
        try{
            if(artistaService.deletarArtista(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Artista com o id " + id + " excluida com sucesso");}

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da admin com o id" + id);

    }
    @PutMapping("/artista/{id}")
    @Transactional
    public ResponseEntity<Artista> atualizarArtista(@RequestBody Artista artista, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(artistaService.atualizarArtista(artista , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @PutMapping("/deletlogic/artista/{id}")
    @Transactional
    public ResponseEntity<Artista> deletarLogicArtista(@RequestBody Artista artista, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(artistaService.deletarLogicArtista(artista, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}