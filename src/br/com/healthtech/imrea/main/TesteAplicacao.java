package br.com.healthtech.imrea.main;

import br.com.healthtech.imrea.model.Consulta;
import br.com.healthtech.imrea.model.Cuidador;
import br.com.healthtech.imrea.model.Paciente;
import br.com.healthtech.imrea.model.Profissional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TesteAplicacao {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTES DO SISTEMA CARE-LINK (SPRINT 3) ---");

        // Inicializa a camada de lógica de negócio
        GerenciadorDeAgendamentos gerenciador = new GerenciadorDeAgendamentos();

        // 1. Criação de Objetos Fictícios

        // Simula o paciente idoso (Data de nascimento fictícia)
        Paciente paciente1 = new Paciente(
                "Maria Antônia Silva",
                "11987654321",
                new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(80 * 365)) // 80 anos atrás
        );
        paciente1.setScoreRiscoAbsenteismo(800); // Setado manualmente para teste de Alto Risco

        // Simula um paciente de baixo risco
        Paciente paciente2 = new Paciente(
                "Carlos Alberto Souza",
                "11999991234",
                new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(40 * 365)) // 40 anos atrás
        );
        paciente2.setScoreRiscoAbsenteismo(50); // Baixo Risco

        // Simula o cuidador
        Cuidador cuidador1 = new Cuidador(
                "Ana Paula Silva",
                "11998887777"
        );

        // Simula o profissional
        Profissional profissional1 = new Profissional(
                null, // ID será gerado no DAO
                "Dra. Camila Costa",
                "Psicologia"
        );

        // Simula a consulta
        Consulta consulta1 = new Consulta(
                paciente1.getIdPaciente(), // FK temporário, será ajustado no Service
                profissional1.getIdProfissional(), // FK temporário
                new Date(),
                "14:00",
                "AGENDADO"
        );
        consulta1.setIdConsulta(1L); // ID para fins de teste de atualização

        // ---------------------------------------------------------------------
        // TESTE 1: CADASTRO COMPLETO DE AGENDAMENTO (C, C, R)
        // ---------------------------------------------------------------------
        try {
            System.out.println("\n== TESTE 1: CADASTRAR AGENDAMENTO COM CUIDADOR ==");
            gerenciador.cadastrarNovoAgendamento(paciente1, profissional1, cuidador1, consulta1);
        } catch (Exception e) {
            System.err.println("Falha no cadastro inicial: " + e.getMessage());
        }

        // ---------------------------------------------------------------------
        // TESTE 2: LÓGICA DE NEGÓCIO - BUSCA DE ALTO RISCO (R)
        // ---------------------------------------------------------------------
        System.out.println("\n== TESTE 2: BUSCA DE PACIENTES DE ALTO RISCO ==");

        // Para simular alto risco, garantimos que o paciente 1 tem 800 pontos
        int limiteRisco = 500;
        List<Paciente> pacientesEmAlerta = gerenciador.buscarPacientesAltoRisco(limiteRisco);

        System.out.println("Pacientes com score acima de " + limiteRisco + ":");
        if (pacientesEmAlerta.isEmpty()) {
            System.out.println("Nenhum paciente em alerta encontrado (Verifique os dados no banco).");
        } else {
            for (Paciente p : pacientesEmAlerta) {
                System.out.println("- " + p.getNomePaciente() + " | Score: " + p.getScoreRiscoAbsenteismo());
            }
        }

        // ---------------------------------------------------------------------
        // TESTE 3: ATUALIZAÇÃO DO STATUS DA CONSULTA (U)
        // ---------------------------------------------------------------------
        System.out.println("\n== TESTE 3: ATUALIZAÇÃO DE STATUS (FALTA) ==");
        // Simula o registro de uma falta, que dispara a lógica de recalcular o risco
        gerenciador.atualizarStatusConsulta(1L, "FALTOU");

        // ---------------------------------------------------------------------
        // TESTE 4: REMOÇÃO (D)
        // ---------------------------------------------------------------------
        System.out.println("\n== TESTE 4: REMOÇÃO DE AGENDAMENTO (D) ==");
        gerenciador.removerAgendamento(1L);

        System.out.println("\n--- TESTES CONCLUÍDOS ---");
    }
}
