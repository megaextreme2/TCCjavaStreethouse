package com.fiebinf2bm.streethouse.controller;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.model.TelefoneOrganizador;
import com.fiebinf2bm.streethouse.services.TelefoneOrganizadorService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/telefoneOrganizador")
public class TelefoneOrganizadorController {
    private final TelefoneOrganizadorService telefoneOrganizadorService;

    public TelefoneOrganizadorController(TelefoneOrganizadorService telefoneOrganizadorService) {
        this.telefoneOrganizadorService = telefoneOrganizadorService;
    }
    @GetMapping("/telefoneOrganizador")
    public ResponseEntity<List<TelefoneOrganizador>> listarTodosTelefonesOrganizador(){
        return ResponseEntity.ok().body(telefoneOrganizadorService.listarTodosTelefonesOrganizador());
    }
    @PostMapping("/telefoneOrganizador")
    @Transactional
    public ResponseEntity<TelefoneOrganizador> salvarTelefonesOrganizador(@RequestBody TelefoneOrganizador telefoneOrganizador){
        telefoneOrganizador.setCodStatus(true);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/telefoneOrganizador").toUriString());
        return ResponseEntity.created(uri).body(telefoneOrganizadorService.salvarTelefonesOrganizador(telefoneOrganizador));
    }
    @GetMapping("/telefoneOrganizador/{id}")
    public  ResponseEntity<TelefoneOrganizador> listarTelefonesOrganizadorPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(telefoneOrganizadorService.listarTelefonesOrganizadorPorId(Long.parseLong(id)));
        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @DeleteMapping("/telefoneOrganizador/{id}")
    public ResponseEntity<Object> deletarTelefoneOrganizador(@PathVariable(value = "id") String id){
        try{
            if(telefoneOrganizadorService.deletarTelefoneOrganizador(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Telefone com o id " + id + " excluida com sucesso");}

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da admin com o id" + id);

    }
    @PutMapping("/telefoneOrganizador/{id}")
    @Transactional
    public ResponseEntity<TelefoneOrganizador> atualizarTelefoneOrganizador(@RequestBody TelefoneOrganizador telefoneOrganizador, @PathVariable(value = "id") String id){

        try{
            return ResponseEntity.ok().body(telefoneOrganizadorService.atualizarTelefoneOrganizador(telefoneOrganizador , Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
    @PutMapping("/deletlogic/telefoneOrganizador/{id}")
    @Transactional
    public ResponseEntity<TelefoneOrganizador> deletarLogicTelefoneOrganizador(@RequestBody TelefoneOrganizador telefoneOrganizador, @PathVariable(value = "id") String id){

        try {
            return ResponseEntity.ok().body(telefoneOrganizadorService.deletarLogicTelefoneOrganizador(telefoneOrganizador, Long.parseLong(id)));

        }catch (NumberFormatException ex){
            throw new BadRequest("'"+ id + "' Não é um número inteiro válido. Por favor, forneça um valor inteiro, como 10");
        }
    }
}
