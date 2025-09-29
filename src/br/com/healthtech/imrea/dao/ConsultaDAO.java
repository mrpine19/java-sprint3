package br.com.healthtech.imrea.dao;

import br.com.healthtech.imrea.conexao.ConnectionFactory;
import br.com.healthtech.imrea.model.Consulta;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void create(Consulta consulta) {
    	String sql = "INSERT INTO TB_CAR_CONSULTA (id_paciente, id_profissional, data_agenda, status_consulta) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"ID_CONSULTA"})) {

            pstmt.setLong(1, consulta.getIdPaciente());
            pstmt.setLong(2, consulta.getIdProfissional());
            
            java.sql.Timestamp timestamp = Timestamp.valueOf(consulta.getDataHoraAgenda()); 
            pstmt.setTimestamp(3, timestamp); 
            pstmt.setString(4, consulta.getStatusConsulta());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                consulta.setIdConsulta(rs.getLong(1));
            }

            System.out.println("Consulta criada com sucesso! ID: " + consulta.getIdConsulta());

        } catch (SQLException e) {
            System.err.println("Erro ao criar consulta: " + e.getMessage());
        }
    }

    public Consulta read(Long id) {
        String sql = "SELECT id_consulta, id_paciente, id_profissional, data_agenda, status_consulta FROM TB_CAR_CONSULTA WHERE id_consulta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("data_agenda");
                LocalDateTime dataHoraAgenda = timestamp != null ? timestamp.toLocalDateTime() : null;

                Consulta consulta = new Consulta(
                        rs.getLong("id_paciente"),
                        rs.getLong("id_profissional"),
                        dataHoraAgenda, 
                        rs.getString("status_consulta")
                );
                consulta.setIdConsulta(rs.getLong("id_consulta"));
                return consulta;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consulta: " + e.getMessage());
        }
        return null;
    }

    public void update(Consulta consulta) {
        String sql = "UPDATE TB_CAR_CONSULTA SET status_consulta = ?, data_agenda = ? WHERE id_consulta = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, consulta.getStatusConsulta());
            
            Timestamp timestamp = Timestamp.valueOf(consulta.getDataHoraAgenda()); 
            pstmt.setTimestamp(2, timestamp);
            pstmt.setLong(3, consulta.getIdConsulta());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Consulta com ID " + consulta.getIdConsulta() + " atualizada.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM TB_CAR_CONSULTA WHERE id_consulta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Consulta com ID " + id + " deletada.");
            } else {
                System.out.println("Nenhuma consulta com ID " + id + " foi encontrado para deletar.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar consulta: " + e.getMessage());
        }
    }

    public List<Consulta> findAll() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT id_consulta, id_paciente, id_profissional, data_agenda, status_consulta FROM TB_CAR_CONSULTA ORDER BY id_consulta";
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("data_agenda");
                LocalDateTime dataHoraAgenda = timestamp != null ? timestamp.toLocalDateTime() : null;

                Consulta consulta = new Consulta(
                        rs.getLong("id_paciente"),
                        rs.getLong("id_profissional"),
                        dataHoraAgenda,
                        rs.getString("status_consulta")
                );
                consulta.setIdConsulta(rs.getLong("id_consulta"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todas as consultas: " + e.getMessage());
        }
        return consultas;
    }
}
