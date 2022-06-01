package br.cursojava.services;


import java.util.List;

import javax.ws.rs.core.Response;

import br.cursojava.dto.UsuarioLogin;
import br.cursojava.entity.Usuario;
import br.cursojava.exception.MenssageNotFoundException;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    Usuario updateUsuario(Long id, Usuario usuario) throws MenssageNotFoundException;

    void deleteUsuario(Long id) throws MenssageNotFoundException;

    List<Usuario> allUsuario();

    Usuario getUsuarioById(Long id) throws MenssageNotFoundException;

    List<Usuario> getUsuarioByNome(String nome) throws MenssageNotFoundException;

    Long countUsuario();

    List<Usuario> allUsuarioPagination(int pag, int quant);

    Response generateToken(UsuarioLogin uLogin) throws Exception;

}
