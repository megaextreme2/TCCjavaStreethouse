package com.fiebinf2bm.streethouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="TelefoneArtista")
public class TelefoneArtista {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long telefone_id;
    @Column(nullable = true, columnDefinition = "INTEGER")
    private Long numero;
    @Column(nullable = true, columnDefinition = "INTEGER")
    private Long ddd;
    @Column(nullable = false)
    private boolean codStatus;

    @Transient  //Atributos que não estão em uma coluna
    @JsonIgnore
    private String mensagemErro = "";

    @Transient
    @JsonIgnore
    private boolean isValid = true;

    public Long getTelefone_id() {
        return telefone_id;
    }

    public void setTelefone_id(Long telefone_id) {
        this.telefone_id = telefone_id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getDdd() {
        return ddd;
    }

    public void setDdd(Long ddd) {
        this.ddd = ddd;
    }

    public boolean isCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
    public boolean validarTelefoneArtista(){

        return isValid;
    }
}
