package br.com.healthtech.imrea.main;

import br.com.healthtech.imrea.controller.ConsultaController;
import br.com.healthtech.imrea.controller.CuidadorController;
import br.com.healthtech.imrea.controller.PacienteController;
import br.com.healthtech.imrea.controller.ProfissionalController;
import br.com.healthtech.imrea.model.Consulta;
import br.com.healthtech.imrea.model.Cuidador;
import br.com.healthtech.imrea.model.Paciente;
import br.com.healthtech.imrea.model.Profissional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TesteAplicacao {

    private GerenciadorDeAgendamentos gerenciador;
    private PacienteController pacienteController;
    private ProfissionalController profissionalController;
    private CuidadorController cuidadorController;
    private ConsultaController consultaController;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public TesteAplicacao() {
        this.gerenciador = new GerenciadorDeAgendamentos();
        this.pacienteController = new PacienteController();
        this.profissionalController = new ProfissionalController();
        this.cuidadorController = new CuidadorController();
        this.consultaController = new ConsultaController();
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n=== Sistema CareLink - Gerenciamento ===");
            System.out.println("1. Gerenciar Pacientes");
            System.out.println("2. Gerenciar Profissionais");
            System.out.println("3. Gerenciar Cuidadores");
            System.out.println("4. Gerenciar Consultas");
            System.out.println("5. Realizar Novo Agendamento (Lógica de Negócio)");
            System.out.println("6. Buscar Pacientes de Alto Risco (Lógica de Negócio)");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: exibirMenuPacientes(); break;
                case 2: exibirMenuProfissionais(); break;
                case 3: exibirMenuCuidadores(); break;
                case 4: exibirMenuConsultas(); break;
                case 5: realizarNovoAgendamento(); break;
                case 6: buscarPacientesAltoRisco(); break;
                case 7: System.out.println("Saindo do sistema CareLink. Até logo!"); break;
                default: System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
    }

    private void exibirMenuPacientes() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Pacientes ---");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Atualizar Paciente");
            System.out.println("4. Deletar Paciente");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: adicionarPaciente(); break;
                case 2: listarPacientes(); break;
                case 3: atualizarPaciente(); break;
                case 4: deletarPaciente(); break;
                case 5: break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private void adicionarPaciente() {
        System.out.print("Nome do Paciente: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone do Paciente: ");
        String telefone = scanner.nextLine();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        String dataNascStr = scanner.nextLine();
        Date dataNascimento = null;
        try {
            dataNascimento = dateFormat.parse(dataNascStr);
        } catch (ParseException e) {
            System.err.println("Formato de data inválido. Use dd/MM/yyyy. Paciente não adicionado.");
            return;
        }

        Paciente paciente = new Paciente(nome, telefone, dataNascimento);
        System.out.print("Afinidade Digital Score (ex: 50): ");
        paciente.setAfinidadeDigitalScore(scanner.nextInt());
        System.out.print("Score de Risco Absenteísmo (ex: 100): ");
        paciente.setScoreRiscoAbsenteismo(scanner.nextInt());
        scanner.nextLine();

        pacienteController.adicionar(paciente);
        System.out.println("Paciente adicionado! ID: " + paciente.getIdPaciente());
    }

    private void listarPacientes() {
        List<Paciente> pacientes = pacienteController.listarTodos();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Pacientes ---");
        for (Paciente paciente : pacientes) {
            System.out.println("ID: " + paciente.getIdPaciente() + ", Nome: " + paciente.getNomePaciente() +
                    ", Tel: " + paciente.getTelefonePaciente() + ", Data Nasc: " + dateFormat.format(paciente.getDataNascimento()) +
                    ", Afinidade: " + paciente.getAfinidadeDigitalScore() + ", Risco: " + paciente.getScoreRiscoAbsenteismo());
            if (paciente.getCuidadores() != null && !paciente.getCuidadores().isEmpty()) {
                System.out.println("  Cuidadores: " + paciente.getCuidadores().stream().map(c -> c.getNomeCuidador()).collect(Collectors.joining(", ")));
            }
        }
    }

    private void atualizarPaciente() {
        System.out.print("ID do Paciente para atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Paciente paciente = pacienteController.buscarPorId(id);
        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.print("Novo Nome (" + paciente.getNomePaciente() + "): ");
        paciente.setNomePaciente(scanner.nextLine());
        System.out.print("Novo Telefone (" + paciente.getTelefonePaciente() + "): ");
        paciente.setTelefonePaciente(scanner.nextLine());
        System.out.print("Nova Data de Nascimento (dd/MM/yyyy) (" + dateFormat.format(paciente.getDataNascimento()) + "): ");
        String dataNascStr = scanner.nextLine();
        try {
            paciente.setDataNascimento(dateFormat.parse(dataNascStr));
        } catch (ParseException e) {
            System.err.println("Formato de data inválido. Mantendo data anterior.");
        }
        System.out.print("Novo Afinidade Digital Score (" + paciente.getAfinidadeDigitalScore() + "): ");
        paciente.setAfinidadeDigitalScore(scanner.nextInt());
        System.out.print("Novo Score de Risco Absenteísmo (" + paciente.getScoreRiscoAbsenteismo() + "): ");
        paciente.setScoreRiscoAbsenteismo(scanner.nextInt());
        scanner.nextLine();

        pacienteController.atualizar(paciente);
        System.out.println("Paciente atualizado com sucesso!");
    }

    private void deletarPaciente() {
        System.out.print("ID do Paciente para deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        pacienteController.deletar(id);
    }

    private void exibirMenuProfissionais() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Profissionais ---");
            System.out.println("1. Adicionar Profissional");
            System.out.println("2. Listar Profissionais");
            System.out.println("3. Atualizar Profissional");
            System.out.println("4. Deletar Profissional");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: adicionarProfissional(); break;
                case 2: listarProfissionais(); break;
                case 3: atualizarProfissional(); break;
                case 4: deletarProfissional(); break;
                case 5: break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private void adicionarProfissional() {
        System.out.print("Nome do Profissional: ");
        String nome = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        Profissional profissional = new Profissional(null, nome, especialidade);
        profissionalController.adicionar(profissional);
        System.out.println("Profissional adicionado! ID: " + profissional.getIdProfissional());
    }

    private void listarProfissionais() {
        List<Profissional> profissionais = profissionalController.listarTodos();
        if (profissionais.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Profissionais ---");
        for (Profissional p : profissionais) {
            System.out.println("ID: " + p.getIdProfissional() + ", Nome: " + p.getNomeProfissional() + ", Especialidade: " + p.getEspecialidade());
        }
    }

    private void atualizarProfissional() {
        System.out.print("ID do Profissional para atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Profissional profissional = profissionalController.buscarPorId(id);
        if (profissional == null) {
            System.out.println("Profissional não encontrado.");
            return;
        }
        System.out.print("Novo Nome (" + profissional.getNomeProfissional() + "): ");
        profissional.setNomeProfissional(scanner.nextLine());
        System.out.print("Nova Especialidade (" + profissional.getEspecialidade() + "): ");
        profissional.setEspecialidade(scanner.nextLine());
        profissionalController.atualizar(profissional);
        System.out.println("Profissional atualizado com sucesso!");
    }

    private void deletarProfissional() {
        System.out.print("ID do Profissional para deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        profissionalController.deletar(id);
    }

    private void exibirMenuCuidadores() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Cuidadores ---");
            System.out.println("1. Adicionar Cuidador");
            System.out.println("2. Listar Cuidadores");
            System.out.println("3. Atualizar Cuidador");
            System.out.println("4. Deletar Cuidador");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: adicionarCuidador(); break;
                case 2: listarCuidadores(); break;
                case 3: atualizarCuidador(); break;
                case 4: deletarCuidador(); break;
                case 5: break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private void adicionarCuidador() {
        System.out.print("Nome do Cuidador: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone do Cuidador: ");
        String telefone = scanner.nextLine();
        Cuidador cuidador = new Cuidador(nome, telefone);
        cuidadorController.adicionar(cuidador);
        System.out.println("Cuidador adicionado! ID: " + cuidador.getIdCuidador());
    }

    private void listarCuidadores() {
        List<Cuidador> cuidadores = cuidadorController.listarTodos();
        if (cuidadores.isEmpty()) {
            System.out.println("Nenhum cuidador cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Cuidadores ---");
        for (Cuidador c : cuidadores) {
            System.out.println("ID: " + c.getIdCuidador() + ", Nome: " + c.getNomeCuidador() + ", Tel: " + c.getTelefoneCuidador());
        }
    }

    private void atualizarCuidador() {
        System.out.print("ID do Cuidador para atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Cuidador cuidador = cuidadorController.buscarPorId(id);
        if (cuidador == null) {
            System.out.println("Cuidador não encontrado.");
            return;
        }
        System.out.print("Novo Nome (" + cuidador.getNomeCuidador() + "): ");
        cuidador.setNomeCuidador(scanner.nextLine());
        System.out.print("Novo Telefone (" + cuidador.getTelefoneCuidador() + "): ");
        cuidador.setTelefoneCuidador(scanner.nextLine());
        cuidadorController.atualizar(cuidador);
        System.out.println("Cuidador atualizado com sucesso!");
    }

    private void deletarCuidador() {
        System.out.print("ID do Cuidador para deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        cuidadorController.deletar(id);
    }

    private void exibirMenuConsultas() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Consultas ---");
            System.out.println("1. Adicionar Consulta");
            System.out.println("2. Listar Consultas");
            System.out.println("3. Atualizar Consulta");
            System.out.println("4. Deletar Consulta");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: adicionarConsulta(); break;
                case 2: listarConsultas(); break;
                case 3: atualizarConsulta(); break;
                case 4: deletarConsulta(); break;
                case 5: break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private void adicionarConsulta() {
        System.out.print("ID do Paciente: ");
        Long idPaciente = scanner.nextLong();
        System.out.print("ID do Profissional: ");
        Long idProfissional = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Data da Consulta (dd/MM/yyyy): ");
        String dataAgendaStr = scanner.nextLine();
        System.out.print("Hora da Consulta (HH:mm): ");
        String horaAgendaStr = scanner.nextLine();
        
        java.time.LocalDateTime dataHoraAgenda = null; 
        
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String dataHoraCompleta = dataAgendaStr + " " + horaAgendaStr;
            
            dataHoraAgenda = LocalDateTime.parse(dataHoraCompleta, formatter); 
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("Formato de Data/Hora inválido. Use dd/MM/yyyy e HH:mm. Consulta não adicionada.");
            return;
        }
        
        System.out.print("Status da Consulta (ex: AGENDADO): ");
        String statusConsulta = scanner.nextLine();

        Consulta consulta = new Consulta(idPaciente, idProfissional, dataHoraAgenda, statusConsulta);
        consultaController.adicionar(consulta);
        System.out.println("Consulta adicionada!");
    }

    private void listarConsultas() {
        List<Consulta> consultas = consultaController.listarTodos();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        System.out.println("\n--- Lista de Consultas ---");
        for (Consulta c : consultas) {
            String dataHoraFormatada = c.getDataHoraAgenda().format(formatter);
            
            System.out.println("ID: " + c.getIdConsulta() + 
                    ", Paciente: " + c.getIdPaciente() + 
                    ", Profissional: " + c.getIdProfissional() +
                    ", Data/Hora: " + dataHoraFormatada + 
                    ", Status: " + c.getStatusConsulta());
        }
    }

    private void atualizarConsulta() {
        System.out.print("ID da Consulta para atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        
        Consulta consulta = consultaController.buscarPorId(id); 
        
        if (consulta == null) {
            System.out.println("Consulta não encontrada.");
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        System.out.print("Novo Status (" + consulta.getStatusConsulta() + "): ");
        consulta.setStatusConsulta(scanner.nextLine());
        
        System.out.print("Nova Data e Hora (dd/MM/yyyy HH:mm) (" + consulta.getDataHoraAgenda().format(formatter) + "): ");
        String dataHoraStr = scanner.nextLine();
        
        try {
            LocalDateTime novaDataHora = LocalDateTime.parse(dataHoraStr, formatter);
            consulta.setDataHoraAgenda(novaDataHora);
        } catch (DateTimeParseException e) {
            System.err.println("Formato de data/hora inválido. Mantendo data/hora anterior.");
        }

        consultaController.atualizar(consulta);
        System.out.println("Consulta atualizada com sucesso!");
    }

    private void deletarConsulta() {
        System.out.print("ID da Consulta para deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        consultaController.deletar(id);
    }

    private void realizarNovoAgendamento() {
        System.out.println("\n--- Realizar Novo Agendamento Completo ---");
        System.out.println("Dados do Paciente:");
        System.out.print("Nome: ");
        String pNome = scanner.nextLine();
        System.out.print("Telefone: ");
        String pTelefone = scanner.nextLine();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        String pDataNascStr = scanner.nextLine();
        Date pDataNasc = null;
        try {
            pDataNasc = dateFormat.parse(pDataNascStr);
        } catch (ParseException e) {
            System.err.println("Formato de data inválido. Usando data atual.");
            pDataNasc = new Date();
        }
        Paciente paciente = new Paciente(pNome, pTelefone, pDataNasc);
        paciente.setAfinidadeDigitalScore(50);
        paciente.setScoreRiscoAbsenteismo(100);

        System.out.println("Dados do Profissional:");
        System.out.print("Nome: ");
        String prNome = scanner.nextLine();
        System.out.print("Especialidade: ");
        String prEspecialidade = scanner.nextLine();
        Profissional profissional = new Profissional(null, prNome, prEspecialidade);

        System.out.println("Dados do Cuidador (opcional, deixe em branco para pular):");
        System.out.print("Nome: ");
        String cNome = scanner.nextLine();
        Cuidador cuidador = null;
        if (!cNome.isEmpty()) {
            System.out.print("Telefone: ");
            String cTelefone = scanner.nextLine();
            cuidador = new Cuidador(cNome, cTelefone);
        }

        System.out.println("Dados da Consulta:");
        System.out.print("Data (dd/MM/yyyy): ");
        String conDataStr = scanner.nextLine();
        System.out.print("Hora (HH:mm): ");
        String conHoraStr = scanner.nextLine();
        System.out.print("Status (ex: AGENDADO): ");
        String statusConsulta = scanner.nextLine();

        LocalDateTime dataHoraAgenda = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String dataHoraCompleta = conDataStr + " " + conHoraStr;
            dataHoraAgenda = LocalDateTime.parse(dataHoraCompleta, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Formato de Data/Hora inválido. Use dd/MM/yyyy e HH:mm. Agendamento cancelado.");
            return;
        }

        Consulta consulta = new Consulta(
                paciente.getIdPaciente(),
                profissional.getIdProfissional(),
                dataHoraAgenda,
                statusConsulta
            );

        gerenciador.cadastrarNovoAgendamento(paciente, profissional, cuidador, consulta);
        System.out.println("Agendamento completo realizado com sucesso!");
    }

    private void buscarPacientesAltoRisco() {
        System.out.print("Informe o score mínimo para considerar 'Alto Risco' (ex: 500): ");
        int threshold = scanner.nextInt();
        scanner.nextLine();
        List<Paciente> pacientesAltoRisco = gerenciador.buscarPacientesAltoRisco(threshold);
        if (pacientesAltoRisco.isEmpty()) {
            System.out.println("Nenhum paciente encontrado com score de risco acima de " + threshold + ".");
        } else {
            System.out.println("\n--- Pacientes de Alto Risco ---");
            for (Paciente p : pacientesAltoRisco) {
                System.out.println("ID: " + p.getIdPaciente() + ", Nome: " + p.getNomePaciente() + ", Score Risco: " + p.getScoreRiscoAbsenteismo());
            }
        }
    }


    public static void main(String[] args) {
    	Locale.setDefault(Locale.US);
        TesteAplicacao app = new TesteAplicacao();
        app.exibirMenuPrincipal();
    }
}
