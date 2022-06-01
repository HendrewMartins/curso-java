package br.cursojava.repository;

import javax.enterprise.context.ApplicationScoped;

import br.cursojava.entity.Setor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class SetorRepository implements PanacheRepository<Setor>{
    
}
