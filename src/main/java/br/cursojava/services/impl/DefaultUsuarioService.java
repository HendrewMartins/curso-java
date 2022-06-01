package br.cursojava.services.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.cursojava.dto.Token;
import br.cursojava.dto.UsuarioLogin;
import br.cursojava.entity.Usuario;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.repository.UsuarioRepository;
import br.cursojava.services.UsuarioService;
import br.cursojava.util.PBKDF2Encoder;
import br.cursojava.util.TokenUtils;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class DefaultUsuarioService implements UsuarioService {

    @ConfigProperty(name = "br.cursojava.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

    @Inject
    private final UsuarioRepository usuarioRepository;

    @Inject
    private final PBKDF2Encoder encoder;

    public DefaultUsuarioService(UsuarioRepository usuarioRepository, PBKDF2Encoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public Usuario saveUsuario(Usuario usuario) {
        if (!usuarioExiste(usuario.getEmail())) {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            usuarioRepository.persistAndFlush(usuario);
            return usuario;
        }
        return null;
    }

    @Transactional
    @Override
    public Usuario updateUsuario(Long id, Usuario usuario) throws MenssageNotFoundException {
        Usuario update = getUsuarioById(id);
        update.setEmail(usuario.getEmail());
        update.setNome(usuario.getNome());
        update.setRoles(usuario.getRoles());
        update.setSenha(encoder.encode(usuario.getSenha()));
        return update;
    }

    @Transactional
    @Override
    public void deleteUsuario(Long id) throws MenssageNotFoundException {
        usuarioRepository.delete(getUsuarioById(id));
    }

    @Override
    public List<Usuario> allUsuario() {
        return usuarioRepository.listAll();
    }

    @Override
    public Usuario getUsuarioById(Long id) throws MenssageNotFoundException {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("Não foi possivel localizar o Id informado"));
    }

    @Override
    public List<Usuario> getUsuarioByNome(String nome) throws MenssageNotFoundException {
        return usuarioRepository.findByNomeUsuario(nome);
    }

    public boolean usuarioExiste(String email) {
        return (usuarioRepository.count("email", email) > 0);
    }

    @Override
    public Long countUsuario(){
        return usuarioRepository.count();
    }

    @Override
    public List<Usuario> allUsuarioPagination(int pag, int quant) {
        return usuarioRepository.findAll().page(Page.of(pag,quant)).list();
    }

    @Override
    public Response generateToken(UsuarioLogin userLogin) throws Exception{

        Usuario usuario = usuarioRepository.find("email", userLogin.getEmail()).firstResult();
       
        try {
            if (usuario != null && verifyCryptPassword(usuario, userLogin)){
                try {
                    return Response.ok(new Token(TokenUtils.generateToken(usuario.getEmail(), usuario.getRoles(), duration, issuer))).build();
                } catch (Exception e) {
                    return Response.status(Status.UNAUTHORIZED).build();
                }
            } else {
                return Response.status(Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyCryptPassword(Usuario user, UsuarioLogin login){
        return user.getSenha().equals(encoder.encode(login.getSenha()));
    }

}
