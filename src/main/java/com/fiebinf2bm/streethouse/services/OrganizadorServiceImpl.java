package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.exceptions.NotFound;
import com.fiebinf2bm.streethouse.model.Organizador;
import com.fiebinf2bm.streethouse.repository.OrganizadorRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class OrganizadorServiceImpl implements OrganizadorService {

    private final OrganizadorRepository organizadorRepository;

    public OrganizadorServiceImpl(OrganizadorRepository organizadorRepository ) {
        this.organizadorRepository = organizadorRepository;
    }


    @Override
    public Organizador salvarOrganizadores(Organizador organizador) {
        if (!organizador.validarOrganizador()) {
            throw new BadRequest(organizador.getMensagemErro());
        }
        return organizadorRepository.save(organizador);
    }

    @Override
    public List<Organizador> listarTodosOrganizadores() {
        return organizadorRepository.findAll();
    }

    @Override
    public Organizador listarOrganizadoresPorId(Long id) {
        try {
            return organizadorRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Organizador com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarOrganizador(Long id) {
        if (organizadorRepository.existsById(id)) {
            organizadorRepository.deleteById(id);
        } else {
            throw new NotFound("Organizador com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Organizador atualizarOrganizador(Organizador organizador, Long id) {
        try {
            if (!organizador.validarOrganizador()) {
                throw new BadRequest(organizador.getMensagemErro());
            }
            Organizador organizadorBD = organizadorRepository.findById(id).get();
            organizadorBD.setNome_organizador(organizador.getNome_organizador());
            return organizadorRepository.save(organizadorBD); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Organizador com o id " + id + " não encontrado");
        }
    }

    @Override
    public Organizador deletarLogicOrganizador(Organizador organizador, Long id) {
        try {
            if (!organizador.validarOrganizador()){
                throw new BadRequest(organizador.getMensagemErro());
            }
            Organizador organizadorBD = organizadorRepository.findById(id).get();
            organizadorBD.setCodStatus(organizador.isCodStatus());
            return organizadorRepository.save(organizadorBD);
        }catch (Exception ex){
            throw new NotFound("Organizador com o id " + id + " não encontrado");
        }
    }

    @Override
    public Organizador listarOrganizadoresPorIdAtivos(Long id) {
        Organizador organizador = organizadorRepository.listarOrganizadoresPorIdAtivos(id);
        if(organizador == null){
            throw new NotFound("Organizador com o id " + id + " não encontrado");
        }
        return organizador;
    }

    @Override
    public List<Organizador> listarOrganizadoresAtivos() {
        return organizadorRepository.listarOrganizadoresAtivos();
    }
}
