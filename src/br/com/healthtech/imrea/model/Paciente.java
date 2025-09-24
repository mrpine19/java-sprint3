package br.com.healthtech.imrea.model;

public class Paciente {
    private Long idPaciente;
    private String nomePaciente;
    private String telefonePaciente;
    private int afinidadeDigitalScore;

    public Paciente() {
    }

    public Paciente(String nomePaciente, String telefonePaciente) {
        this.nomePaciente = nomePaciente;
        this.telefonePaciente = telefonePaciente;
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

    public int getAfinidadeDigitalScore() {
        return afinidadeDigitalScore;
    }

    public void setAfinidadeDigitalScore(int afinidadeDigitalScore) {
        this.afinidadeDigitalScore = afinidadeDigitalScore;
    }
}
