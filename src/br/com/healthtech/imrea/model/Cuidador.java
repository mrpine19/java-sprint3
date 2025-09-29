package br.com.healthtech.imrea.model;

import java.time.LocalDateTime;

public class Cuidador {
    private Long idCuidador;
    private String nomeCuidador;
    private String telefoneCuidador;
    private LocalDateTime dataCriacao;


    public Cuidador(String nomeCuidadorMascarado, String telefoneCuidadorMascarado) {
        this.nomeCuidador = nomeCuidadorMascarado;
        this.telefoneCuidador = telefoneCuidadorMascarado;
    }

    public Long getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(Long idCuidador) {
        this.idCuidador = idCuidador;
    }

    public String getNomeCuidador() {
        return nomeCuidador;
    }

    public void setNomeCuidador(String nomeCuidador) {
        this.nomeCuidador = nomeCuidador;
    }

    public String getTelefoneCuidador() {
        return telefoneCuidador;
    }

    public void setTelefoneCuidador(String telefoneCuidador) {
        this.telefoneCuidador = telefoneCuidador;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
