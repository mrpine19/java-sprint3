package br.com.healthtech.imrea.dao;

import br.com.healthtech.imrea.conexao.ConnectionFactory;
import br.com.healthtech.imrea.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void create(Paciente paciente) {
        String sql = "INSERT INTO TB_CAR_PACIENTE (nome_paciente, telefone_paciente, data_nascimento_paciente, afinidade_digital_score) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"ID_PACIENTE"})) {

            pstmt.setString(1, paciente.getNomePaciente());
            pstmt.setString(2, paciente.getTelefonePaciente());
            pstmt.setDate(3, new Date(paciente.getDataNascimento().getTime()));
            pstmt.setInt(4, paciente.getAfinidadeDigitalScore());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                paciente.setIdPaciente(rs.getLong(1));
            }
            System.out.println("Paciente criado com sucesso! ID: " + paciente.getIdPaciente());

        } catch (SQLException e) {
            System.err.println("Erro ao criar paciente: " + e.getMessage());
        }
    }

    public Paciente read(Long id) {
        String sql = "SELECT id_paciente, nome_paciente, telefone_paciente, data_nascimento_paciente, afinidade_digital_score FROM TB_CAR_PACIENTE WHERE id_paciente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Date dataNasc = rs.getDate("data_nascimento_paciente");

                Paciente paciente = new Paciente(rs.getString("nome_paciente"), rs.getString("telefone_paciente"), dataNasc);
                paciente.setIdPaciente(rs.getLong("id_paciente"));
                paciente.setAfinidadeDigitalScore(rs.getInt("afinidade_digital_score"));
                return paciente;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return null;
    }

    public void update(Paciente paciente) {
        // SQL atualizada para incluir todos os campos e usar 'id_paciente' no WHERE
        String sql = "UPDATE TB_CAR_PACIENTE SET nome_paciente = ?, telefone_paciente = ?, data_nascimento_paciente = ?, afinidade_digital_score = ?, score_risco_absenteismo = ? WHERE id_paciente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paciente.getNomePaciente());
            pstmt.setString(2, paciente.getTelefonePaciente());
            // Converte java.util.Date para java.sql.Date
            pstmt.setDate(3, new Date(paciente.getDataNascimento().getTime()));
            pstmt.setInt(4, paciente.getAfinidadeDigitalScore());
            pstmt.setInt(5, paciente.getScoreRiscoAbsenteismo()); // Novo campo
            pstmt.setLong(6, paciente.getIdPaciente()); // Usa id_paciente

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Paciente com ID " + paciente.getIdPaciente() + " atualizado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        // SQL corrigida para usar 'id_paciente' no WHERE
        String sql = "DELETE FROM TB_CAR_PACIENTE WHERE id_paciente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Paciente com ID " + id + " deletado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
        }
    }

    public List<Paciente> findAll() {
        List<Paciente> pacientes = new ArrayList<>();
        // SQL corrigida para usar a coluna correta do DDL
        String sql = "SELECT id_paciente, nome_paciente, telefone_paciente, data_nascimento_paciente, afinidade_digital_score, score_risco_absenteismo FROM TB_CAR_PACIENTE";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Mapeamento corrigido para usar a data de nascimento no construtor
                java.util.Date dataNasc = rs.getDate("data_nascimento_paciente");

                Paciente paciente = new Paciente(rs.getString("nome_paciente"), rs.getString("telefone_paciente"), dataNasc);

                paciente.setIdPaciente(rs.getLong("id_paciente")); // Correto: usando id_paciente
                paciente.setAfinidadeDigitalScore(rs.getInt("afinidade_digital_score"));
                paciente.setScoreRiscoAbsenteismo(rs.getInt("score_risco_absenteismo")); // Novo campo

                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os pacientes: " + e.getMessage());
        }
        return pacientes;
    }
}
