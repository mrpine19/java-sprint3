package br.com.healthtech.imrea.controller;

import br.com.healthtech.imrea.dao.CuidadorDAO;
import br.com.healthtech.imrea.model.Cuidador;

import java.util.List;

public class CuidadorController {
    private CuidadorDAO cuidadorDAO;

    public CuidadorController() {
        this.cuidadorDAO = new CuidadorDAO();
    }

    public void adicionar(Cuidador cuidador) {
        cuidadorDAO.create(cuidador);
    }

    public Cuidador buscarPorId(Long id) {
        return cuidadorDAO.read(id);
    }

    public List<Cuidador> listarTodos() {
        return cuidadorDAO.findAll();
    }

    public void atualizar(Cuidador cuidador) {
        cuidadorDAO.update(cuidador);
    }

    public void deletar(Long id) {
        cuidadorDAO.delete(id);
    }
}