package br.com.healthtech.imrea.model;

public class Profissional {
    private Long idProfissional;
    private String nomeProfissional;
    private String especialidade;

    public Profissional() {
    }

    public Profissional(Long idProfissional, String nomeProfissional, String especialidade) {
        this.idProfissional = idProfissional;
        this.nomeProfissional = nomeProfissional;
        this.especialidade = especialidade;
    }

    public Long getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Long idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
