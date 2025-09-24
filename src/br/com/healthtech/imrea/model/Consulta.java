package br.com.healthtech.imrea.model;

public class Consulta {
    private Long idConsulta;
    private Long idPaciente; // Chave estrangeira para Paciente
    private String dataAgenda;
    private String horaAgenda;

    public Consulta() {
    }

    public Consulta(Long idConsulta, Long idPaciente, String dataAgenda, String horaAgenda) {
        this.idConsulta = idConsulta;
        this.idPaciente = idPaciente;
        this.dataAgenda = dataAgenda;
        this.horaAgenda = horaAgenda;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(String dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public String getHoraAgenda() {
        return horaAgenda;
    }

    public void setHoraAgenda(String horaAgenda) {
        this.horaAgenda = horaAgenda;
    }
}
