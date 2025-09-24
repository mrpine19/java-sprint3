package br.com.healthtech.imrea.dao;

import br.com.healthtech.imrea.conexao.ConnectionFactory;
import br.com.healthtech.imrea.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void create(Paciente paciente) {
        String sql = "INSERT INTO TB_CAR_PACIENTE (nome_paciente, telefone_paciente, afinidade_digital_score) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"})) {

            pstmt.setString(1, paciente.getNomePaciente());
            pstmt.setString(2, paciente.getTelefonePaciente());
            pstmt.setInt(3, paciente.getAfinidadeDigitalScore());
            pstmt.executeUpdate();

            // Pega o ID gerado pelo banco e seta no objeto
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
        String sql = "SELECT * FROM TB_CAR_PACIENTE WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente(rs.getString("nome_paciente"), rs.getString("telefone_paciente"));
                paciente.setIdPaciente(rs.getLong("id"));
                paciente.setAfinidadeDigitalScore(rs.getInt("afinidade_digital_score"));
                return paciente;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar
    }

    public void update(Paciente paciente) {
        String sql = "UPDATE TB_CAR_PACIENTE SET nome_paciente = ?, telefone_paciente = ?, afinidade_digital_score = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paciente.getNomePaciente());
            pstmt.setString(2, paciente.getTelefonePaciente());
            pstmt.setInt(3, paciente.getAfinidadeDigitalScore());
            pstmt.setLong(4, paciente.getIdPaciente());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Paciente com ID " + paciente.getIdPaciente() + " atualizado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM TB_CAR_PACIENTE WHERE id = ?";
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
        String sql = "SELECT * FROM TB_CAR_PACIENTE";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente(rs.getString("nome_paciente"), rs.getString("telefone_paciente"));
                paciente.setIdPaciente(rs.getLong("id"));
                paciente.setAfinidadeDigitalScore(rs.getInt("afinidade_digital_score"));
                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os pacientes: " + e.getMessage());
        }
        return pacientes;
    }
}
