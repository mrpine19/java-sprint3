package br.com.healthtech.imrea.model;

import java.util.Date;

public class Consulta {
    private Long idConsulta;
    private Long idPaciente;
    private Long idProfissional; // Adicionado para lógica de negócio
    private Date dataAgenda; // Alterado para Date para facilitar o uso no SQL
    private String horaAgenda;
    private String statusConsulta; // Adicionado para demonstração de Update/Status

    public Consulta(Long idPaciente, Long idProfissional, Date dataAgenda, String horaAgenda, String statusConsulta) {
        this.idPaciente = idPaciente;
        this.idProfissional = idProfissional;
        this.dataAgenda = dataAgenda;
        this.horaAgenda = horaAgenda;
        this.statusConsulta = statusConsulta;
    }

    public Long getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Long idConsulta) { this.idConsulta = idConsulta; }
    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }
    public Long getIdProfissional() { return idProfissional; }
    public void setIdProfissional(Long idProfissional) { this.idProfissional = idProfissional; }
    public Date getDataAgenda() { return dataAgenda; }
    public void setDataAgenda(Date dataAgenda) { this.dataAgenda = dataAgenda; }
    public String getHoraAgenda() { return horaAgenda; }
    public void setHoraAgenda(String horaAgenda) { this.horaAgenda = horaAgenda; }
    public String getStatusConsulta() { return statusConsulta; }
    public void setStatusConsulta(String statusConsulta) { this.statusConsulta = statusConsulta; }
}
