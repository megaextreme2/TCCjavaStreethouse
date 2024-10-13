package com.fiebinf2bm.streethouse.services;

import com.fiebinf2bm.streethouse.model.TelefoneOrganizador;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TelefoneOrganizadorService {
    public TelefoneOrganizador salvarTelefonesOrganizador(TelefoneOrganizador telefoneOrganizador);
    public List<TelefoneOrganizador> listarTodosTelefonesOrganizador();
    public TelefoneOrganizador listarTelefonesOrganizadorPorId(Long id);
    public boolean deletarTelefoneOrganizador(Long id);
    public TelefoneOrganizador atualizarTelefoneOrganizador(TelefoneOrganizador telefoneOrganizador, Long id);
    public TelefoneOrganizador deletarLogicTelefoneOrganizador(TelefoneOrganizador telefoneOrganizador, Long id);
    public TelefoneOrganizador listarTelefonesOrganizadoresPorIdAtivos(Long id);
    public List<TelefoneOrganizador> listarTelefonesOrganizadoresAtivos();
}
