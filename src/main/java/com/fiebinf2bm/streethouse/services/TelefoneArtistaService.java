package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.model.TelefoneArtista;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TelefoneArtistaService {
    public TelefoneArtista salvarTelefonesArtista(TelefoneArtista telefoneArtista);
    public List<TelefoneArtista> listarTodosTelefonesArtista();
    public TelefoneArtista listarTelefonesArtistaPorId(Long id);
    public boolean deletarTelefoneArtista(Long id);
    public TelefoneArtista atualizarTelefoneArtista(TelefoneArtista telefoneArtista, Long id);
    public TelefoneArtista deletarLogicTelefoneArtista(TelefoneArtista telefoneArtista, Long id);
    public TelefoneArtista listarTelefonesArtivasPorIdAtivos(Long id);
    public List<TelefoneArtista> listarTelefonesArtistasAtivos();
}
