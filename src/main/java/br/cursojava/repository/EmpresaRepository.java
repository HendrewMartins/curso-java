package br.cursojava.repository;

import javax.enterprise.context.ApplicationScoped;

import br.cursojava.entity.Empresa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class EmpresaRepository implements PanacheRepository<Empresa>{
    
}
