package br.cursojava.services;

import java.util.List;

import br.cursojava.entity.Departamento;
import br.cursojava.exception.MenssageNotFoundException;

public interface DepartamentoService {
    
    Departamento save(Departamento departamento);

    Departamento update(Long id, Long empresaId, Departamento departamento) throws MenssageNotFoundException;

    void delete(Long id, Long empresaId) throws MenssageNotFoundException;

    List<Departamento> all();

    Departamento getById(Long id, Long empresaId) throws MenssageNotFoundException;
}
