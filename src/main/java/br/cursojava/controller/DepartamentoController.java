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

import br.cursojava.entity.Departamento;
import br.cursojava.entity.DepartamentoId;
import br.cursojava.entity.Empresa;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.exceptionHandler.ExceptionHandler;
import br.cursojava.services.DepartamentoService;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("api/departamento")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartamentoController {

    @Inject
    public DepartamentoService service;

    @POST
    @RolesAllowed("ADMIN")
    @Operation(summary = "Adicionar Departamento", description = "Adicionar um novo Departamento ao sistema")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))))
    public Departamento save(@Valid Departamento objeto) {
        return service.save(objeto);
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}/{empresaId}")
    @Operation(summary = "Atualizar um Departamento", description = "Atualizar um Departamento existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))),
            @APIResponse(responseCode = "404", description = "Departamento não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Departamento update(@PathParam("id") Long id, @PathParam("empresaId") Long empresaId, @Valid Departamento objeto) throws MenssageNotFoundException {
         return service.update(DepartamentoId.builder()
        .id(id)
        .empresa(Empresa.builder().id(empresaId).build())
        .build(), objeto);
    }

    @GET
    @RolesAllowed("ADMIN")
    @Operation(summary = "Lista Departamento", description = "Buscar todos os Departamento")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))))
    public List<Departamento> getAll() {
        return service.all();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}/{empresaId}")
    @Operation(summary = "Buscar Departamento", description = "Buscar Departamento por Id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))),
            @APIResponse(responseCode = "404", description = "Departamento não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Departamento getById(@PathParam("id") Long id,  @PathParam("empresaId") Long empresaId)  throws MenssageNotFoundException {
        return service.getById(DepartamentoId.builder()
        .id(id)
        .empresa(Empresa.builder().id(empresaId).build())
        .build());
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}/{empresaId}")
    @Operation(summary = "Deletar um Departamento", description = "Apagar um Departamento existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
            @APIResponse(responseCode = "404", description = "Departamento não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public boolean update(@PathParam("id") Long id,  @PathParam("empresaId") Long empresaId) throws MenssageNotFoundException {
        service.delete(DepartamentoId.builder()
        .id(id)
        .empresa(Empresa.builder().id(empresaId).build())
        .build());
        return true;
    }
    
}
