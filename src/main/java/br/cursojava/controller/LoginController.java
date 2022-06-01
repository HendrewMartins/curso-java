package br.cursojava.controller;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.cursojava.dto.UsuarioLogin;
import br.cursojava.services.UsuarioService;

@Path("api/login")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {
    
    @Inject
    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @POST
    @PermitAll
    @Path("/auth")
    public Response login(@Valid UsuarioLogin uLogin) throws Exception{
        return usuarioService.generateToken(uLogin);
    }
}
