package br.cursojava.services;

import java.util.List;

import br.cursojava.entity.Cliente;
import br.cursojava.exception.MenssageNotFoundException;

public interface ClienteService {
    Cliente save(Cliente cliente);

    Cliente update(Long id, Cliente cliente) throws MenssageNotFoundException;

    void delete(Long id) throws MenssageNotFoundException;

    List<Cliente> all();

    Cliente getById(Long id) throws MenssageNotFoundException;
}
