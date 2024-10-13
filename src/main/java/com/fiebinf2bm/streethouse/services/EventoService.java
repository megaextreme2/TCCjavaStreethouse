package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.model.Evento;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EventoService {
    public Evento salvarEventos(Evento evento);
    public List<Evento> listarTodosEventos();
    public Evento listarEventosPorId(Long id);
    public boolean deletarEvento(Long id);
    public Evento atualizarEvento(Evento evento, Long id);
    public Evento deletarLogicEvento(Evento evento, Long id);
    public Evento listarEventosPorIdAtivos(Long id);
    public List<Evento> listarTodosEventosAtivos();
}
