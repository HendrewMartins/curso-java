package br.cursojava.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.cursojava.entity.Setor;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.repository.SetorRepository;
import br.cursojava.services.SetorService;

@ApplicationScoped
public class DefaultSetorService implements SetorService{

    @Inject
    SetorRepository repository;

    @Transactional
    @Override
    public Setor save(Setor setor) {
        repository.persistAndFlush(setor);
        return setor;
    }

    @Transactional
    @Override
    public Setor update(Long id, Setor setor) throws MenssageNotFoundException {
        Setor update = getById(id);
        update.setDepartamento(setor.getDepartamento());
        update.setNome(setor.getNome());
        return update;
    }

    @Transactional
    @Override
    public void delete(Long id) throws MenssageNotFoundException {
        repository.delete(getById(id));
    }

    @Override
    public List<Setor> all() {
        return repository.listAll();
    }

    @Override
    public Setor getById(Long id) throws MenssageNotFoundException {
        return repository.findByIdOptional(id)
        .orElseThrow(() -> new MenssageNotFoundException("Não foi possivel localizar o Id informado"));
    }

   
    
}
