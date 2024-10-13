package com.fiebinf2bm.streethouse.repository;

import com.fiebinf2bm.streethouse.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {

    @Query(value = "SELECT * FROM Evento ev WHERE ev.codStatus='1'", nativeQuery = true)
    public List<Evento> listarTodosEventosAtivos();

    @Query(value = "SELECT * FROM Evento ev WHERE ev.id=?1 AND ev.codStatus='1'", nativeQuery = true)
    public Evento listarEventosPorIdAtivos(Long id);
}
