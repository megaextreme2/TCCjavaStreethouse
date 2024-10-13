package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.exceptions.NotFound;
import com.fiebinf2bm.streethouse.model.Artista;
import com.fiebinf2bm.streethouse.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaServiceImpl implements ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaServiceImpl(ArtistaRepository artistaRepository ) {
        this.artistaRepository = artistaRepository;
    }


    @Override
    public Artista salvarArtistas(Artista artista) {
        if (!artista.validarArtista()) {
            throw new BadRequest(artista.getMensagemErro());
        }
        return artistaRepository.save(artista);
    }

    @Override
    public List<Artista> listarTodosArtistas() {
        return artistaRepository.findAll();
    }

    @Override
    public Artista listarArtistasPorId(Long id) {
        try {
            return artistaRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Artista com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarArtista(Long id) {
        if (artistaRepository.existsById(id)) {
            artistaRepository.deleteById(id);
        } else {
            throw new NotFound("Artista com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public Artista atualizarArtista(Artista artista, Long id) {
        try {
            if (!artista.validarArtista()) {
                throw new BadRequest(artista.getMensagemErro());
            }
            Artista artistaeBD = artistaRepository.findById(id).get();
            artistaeBD.setNome_artista(artista.getNome_artista());
            return artistaRepository.save(artistaeBD); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Artista com o id " + id + " não encontrado");
        }
    }

    @Override
    public Artista deletarLogicArtista(Artista artista, Long id) {
        try {
            if (!artista.validarArtista()){
                throw new BadRequest(artista.getMensagemErro());
            }
            Artista artistaBd = artistaRepository.findById(id).get();
            artistaBd.setCodStatus(artista.isCodStatus());
            return artistaRepository.save(artistaBd);
        }catch (Exception ex){
            throw new NotFound("Artista com o id " + id + " não encontrado");
        }
    }

    @Override
    public Artista listarArtistasPorIdAtivos(Long id) {
        Artista artista = artistaRepository.listarArtistasPorIdAtivos(id);
        if(artista == null){
            throw new NotFound("Artista com o id " + id + " não encontrado");
        }
        return artista;
    }

    @Override
    public List<Artista> listarTodosArtistasAtivos() {
        return artistaRepository.listarTodosArtistasAtivos();
    }
}
