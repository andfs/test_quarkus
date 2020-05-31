package br.com.marketcode.controller;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.marketcode.model.Cliente;
import br.com.marketcode.services.ClienteService;

@Path("/api/cliente/v1/")
 @Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "cliente", description = "CRUD de Cliente")
public class ClienteController {

    @Inject
     ClienteService clienteService;


    @GET
    @Operation(summary = "Retornar todos os clientes")
    public List<Cliente> get() {
        return Cliente.listAll();
    }

    @GET
    @Path("{id}")
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "404", description = "Não encontrado, OPS!")
    @Operation(summary = "Encontrar pelo ID")
    public Cliente getSindle(@PathParam("id") UUID id) {
        Cliente entity = Cliente.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Não encontrado", Status.NOT_FOUND);
        }

        return entity;
    }

    @POST
   
    @Transactional
    @APIResponse(responseCode = "201",
            description = "resource created",
            content = @Content(schema = @Schema(implementation = Cliente.class)))
    @APIResponse(responseCode = "406", description = "Dados inválidos")
    @APIResponse(responseCode = "409", description = "Recursos ja existem")
    @Operation(summary = "Cria um novo recurso")
    public Response create(@Valid Cliente entity) {
        if (entity.id != null) {
            throw new WebApplicationException("Id invalido na requisição",
                    Status.NOT_ACCEPTABLE);
        }

        entity.persist();

        return Response.ok(entity).status(Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @APIResponse(responseCode = "200",
            description = "Cliente criado",
            content = @Content(schema = @Schema(implementation = Cliente.class)))
    @APIResponse(responseCode = "404", description = "Não encontrado")
    @APIResponse(responseCode = "409", description = "Recurso ja existente")
    @Operation(summary = "Editado por id")
    public Response update(@PathParam("id") Long id, @Valid Cliente newEntity) throws Exception {
        
        clienteService.updateCliente(newEntity, id);
        
        return Response.ok().status(Status.OK).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    @APIResponse(responseCode = "204", description = "Deletado")
    @APIResponse(responseCode = "404", description = "Não encontrado")
    @Operation(summary = "Deleta pelo ID")
    public Response delete(@PathParam("id") UUID id) {
        Cliente entity = Cliente.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Não encontrado", Status.NOT_FOUND);
        }

        entity.delete();

        return Response.status(Status.NO_CONTENT).build();
    }
}
