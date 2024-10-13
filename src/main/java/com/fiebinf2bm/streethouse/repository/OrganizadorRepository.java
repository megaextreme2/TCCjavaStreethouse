package com.fiebinf2bm.streethouse.repository;


import com.fiebinf2bm.streethouse.model.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador,Long> {
    @Query(value = "SELECT * FROM Organizador or WHERE or.codStatus='1'", nativeQuery = true)
    public List<Organizador> listarOrganizadoresAtivos();

    @Query(value = "SELECT * FROM Organizador or WHERE or.id=?1 AND or.codStatus='1'", nativeQuery = true)
    public Organizador listarOrganizadoresPorIdAtivos(Long id);
}
