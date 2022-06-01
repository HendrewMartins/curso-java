package br.cursojava.repository;

import javax.enterprise.context.ApplicationScoped;

import br.cursojava.entity.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente>{
    
}
