package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.exceptions.NotFound;
import com.fiebinf2bm.streethouse.model.TelefoneArtista;
import com.fiebinf2bm.streethouse.repository.TelefoneArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneArtistaServiceImpl implements TelefoneArtistaService{

    private final TelefoneArtistaRepository telefoneArtistaRepository;

    public TelefoneArtistaServiceImpl(TelefoneArtistaRepository telefoneArtistaRepository ) {
        this.telefoneArtistaRepository = telefoneArtistaRepository;
    }


    @Override
    public TelefoneArtista salvarTelefonesArtista(TelefoneArtista telefoneArtista) {
        if (!telefoneArtista.validarTelefoneArtista()) {
            throw new BadRequest(telefoneArtista.getMensagemErro());
        }
        return telefoneArtistaRepository.save(telefoneArtista);
    }

    @Override
    public List<TelefoneArtista> listarTodosTelefonesArtista() {
        return telefoneArtistaRepository.findAll();
    }

    @Override
    public TelefoneArtista listarTelefonesArtistaPorId(Long id) {
        try {
            return telefoneArtistaRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarTelefoneArtista(Long id) {
        if (telefoneArtistaRepository.existsById(id)) {
            telefoneArtistaRepository.deleteById(id);
        } else {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public TelefoneArtista atualizarTelefoneArtista(TelefoneArtista telefoneArtista, Long id) {
        try {
            if (!telefoneArtista.validarTelefoneArtista()) {
                throw new BadRequest(telefoneArtista.getMensagemErro());
            }
            TelefoneArtista telefoneBd = telefoneArtistaRepository.findById(id).get();
            telefoneBd.setNumero(telefoneArtista.getNumero());
            return telefoneArtistaRepository.save(telefoneBd); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public TelefoneArtista deletarLogicTelefoneArtista(TelefoneArtista telefoneArtista, Long id) {
        try {
            if (!telefoneArtista.validarTelefoneArtista()){
                throw new BadRequest(telefoneArtista.getMensagemErro());
            }
            TelefoneArtista telefoneBd = telefoneArtistaRepository.findById(id).get();
            telefoneBd.setCodStatus(telefoneArtista.isCodStatus());
            return telefoneArtistaRepository.save(telefoneBd);
        }catch (Exception ex){
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public List<TelefoneArtista> listarTelefonesArtistasAtivos() {
        return telefoneArtistaRepository.listarTelefonesArtistasAtivos();
    }

    @Override
    public TelefoneArtista listarTelefonesArtivasPorIdAtivos(Long id) {
        TelefoneArtista telefoneArtista = telefoneArtistaRepository.listarTelefonesArtivasPorIdAtivos(id);
        if(telefoneArtista == null){
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
        return telefoneArtista;
    }
}
