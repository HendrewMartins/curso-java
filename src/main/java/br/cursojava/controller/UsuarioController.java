package br.cursojava.controller;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import br.cursojava.entity.Usuario;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.exceptionHandler.ExceptionHandler;
import br.cursojava.services.UsuarioService;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("api/usuario")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    @Inject
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @POST
    @PermitAll
    @Path("/registro")
    @Operation(summary = "Adicionar Usuário", description = "Adicionar um novo Usuário ao sistema")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))))
    public Response registro(@Valid Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return Response.ok(null).build();
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Atualizar um Usuário", description = "Atualizar um Usuário existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @APIResponse(responseCode = "404", description = "Usuário não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    private Usuario updateUsuario(@PathParam("id") Long id, @Valid Usuario usuario) throws MenssageNotFoundException {
        return usuarioService.updateUsuario(id, usuario);
    }

}
