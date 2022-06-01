package br.cursojava.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.cursojava.entity.Cliente;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.services.ClienteService;

@ApplicationScoped
public class DefaultClienteService implements ClienteService{

    @Override
    public Cliente save(Cliente cliente) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cliente update(Long id, Cliente cliente) throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Cliente> all() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cliente getById(Long id) throws MenssageNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
