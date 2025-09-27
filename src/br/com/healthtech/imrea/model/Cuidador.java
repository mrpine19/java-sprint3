package br.com.healthtech.imrea.model;

import java.time.LocalDateTime;

public class Cuidador {
    private Long idCuidador;
    private String nomeCuidadorMascarado;
    private String telefoneCuidadorMascarado;
    private LocalDateTime dataCriacao;


    public Cuidador(String nomeCuidadorMascarado, String telefoneCuidadorMascarado) {
        this.nomeCuidadorMascarado = nomeCuidadorMascarado;
        this.telefoneCuidadorMascarado = telefoneCuidadorMascarado;
    }

    public Long getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(Long idCuidador) {
        this.idCuidador = idCuidador;
    }

    public String getNomeCuidadorMascarado() {
        return nomeCuidadorMascarado;
    }

    public void setNomeCuidadorMascarado(String nomeCuidadorMascarado) {
        this.nomeCuidadorMascarado = nomeCuidadorMascarado;
    }

    public String getTelefoneCuidadorMascarado() {
        return telefoneCuidadorMascarado;
    }

    public void setTelefoneCuidadorMascarado(String telefoneCuidadorMascarado) {
        this.telefoneCuidadorMascarado = telefoneCuidadorMascarado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
