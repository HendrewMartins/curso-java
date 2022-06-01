package br.cursojava.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import br.cursojava.entity.Cliente;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.exceptionHandler.ExceptionHandler;
import br.cursojava.services.ClienteService;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("api/cliente")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    public ClienteService service;

    @POST
    @RolesAllowed("ADMIN")
    @Operation(summary = "Adicionar Cliente", description = "Adicionar um novo Cliente ao sistema")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))))
    public Cliente save(@Valid Cliente objeto) {
        return service.save(objeto);
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Atualizar um Cliente", description = "Atualizar um Cliente existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @APIResponse(responseCode = "404", description = "Cliente não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Cliente update(@PathParam("id") Long id, @Valid Cliente objeto) throws MenssageNotFoundException {
        return service.update(id, objeto);
    }

    @GET
    @RolesAllowed("ADMIN")
    @Operation(summary = "Lista Cliente", description = "Buscar todos os Cliente")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))))
    public List<Cliente> getAll() {
        return service.all();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Buscar Cliente", description = "Buscar Cliente por Id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @APIResponse(responseCode = "404", description = "Cliente não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Cliente getById(@PathParam("id") Long id)  throws MenssageNotFoundException {
        return service.getById(id);
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Deletar um Cliente", description = "Apagar um Cliente existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
            @APIResponse(responseCode = "404", description = "Cliente não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public boolean update(@PathParam("id") Long id) throws MenssageNotFoundException {
        service.delete(id);
        return true;
    }
    
}
