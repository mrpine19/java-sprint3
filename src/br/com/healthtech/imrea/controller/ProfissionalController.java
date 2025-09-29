package br.com.healthtech.imrea.controller;

import br.com.healthtech.imrea.dao.ProfissionalDAO;
import br.com.healthtech.imrea.model.Profissional;

import java.util.List;

public class ProfissionalController {
    private ProfissionalDAO profissionalDAO;

    public ProfissionalController() {
        this.profissionalDAO = new ProfissionalDAO();
    }

    public void adicionar(Profissional profissional) {
        profissionalDAO.create(profissional);
    }

    public Profissional buscarPorId(Long id) {
        return profissionalDAO.read(id);
    }

    public List<Profissional> listarTodos() {
        return profissionalDAO.findAll();
    }

    public void atualizar(Profissional profissional) {
        profissionalDAO.update(profissional);
    }

    public void deletar(Long id) {
        profissionalDAO.delete(id);
    }
}