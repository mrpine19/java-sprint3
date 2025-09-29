package br.com.healthtech.imrea.main;

import br.com.healthtech.imrea.controller.ConsultaController;
import br.com.healthtech.imrea.controller.CuidadorController;
import br.com.healthtech.imrea.controller.PacienteController;
import br.com.healthtech.imrea.controller.ProfissionalController;
import br.com.healthtech.imrea.model.Consulta;
import br.com.healthtech.imrea.model.Cuidador;
import br.com.healthtech.imrea.model.Paciente;
import br.com.healthtech.imrea.model.Profissional;

import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorDeAgendamentos {

    private final PacienteController pacienteController;
    private final ProfissionalController profissionalController;
    private final CuidadorController cuidadorController;
    private final ConsultaController consultaController;

    public GerenciadorDeAgendamentos() {
        this.pacienteController = new PacienteController();
        this.profissionalController = new ProfissionalController();
        this.cuidadorController = new CuidadorController();
        this.consultaController = new ConsultaController();
    }

    public void cadastrarNovoAgendamento(Paciente paciente, Profissional profissional, Cuidador cuidador, Consulta consulta) {
        System.out.println("\n--- Iniciando Cadastro de Agendamento ---");

        if (cuidador != null) {
            cuidadorController.adicionar(cuidador);
            paciente.addCuidador(cuidador);
        }

        pacienteController.adicionar(paciente); 
        profissionalController.adicionar(profissional); 
        
        consulta.setIdPaciente(paciente.getIdPaciente());
        consulta.setIdProfissional(profissional.getIdProfissional());
        consulta.setStatusConsulta("AGENDADO");
        consultaController.adicionar(consulta);

        System.out.println("--- Agendamento concluído ---");
    }

    public void atualizarStatusConsulta(Long idConsulta, String novoStatus) {
        System.out.println("\n--- Atualizando Status da Consulta ---");

        Consulta consulta = consultaController.buscarPorId(idConsulta);

        if (consulta == null) {
            System.err.println("Erro: Consulta ID " + idConsulta + " não encontrada.");
            return;
        }

        consulta.setStatusConsulta(novoStatus);
        consultaController.atualizar(consulta);
    }

    public List<Paciente> buscarPacientesAltoRisco(int thresholdScore) {
        System.out.println("\n--- Buscando Pacientes de Alto Risco (> " + thresholdScore + " pontos) ---");

        List<Paciente> todosPacientes = pacienteController.listarTodos();

        List<Paciente> altoRisco = todosPacientes.stream()
                .filter(p -> p.getScoreRiscoAbsenteismo() >= thresholdScore)
                .collect(Collectors.toList());

        System.out.println("Total de pacientes de Alto Risco encontrados: " + altoRisco.size());
        return altoRisco;
    }

    public void removerAgendamento(Long idConsulta) {
        System.out.println("\n--- Removendo Agendamento ---");
        consultaController.deletar(idConsulta);
        System.out.println("Agendamento " + idConsulta + " removido.");
    }
}