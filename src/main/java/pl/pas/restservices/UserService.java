package pl.pas.restservices;

import pl.pas.managers.UserManager;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserService {

    @Inject
    UserManager userManager;

    @GET
    @Path("/me")
    public Response findSelf(@Context SecurityContext securityContext) {
        return Response.status(Response.Status.OK).entity(userManager.
                getUser(securityContext.getUserPrincipal().getName())).build();
    }

    @GET
    @Path("/{uuid}")
    public Response getUser(@PathParam("uuid") String uuid) {
        return Response.status(Response.Status.OK).entity(userManager.getUser(UUID.fromString(uuid))).build();
    }

    @GET
    public Response getAllUsers() {
        return Response.status(Response.Status.OK).entity(userManager.getAll())
                .build();
    }


    @PUT
    @Path("/admin/{uuid}")
    public Response updateAdministrator(@PathParam("uuid") String uuid, Administrator admin) {
        if (userManager.updateUser(userManager.getUser(UUID.fromString(uuid)), admin.getLogin(), admin.getName(), admin.getLastName())) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(422).build();
    }

    @PUT
    @Path("/employee/{uuid}")
    public Response updateEmployee(@PathParam("uuid") String uuid, Employee employee) {
        if (userManager.updateUser(userManager.getUser(UUID.fromString(uuid)), employee.getLogin(), employee.getName(), employee.getLastName())) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(422).build();

    }

    @PUT
    @Path("/client/{uuid}")
    public Response updateClient(@PathParam("uuid") String uuid, Client client) {
        if (userManager.updateClient(userManager.getUser(UUID.fromString(uuid)), client.getLogin(), client.getName(), client.getLastName(), client.getAge())) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(422).build();
    }


    @POST
    @Path("/admin")
    public Response createAdmin(Administrator admin) {
        if (userManager.addAdministrator(admin)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(422).build();
    }

    @POST
    @Path("/superUser")
    public Response createEmployee(Employee employee) {
        if (userManager.addEmployee(employee)) {
            return Response.status(Response.Status.CREATED).build();
        }

        return Response.status(422).build();
    }

    @POST
    @Path("/client")
    public Response createClient(Client client) {
        if (userManager.addClient(client)) {
            return Response.status(201).build();
        }
        return Response.status(422).build();
    }


}
