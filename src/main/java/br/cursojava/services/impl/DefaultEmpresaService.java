package br.cursojava.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.cursojava.entity.Empresa;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.repository.EmpresaRepository;
import br.cursojava.services.EmpresaService;

@ApplicationScoped
public class DefaultEmpresaService implements EmpresaService {

    @Inject
    public EmpresaRepository repository;

    @Override
    public Empresa saveEmpresa(Empresa empresa) {
        repository.persistAndFlush(empresa);
        return empresa;
    }

    @Override
    public Empresa updateEmpresa(Long id, Empresa usuario) throws MenssageNotFoundException {
        Empresa update = getEmpresaById(id);
        update.setRazaoSocial(usuario.getRazaoSocial());
        return update;
    }

    @Override
    public void deleteEmpresa(Long id) throws MenssageNotFoundException {
        repository.delete(getEmpresaById(id));
    }

    @Override
    public List<Empresa> allEmpresa() {
        return repository.listAll();
    }

    @Override
    public Empresa getEmpresaById(Long id) throws MenssageNotFoundException {
        return repository.findByIdOptional(id)
        .orElseThrow(() -> new MenssageNotFoundException("Não foi possivel localizar o Id informado"));
    }
    
}
