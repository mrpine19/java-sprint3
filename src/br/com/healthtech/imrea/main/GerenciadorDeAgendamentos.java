package br.com.healthtech.imrea.main;

import br.com.healthtech.imrea.dao.ConsultaDAO;
import br.com.healthtech.imrea.dao.CuidadorDAO;
import br.com.healthtech.imrea.dao.PacienteDAO;
import br.com.healthtech.imrea.dao.ProfissionalDAO;
import br.com.healthtech.imrea.model.Consulta;
import br.com.healthtech.imrea.model.Cuidador;
import br.com.healthtech.imrea.model.Paciente;
import br.com.healthtech.imrea.model.Profissional;

import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorDeAgendamentos {

    // Instanciação das classes DAO (a camada de acesso a dados)
    private final PacienteDAO pacienteDAO;
    private final ConsultaDAO consultaDAO;
    private final ProfissionalDAO profissionalDAO;
    private final CuidadorDAO cuidadorDAO; // Adicionado para demonstração completa

    public GerenciadorDeAgendamentos() {
        // Inicialização dos DAOs
        this.pacienteDAO = new PacienteDAO();
        this.consultaDAO = new ConsultaDAO();
        this.profissionalDAO = new ProfissionalDAO();
        this.cuidadorDAO = new CuidadorDAO();
    }

    /**
     * Método auxiliar: Busca o cuidador por nome/telefone, se não existir, o cria.
     * Simplifica a lógica dentro do método principal.
     */
    private Cuidador buscarOuCriarCuidador(Cuidador cuidador) {
        // Simulação de lógica: Na vida real, você buscaria no banco de dados.
        // Se o ID for null, simula-se a criação para fins de demonstração.
        if (cuidador.getIdCuidador() == null) {
            cuidadorDAO.create(cuidador);
        }
        return cuidador;
    }

    /**
     * Método 1: Orquestra o cadastro de Paciente, Profissional, Cuidador e Agendamento.
     * Adicionando a Persona CUIDADOR.
     */
    public void cadastrarNovoAgendamento(Paciente paciente, Profissional profissional, Cuidador cuidador, Consulta consulta) {
        System.out.println("\n--- Iniciando Cadastro de Agendamento ---");

        // 1. Lógica de Cuidador: Buscar ou criar o cuidador
        if (cuidador != null) {
            Cuidador cuidadorPersistido = buscarOuCriarCuidador(cuidador);
            // Lógica de Negócio: Associa o cuidador ao paciente no modelo
            paciente.addCuidador(cuidadorPersistido);
        }

        // 2. Lógica de Paciente: Tenta buscar o paciente. Se não existir, cria.
        // Se o paciente for novo, ele será criado já com o cuidador associado em memória.
        Paciente pacienteExistente = pacienteDAO.read(paciente.getIdPaciente());
        if (pacienteExistente == null) {
            pacienteDAO.create(paciente);
        } else {
            // Se o paciente existe, a lógica de negócio precisaria atualizar a lista de cuidadores
            System.out.println("Paciente já cadastrado. Usando ID: " + pacienteExistente.getIdPaciente());
        }

        // 3. Lógica de Profissional: Cria/Busca o profissional.
        profissionalDAO.create(profissional); // Simula o UPSERT

        // 4. Lógica de Agendamento: Vincula e cria a consulta.
        consulta.setIdPaciente(paciente.getIdPaciente());
        consulta.setIdProfissional(profissional.getIdProfissional());
        consulta.setStatusConsulta("AGENDADO");
        consultaDAO.create(consulta);

        System.out.println("--- Agendamento concluído ---");
    }

    /**
     * Método 2: Atualiza o status de uma consulta e demonstra a lógica de impacto no SCORE de Risco.
     * (Requisito central da equipe de saúde digital).
     */
    public void atualizarStatusConsulta(Long idConsulta, String novoStatus) {
        System.out.println("\n--- Atualizando Status da Consulta ---");

        Consulta consulta = consultaDAO.read(idConsulta);

        if (consulta == null) {
            System.err.println("Erro: Consulta ID " + idConsulta + " não encontrada.");
            return;
        }

        consulta.setStatusConsulta(novoStatus);
        consultaDAO.update(consulta);

        // Lógica de Negócio: Se o paciente faltou, o Score de Risco é impactado.
        if (novoStatus.equalsIgnoreCase("Faltou")) {
            System.out.println("! ALERTA ! Ação de falta registrada. Score de Risco do Paciente será recalculado (RN04).");
            // Aqui, na API, chamaríamos o serviço de IA para recalcular o score.
        }
    }

    /**
     * Método 3: Busca pacientes que se enquadram no perfil de "Alto Risco".
     * Simula a lógica do Dashboard de Alertas.
     */
    public List<Paciente> buscarPacientesAltoRisco(int thresholdScore) {
        System.out.println("\n--- Buscando Pacientes de Alto Risco (> " + thresholdScore + " pontos) ---");

        List<Paciente> todosPacientes = pacienteDAO.findAll();

        // Lógica de Negócio: Filtra pacientes cujo Score de Risco seja maior que o limite.
        List<Paciente> altoRisco = todosPacientes.stream()
                .filter(p -> p.getScoreRiscoAbsenteismo() >= thresholdScore)
                .collect(Collectors.toList());

        System.out.println("Total de pacientes de Alto Risco encontrados: " + altoRisco.size());
        return altoRisco;
    }

    /**
     * Método 4: Remove um agendamento e demonstra a lógica de exclusão (CRUD).
     */
    public void removerAgendamento(Long idConsulta) {
        System.out.println("\n--- Removendo Agendamento ---");
        consultaDAO.delete(idConsulta);
    }
}