package com.fiebinf2bm.streethouse.repository;

import com.fiebinf2bm.streethouse.model.TelefoneOrganizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneOrganizadorRepository extends JpaRepository<TelefoneOrganizador,Long> {
    @Query(value = "SELECT * FROM TelefoneOrganizador to WHERE to.codStatus='1'", nativeQuery = true)
    public List<TelefoneOrganizador> listarTelefonesOrganizadoresAtivos();

    @Query(value = "SELECT * FROM TelefoneOrganizador to WHERE to.id=?1 to.codStatus='1'",nativeQuery = true)
    public TelefoneOrganizador listarTelefonesOrganizadoresPorIdAtivos(long id);
}
