package com.fiebinf2bm.streethouse.repository;

import com.fiebinf2bm.streethouse.model.TelefoneArtista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneArtistaRepository extends JpaRepository<TelefoneArtista,Long> {
    @Query(value = "SELECT * FROM TelefoneArtista ta WHERE ta.codStatus='1'", nativeQuery = true)
    public List<TelefoneArtista> listarTelefonesArtistasAtivos();

    @Query(value = "SELECT * FROM TelefoneArtista ta WHERE ta.id=?1 ta.codStatus='1'",nativeQuery = true)
    public TelefoneArtista listarTelefonesArtivasPorIdAtivos(long id);
}

