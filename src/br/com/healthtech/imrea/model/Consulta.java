package br.com.healthtech.imrea.model;

import java.time.LocalDateTime;

public class Consulta {
    private Long idConsulta;
    private Long idPaciente;
    private Long idProfissional;
    private String statusConsulta; 
    private LocalDateTime dataHoraAgenda;

    public Consulta(Long idPaciente, Long idProfissional, LocalDateTime dataHoraAgenda, String statusConsulta) {
        this.idPaciente = idPaciente;
        this.idProfissional = idProfissional;
        this.dataHoraAgenda = dataHoraAgenda;
        this.statusConsulta = statusConsulta;
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

	public Long getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(Long idProfissional) {
		this.idProfissional = idProfissional;
	}

	public String getStatusConsulta() {
		return statusConsulta;
	}

	public void setStatusConsulta(String statusConsulta) {
		this.statusConsulta = statusConsulta;
	}

	public LocalDateTime getDataHoraAgenda() {
		return dataHoraAgenda;
	}

	public void setDataHoraAgenda(LocalDateTime dataHoraAgenda) {
		this.dataHoraAgenda = dataHoraAgenda;
	}

    
}