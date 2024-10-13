package com.fiebinf2bm.streethouse.repository;

import com.fiebinf2bm.streethouse.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista,Long> {
    @Query(value = "SELECT * FROM Artista at WHERE at.codStatus='1'", nativeQuery = true)
    public List<Artista> listarTodosArtistasAtivos();

    @Query(value = "SELECT * FROM Artista at WHERE at.id=?1 AND at.codStatus='1'", nativeQuery = true)
    public Artista listarArtistasPorIdAtivos(Long id);

}
