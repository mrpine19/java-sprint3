package br.com.healthtech.imrea.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paciente {
    private Long idPaciente;
    private String nomePaciente;
    private String telefonePaciente;
    private Date dataNascimento;
    private int afinidadeDigitalScore;
    private int scoreRiscoAbsenteismo;
    private List<Cuidador> cuidadores;

    public Paciente() {}

    public Paciente(String nomePaciente, String telefonePaciente, Date dataNascimento) {
        this.nomePaciente = nomePaciente;
        this.telefonePaciente = telefonePaciente;
        this.dataNascimento = dataNascimento;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getAfinidadeDigitalScore() {
        return afinidadeDigitalScore;
    }

    public void setAfinidadeDigitalScore(int afinidadeDigitalScore) {
        this.afinidadeDigitalScore = afinidadeDigitalScore;
    }

    public int getScoreRiscoAbsenteismo() {
        return scoreRiscoAbsenteismo;
    }

    public void setScoreRiscoAbsenteismo(int scoreRiscoAbsenteismo) {
        this.scoreRiscoAbsenteismo = scoreRiscoAbsenteismo;
    }

    public List<Cuidador> getCuidadores() {
        return cuidadores;
    }

    public void setCuidadores(List<Cuidador> cuidadores) {
        this.cuidadores = cuidadores;
    }

    public void addCuidador(Cuidador cuidador) {
        if (getCuidadores() == null) {
            setCuidadores(new ArrayList<>());
        }
        getCuidadores().add(cuidador);
    }
}
