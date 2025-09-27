package br.com.healthtech.imrea.dao;

import br.com.healthtech.imrea.conexao.ConnectionFactory;
import br.com.healthtech.imrea.model.Profissional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO {

    public void create(Profissional profissional) {
        String sql = "INSERT INTO TB_CAR_PROFISSIONAL_SAUDE (nome_completo, especialidade) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"ID_PROFISSIONAL"})) {

            pstmt.setString(1, profissional.getNomeProfissional());
            pstmt.setString(2, profissional.getEspecialidade());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                profissional.setIdProfissional(rs.getLong(1));
            }

            System.out.println("Profissional criado com sucesso! ID: " + profissional.getIdProfissional());

        } catch (SQLException e) {
            System.err.println("Erro ao criar profissional: " + e.getMessage());
        }
    }

    public Profissional read(Long id) {
        String sql = "SELECT id_profissional, nome_completo, especialidade FROM TB_CAR_PROFISSIONAL_SAUDE WHERE id_profissional = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Profissional profissional = new Profissional(
                        rs.getLong("id_profissional"),
                        rs.getString("nome_completo"),
                        rs.getString("especialidade")
                );
                return profissional;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar profissional: " + e.getMessage());
        }
        return null;
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE TB_CAR_PROFISSIONAL_SAUDE SET nome_completo = ?, especialidade = ? WHERE id_profissional = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, profissional.getNomeProfissional());
            pstmt.setString(2, profissional.getEspecialidade());
            pstmt.setLong(3, profissional.getIdProfissional());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Profissional com ID " + profissional.getIdProfissional() + " atualizado.");
            } else {
                System.out.println("Nenhum profissional com ID " + profissional.getIdProfissional() + " foi encontrado para atualização.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar profissional: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM TB_CAR_PROFISSIONAL_SAUDE WHERE id_profissional = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Profissional com ID " + id + " deletado.");
            } else {
                System.out.println("Nenhum profissional com ID " + id + " foi encontrado para deletar.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar profissional: " + e.getMessage());
        }
    }

    public List<Profissional> findAll() {
        List<Profissional> profissionais = new ArrayList<>();
        String sql = "SELECT id_profissional, nome_completo, especialidade FROM TB_CAR_PROFISSIONAL_SAUDE";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Profissional profissional = new Profissional(
                        rs.getLong("id_profissional"),
                        rs.getString("nome_completo"),
                        rs.getString("especialidade")
                );
                profissionais.add(profissional);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os profissionais: " + e.getMessage());
        }
        return profissionais;
    }
}
