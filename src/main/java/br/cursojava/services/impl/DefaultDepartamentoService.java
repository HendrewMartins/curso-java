package br.cursojava.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.cursojava.entity.Departamento;
import br.cursojava.entity.DepartamentoId;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.repository.DepartamentoRepository;
import br.cursojava.services.DepartamentoService;

@ApplicationScoped
public class DefaultDepartamentoService implements DepartamentoService{

    @Inject
    DepartamentoRepository repository;

    @Override
    public Departamento save(Departamento departamento) {
        departamento.getId().setEmpresa(departamento.getEmpresa());
        repository.persistAndFlush(departamento);
        return departamento;
    }

    @Override
    public Departamento update(DepartamentoId id, Departamento departamento) throws MenssageNotFoundException {
        Departamento update = getById(id);
        update.setEmpresa(departamento.getEmpresa());
        update.setNome(departamento.getNome());
        return update;
    }

    @Override
    public void delete(DepartamentoId id) throws MenssageNotFoundException {
        repository.delete(getById(id));
    }

    @Override
    public List<Departamento> all() {
        return repository.listAll();
    }

    @Override
    public Departamento getById(DepartamentoId id) throws MenssageNotFoundException {
        return repository.find("id", id).singleResult();
    }
    
}
