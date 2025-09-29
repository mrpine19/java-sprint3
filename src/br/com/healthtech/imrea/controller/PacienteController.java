package br.com.healthtech.imrea.controller;

import br.com.healthtech.imrea.dao.PacienteDAO;
import br.com.healthtech.imrea.model.Paciente;

import java.util.List;

public class PacienteController {
    private PacienteDAO pacienteDAO;

    public PacienteController() {
        this.pacienteDAO = new PacienteDAO();
    }

    public void adicionar(Paciente paciente) {
        pacienteDAO.create(paciente);
    }

    public Paciente buscarPorId(Long id) {
        return pacienteDAO.read(id);
    }

    public List<Paciente> listarTodos() {
        return pacienteDAO.findAll();
    }

    public void atualizar(Paciente paciente) {
        pacienteDAO.update(paciente);
    }

    public void deletar(Long id) {
        pacienteDAO.delete(id);
    }
}