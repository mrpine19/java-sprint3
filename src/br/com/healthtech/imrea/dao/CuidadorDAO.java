package br.com.healthtech.imrea.dao;

import br.com.healthtech.imrea.conexao.ConnectionFactory;
import br.com.healthtech.imrea.model.Cuidador;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CuidadorDAO {

    public void create(Cuidador cuidador) {
        String sql = "INSERT INTO TB_CAR_CUIDADOR (nome_cuidador, telefone_cuidador, dt_criacao) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"ID_CUIDADOR"})) {

            pstmt.setString(1, cuidador.getNomeCuidador());
            pstmt.setString(2, cuidador.getTelefoneCuidador());
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                cuidador.setIdCuidador(rs.getLong(1));
            }

            System.out.println("Cuidador criado com sucesso! ID: " + cuidador.getIdCuidador());

        } catch (SQLException e) {
            System.err.println("Erro ao criar cuidador: " + e.getMessage());
        }
    }

    public Cuidador read(Long id) {
        String sql = "SELECT * FROM TB_CAR_CUIDADOR WHERE id_cuidador = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Cuidador cuidador = new Cuidador(
                        rs.getString("nome_cuidador"),
                        rs.getString("telefone_cuidador")
                );
                cuidador.setIdCuidador(rs.getLong("id_cuidador"));
                cuidador.setDataCriacao(rs.getTimestamp("dt_criacao").toLocalDateTime());
                return cuidador;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar cuidador: " + e.getMessage());
        }
        return null;
    }

    public void update(Cuidador cuidador) {
        String sql = "UPDATE TB_CAR_CUIDADOR SET nome_cuidador = ?, telefone_cuidador = ? WHERE id_cuidador = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cuidador.getNomeCuidador());
            pstmt.setString(2, cuidador.getTelefoneCuidador());
            pstmt.setLong(3, cuidador.getIdCuidador());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cuidador com ID " + cuidador.getIdCuidador() + " atualizado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cuidador: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM TB_CAR_CUIDADOR WHERE id_cuidador = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cuidador com ID " + id + " deletado.");
            } else {
                System.out.println("Nenhum cuidador com ID " + id + " foi encontrado para deletar.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar cuidador: " + e.getMessage());
        }
    }

    public List<Cuidador> findAll() {
        List<Cuidador> cuidadores = new ArrayList<>();
        String sql = "SELECT * FROM TB_CAR_CUIDADOR ORDER BY id_cuidador";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cuidador cuidador = new Cuidador(
                        rs.getString("nome_cuidador"),
                        rs.getString("telefone_cuidador")
                );
                cuidador.setIdCuidador(rs.getLong("id_cuidador"));
                cuidador.setDataCriacao(rs.getTimestamp("dt_criacao").toLocalDateTime());
                cuidadores.add(cuidador);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os cuidadores: " + e.getMessage());
        }
        return cuidadores;
    }
}
