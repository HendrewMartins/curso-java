package br.cursojava.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.cursojava.entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public List<Usuario> findByNomeUsuario(String nome){
      return find("nome", nome).list();
    }
    
}
