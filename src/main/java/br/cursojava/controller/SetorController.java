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

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.cursojava.entity.Setor;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.exceptionHandler.ExceptionHandler;
import br.cursojava.services.SetorService;


@Path("api/setor")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SetorController {
 
    @Inject
    public SetorService service;

    @POST
    @RolesAllowed("ADMIN")
    @Operation(summary = "Adicionar Setor", description = "Adicionar um novo Setor ao sistema")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Setor.class))))
    public Setor save(@Valid Setor objeto) {
        return service.save(objeto);
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Atualizar um Setor", description = "Atualizar um Setor existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Setor.class))),
            @APIResponse(responseCode = "404", description = "Setor não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Setor update(@PathParam("id") Long id, @Valid Setor objeto) throws MenssageNotFoundException {
        return service.update(id, objeto);
    }

    @GET
    @RolesAllowed("ADMIN")
    @Operation(summary = "Lista Setor", description = "Buscar todos os Setor")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Setor.class))))
    public List<Setor> getAll() {
        return service.all();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Buscar Setor", description = "Buscar Setor por Id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Setor.class))),
            @APIResponse(responseCode = "404", description = "Setor não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Setor getById(@PathParam("id") Long id)  throws MenssageNotFoundException {
        return service.getById(id);
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Deletar um Setor", description = "Apagar um Setor existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
            @APIResponse(responseCode = "404", description = "Setor não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public boolean update(@PathParam("id") Long id) throws MenssageNotFoundException {
        service.delete(id);
        return true;
    }

}
