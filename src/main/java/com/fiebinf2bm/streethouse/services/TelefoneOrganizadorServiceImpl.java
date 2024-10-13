package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.exceptions.BadRequest;
import com.fiebinf2bm.streethouse.exceptions.NotFound;
import com.fiebinf2bm.streethouse.model.TelefoneOrganizador;
import com.fiebinf2bm.streethouse.repository.TelefoneOrganizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneOrganizadorServiceImpl implements TelefoneOrganizadorService {

    private final TelefoneOrganizadorRepository telefoneOrganizadorRepository;

    public TelefoneOrganizadorServiceImpl(TelefoneOrganizadorRepository telefoneOrganizadorRepository ) {
        this.telefoneOrganizadorRepository = telefoneOrganizadorRepository;
    }


    @Override
    public TelefoneOrganizador salvarTelefonesOrganizador(TelefoneOrganizador telefoneOrganizador) {
        if (!telefoneOrganizador.validarTelefoneOrganizador()) {
            throw new BadRequest(telefoneOrganizador.getMensagemErro());
        }
        return telefoneOrganizadorRepository.save(telefoneOrganizador);
    }

    @Override
    public List<TelefoneOrganizador> listarTodosTelefonesOrganizador() {
        return telefoneOrganizadorRepository.findAll();
    }

    @Override
    public TelefoneOrganizador listarTelefonesOrganizadorPorId(Long id) {
        try {
            return telefoneOrganizadorRepository.findById(id).get();
        } catch (Exception ex) {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public boolean deletarTelefoneOrganizador(Long id) {
        if (telefoneOrganizadorRepository.existsById(id)) {
            telefoneOrganizadorRepository.deleteById(id);
        } else {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
        return true;
    }

    @Override
    public TelefoneOrganizador atualizarTelefoneOrganizador(TelefoneOrganizador telefoneOrganizador, Long id) {
        try {
            if (!telefoneOrganizador.validarTelefoneOrganizador()) {
                throw new BadRequest(telefoneOrganizador.getMensagemErro());
            }
            TelefoneOrganizador telefoneBd = telefoneOrganizadorRepository.findById(id).get();
            telefoneBd.setNumero(telefoneOrganizador.getNumero());
            return telefoneOrganizadorRepository.save(telefoneBd); // save: dupla função - update para objeto existente (quando não tenho objeto, ele salva, e quando tem, ele atualiza)

        } catch (Exception ex) {
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public TelefoneOrganizador deletarLogicTelefoneOrganizador(TelefoneOrganizador telefoneOrganizador, Long id) {
        try {
            if (!telefoneOrganizador.validarTelefoneOrganizador()){
                throw new BadRequest(telefoneOrganizador.getMensagemErro());
            }
            TelefoneOrganizador telefoneBd = telefoneOrganizadorRepository.findById(id).get();
            telefoneBd.setCodStatus(telefoneOrganizador.isCodStatus());
            return telefoneOrganizadorRepository.save(telefoneBd);
        }catch (Exception ex){
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
    }

    @Override
    public List<TelefoneOrganizador> listarTelefonesOrganizadoresAtivos() {
        return telefoneOrganizadorRepository.listarTelefonesOrganizadoresAtivos();
    }

    @Override
    public TelefoneOrganizador listarTelefonesOrganizadoresPorIdAtivos(Long id) {
        TelefoneOrganizador telefone = telefoneOrganizadorRepository.listarTelefonesOrganizadoresPorIdAtivos(id);
        if(telefone == null){
            throw new NotFound("Telefone com o id " + id + " não encontrado");
        }
        return telefone;
    }
}
