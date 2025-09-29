package br.com.healthtech.imrea.controller;

import br.com.healthtech.imrea.dao.ConsultaDAO;
import br.com.healthtech.imrea.model.Consulta;

import java.util.List;

public class ConsultaController {
    private ConsultaDAO consultaDAO;

    public ConsultaController() {
        this.consultaDAO = new ConsultaDAO();
    }

    public void adicionar(Consulta consulta) {
        consultaDAO.create(consulta);
    }

    public Consulta buscarPorId(Long id) {
        return consultaDAO.read(id);
    }

    public List<Consulta> listarTodos() {
        return consultaDAO.findAll();
    }

    public void atualizar(Consulta consulta) {
        consultaDAO.update(consulta);
    }

    public void deletar(Long id) {
        consultaDAO.delete(id);
    }
}