package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.model.Artista;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistaService {
    public Artista salvarArtistas(Artista artista);
    public List<Artista> listarTodosArtistas();
    public Artista listarArtistasPorId(Long id);
    public boolean deletarArtista(Long id);
    public Artista atualizarArtista(Artista artista, Long id);
    public Artista deletarLogicArtista(Artista artista, Long id);
    public Artista listarArtistasPorIdAtivos(Long id);
    public List<Artista> listarTodosArtistasAtivos();
}
