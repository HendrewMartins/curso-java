package br.cursojava.services;

import java.util.List;

import br.cursojava.entity.Departamento;
import br.cursojava.entity.DepartamentoId;
import br.cursojava.exception.MenssageNotFoundException;

public interface DepartamentoService {
    
    Departamento save(Departamento departamento);

    Departamento update(DepartamentoId id, Departamento departamento) throws MenssageNotFoundException;

    void delete(DepartamentoId id) throws MenssageNotFoundException;

    List<Departamento> all();

    Departamento getById(DepartamentoId id) throws MenssageNotFoundException;
}
