package br.cursojava.services;

import java.util.List;

import br.cursojava.entity.Setor;
import br.cursojava.exception.MenssageNotFoundException;

public interface SetorService {
    Setor save(Setor setor);

    Setor update(Long id, Setor setor) throws MenssageNotFoundException;

    void delete(Long id) throws MenssageNotFoundException;

    List<Setor> all();

    Setor getById(Long id) throws MenssageNotFoundException;
}
