package br.cursojava.repository;

import javax.enterprise.context.ApplicationScoped;

import br.cursojava.entity.Departamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class DepartamentoRepository implements PanacheRepository<Departamento> {
    
}
