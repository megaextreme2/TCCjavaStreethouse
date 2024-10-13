package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.model.Organizador;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrganizadorService {
    public Organizador salvarOrganizadores(Organizador organizador);
    public List<Organizador> listarTodosOrganizadores();
    public Organizador listarOrganizadoresPorId(Long id);
    public boolean deletarOrganizador(Long id);
    public Organizador atualizarOrganizador(Organizador organizador, Long id);
    public Organizador deletarLogicOrganizador(Organizador organizador, Long id);
    public Organizador listarOrganizadoresPorIdAtivos(Long id);
    public List<Organizador> listarOrganizadoresAtivos();
}
