package br.cursojava.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
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

import br.cursojava.entity.Empresa;
import br.cursojava.exception.MenssageNotFoundException;
import br.cursojava.exceptionHandler.ExceptionHandler;
import br.cursojava.services.EmpresaService;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("api/empresa")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaController {

    public EmpresaService service;

    @POST
    @RolesAllowed("ADMIN")
    @Operation(summary = "Adicionar Empresa", description = "Adicionar um novo Empresa ao sistema")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empresa.class))))
    public Empresa saveEmpresa(@Valid Empresa empresa) {
        return service.saveEmpresa(empresa);
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Atualizar um Empresa", description = "Atualizar um Empresa existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empresa.class))),
            @APIResponse(responseCode = "404", description = "Empresa não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Empresa updateEmpresa(@PathParam("id") Long id, @Valid Empresa empresa) throws MenssageNotFoundException {
        return service.updateEmpresa(id, empresa);
    }

    @GET
    @RolesAllowed("ADMIN")
    @Operation(summary = "Lista Empresa", description = "Buscar todos os Empresa")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empresa.class))))
    public List<Empresa> getEmpresa() {
        return service.allEmpresa();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Buscar Empresa", description = "Buscar Empresa por Id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empresa.class))),
            @APIResponse(responseCode = "404", description = "Empresa não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public Empresa getEmpresaById(@PathParam("id") Long id)  throws MenssageNotFoundException {
        return service.getEmpresaById(id);
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(summary = "Deletar um Empresa", description = "Apagar um Empresa existente via id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
            @APIResponse(responseCode = "404", description = "Empresa não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public boolean updateEmpresa(@PathParam("id") Long id) throws MenssageNotFoundException {
        service.deleteEmpresa(id);
        return true;
    }

    
}
