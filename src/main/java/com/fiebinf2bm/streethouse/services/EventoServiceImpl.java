package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.exceptions.NotFound;
import com.fiebinf2bm.streethouse.model.Evento;
import com.fiebinf2bm.streethouse.repository.EventoRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository ) {
        this.eventoRepository = eventoRepository;
    }


    @Override
    public Evento salvarEventos(Evento evento) {
        if (!evento.validarEvento()) {
            throw new BadRequest(evento.getMensagemErro());
        }
        return eventoRepository.save(evento);
    }

    @Override
    public List<Evento> listarTodosEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento listarEventosPorId(Long id) {
        try {
            return eventoRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Evento com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarEvento(Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
        } else {
            throw new NotFound("Evento com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Evento atualizarEvento(Evento evento, Long id) {
        try {
            if (!evento.validarEvento()) {
                throw new BadRequest(evento.getMensagemErro());
            }
            Evento eventoBD = eventoRepository.findById(id).get();
            eventoBD.setEndereco(evento.getEndereco());
            return eventoRepository.save(eventoBD); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Evento com o id " + id + " não encontrado");
        }
    }

    @Override
    public Evento deletarLogicEvento(Evento evento, Long id) {
        try {
            if (!evento.validarEvento()){
                throw new BadRequest(evento.getMensagemErro());
            }
            Evento eventoBD = eventoRepository.findById(id).get();
            eventoBD.setCodStatus(evento.isCodStatus());
            return eventoRepository.save(eventoBD);
        }catch (Exception ex){
            throw new NotFound("Evento com o id " + id + " não encontrado");
        }
    }

    @Override
    public Evento listarEventosPorIdAtivos(Long id) {
        Evento evento = eventoRepository.listarEventosPorIdAtivos(id);
        if(evento == null){
            throw new NotFound("Evento com o id " + id + " não encontrado");
        }
        return evento;
    }

    @Override
    public List<Evento> listarTodosEventosAtivos() {
        return eventoRepository.listarTodosEventosAtivos();
    }
}
