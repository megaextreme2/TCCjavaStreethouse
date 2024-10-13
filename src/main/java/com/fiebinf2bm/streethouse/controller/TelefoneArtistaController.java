package com.fiebinf2bm.streethouse.controller;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.model.Organizador;
import com.fiebinf2bm.streethouse.model.TelefoneArtista;
import com.fiebinf2bm.streethouse.services.OrganizadorService;
import com.fiebinf2bm.streethouse.services.TelefoneArtistaService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/telefoneartista")
public class TelefoneArtistaController {
    private final TelefoneArtistaService telefoneArtistaService;

    public TelefoneArtistaController(TelefoneArtistaService telefoneArtistaService) {
        this.telefoneArtistaService = telefoneArtistaService;
    }
    @GetMapping("/telefoneartista")
    public ResponseEntity<List<TelefoneArtista>> listarTodosTelefonesArtista(){
        return ResponseEntity.ok().body(telefoneArtistaService.listarTodosTelefonesArtista());
    }
    @PostMapping("/telefoneartista")
    @Transactional
    public ResponseEntity<TelefoneArtista> salvarTelefonesArtista(@RequestBody TelefoneArtista telefoneArtista){
        telefoneArtista.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/telefoneartista").toUriString());
        return ResponseEntity.created(uri).body(telefoneArtistaService.salvarTelefonesArtista(telefoneArtista));
    }
    @GetMapping("/telefoneartista/{id}")
    public  ResponseEntity<TelefoneArtista> listarTelefonesArtistaPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(telefoneArtistaService.listarTelefonesArtistaPorId(Long.parseLong(id)));
        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @DeleteMapping("/telefoneartista/{id}")
    public ResponseEntity<Object> deletarTelefoneArtista(@PathVariable(value = "id") String id){
        try{
            if(telefoneArtistaService.deletarTelefoneArtista(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Telefone com o id " + id + " excluida com sucesso");}

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da admin com o id" + id);

    }
    @PutMapping("/telefoneartista/{id}")
    @Transactional
    public ResponseEntity<TelefoneArtista> atualizarTelefoneArtista(@RequestBody TelefoneArtista telefoneArtista, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(telefoneArtistaService.atualizarTelefoneArtista(telefoneArtista , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @PutMapping("/deletlogic/telefoneartista/{id}")
    @Transactional
    public ResponseEntity<TelefoneArtista> deletarLogicTelefoneArtista(@RequestBody TelefoneArtista telefoneArtista, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(telefoneArtistaService.deletarLogicTelefoneArtista(telefoneArtista, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}
