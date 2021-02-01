package pl.pas.restservices;


import pl.pas.managers.DeviceManager;
import pl.pas.managers.EventManager;
import pl.pas.managers.UserManager;
import pl.pas.model.resource.Device;
import pl.pas.model.user.Client;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/events")
public class EventService {

    @Inject
    private EventManager eventManager;

    @Inject
    private UserManager userManager;

    @Inject
    private DeviceManager deviceManager;

    @GET
    public Response getAllEvents(@Context SecurityContext securityContext) {
        return Response.status(Response.Status.OK)
                .entity(eventManager.getEventsForClient(userManager.getUser(securityContext.getUserPrincipal().getName()).getUuid()))
                .build();
    }

    @POST
    @Path("{uuid}")
    public Response borrowDevice(@Context SecurityContext securityContext,
                                 @PathParam("uuid") String uuid) {
        Client client =
                (Client) userManager.getUser(securityContext.getUserPrincipal().getName());
        Device device = deviceManager.getDevice(UUID.fromString(uuid));

        if (eventManager.borrowDevice(device.getUuid(), client.getUuid())) {
            return Response.status(201).build();
        }
        return Response.status(422).build();
    }



}
