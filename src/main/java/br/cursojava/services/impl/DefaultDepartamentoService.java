package br.cursojava.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.cursojava.entity.Departamento;
import br.cursojava.entity.DepartamentoId;
import br.cursojava.entity.Empresa;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.repository.DepartamentoRepository;
import br.cursojava.services.DepartamentoService;
import br.cursojava.services.EmpresaService;

@ApplicationScoped
public class DefaultDepartamentoService implements DepartamentoService {

    @Inject
    DepartamentoRepository repository;

    @Inject
    EmpresaService empresaService;

    @Transactional
    @Override
    public Departamento save(Departamento departamento) {
        departamento.getId().setEmpresa(departamento.getEmpresa());
        repository.persistAndFlush(departamento);
        return departamento;
    }

    @Transactional
    @Override
    public Departamento update(Long id, Long empresaId, Departamento departamento) throws MenssageNotFoundException {
        Departamento update = getById(id, empresaId);
        update.setNome(departamento.getNome());
        return update;
    }

    @Transactional
    @Override
    public void delete(Long id, Long empresaId) throws MenssageNotFoundException {
        repository.delete(getById(id, empresaId));
    }

    @Override
    public List<Departamento> all() {
        return repository.listAll();
    }

    @Override
    public Departamento getById(Long id, Long empresaId) throws MenssageNotFoundException {
        Empresa empresa = empresaService.getEmpresaById(empresaId);
        DepartamentoId departamentoId = DepartamentoId.builder()
                .id(id)
                .empresa(empresa)
                .build();

        return repository.find("id", departamentoId).singleResult();
    }

}
