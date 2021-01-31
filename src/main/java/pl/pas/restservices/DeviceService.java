package pl.pas.restservices;


import pl.pas.managers.DeviceManager;
import pl.pas.model.resource.Laptop;
import pl.pas.model.resource.Smartphone;
import pl.pas.security.EntityIdentitySignerVerifier;
import pl.pas.security.EntityToSign;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/devices")
public class DeviceService {

    @Inject
    private DeviceManager deviceManager;

    @GET
    @Path("{uuid}")
    public Response getDevice(@PathParam("uuid") String uuid) {
        try {
            return Response.status(Response.Status.OK)
                    .header("ETag", EntityIdentitySignerVerifier.calculateETag((EntityToSign) deviceManager.getDevice(UUID.fromString(uuid))))
                    .entity(deviceManager.getDevice(UUID.fromString(uuid))).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @GET
    public Response getAllDevices() {
        return Response.status(200).entity(deviceManager.getAllDevices()).build();
    }

    @PUT
    @Path("/laptop/{uuid}")
    public Response updateLaptop(@PathParam("uuid") String uuid, @HeaderParam("If-Match") String header,
                                 Laptop laptop) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(header, (EntityToSign) laptop)) {
            return Response.status(406).build();
        }
        if (deviceManager.updateLaptop(deviceManager.getDevice(UUID.fromString(uuid)), laptop.getBrand(), laptop.getModel(), laptop.getWeightInGrams(), laptop.getMemoryInGb())) {
            return Response.status(200).build();
        }
        return Response.status(422).build();

    }

    @PUT
    @Path("/smartphone/{uuid}")
    public Response updateSmartphone(@PathParam("uuid") String uuid, @HeaderParam("If-Match") String header,
                                     Smartphone smartphone
    ) {
        if (EntityIdentitySignerVerifier.verifyEntityIntegrity(header, (EntityToSign) smartphone)) {
            if (deviceManager.updateSmartphone(deviceManager.getDevice(UUID.fromString(uuid)), smartphone.getBrand(), smartphone.getModel(), smartphone.getWeightInGrams(), smartphone.getBatteryLifetime())) {
                return Response.status(200).build();
            }
            return Response.status(422).build();
        } else {
            return Response.status(406).build();
        }

    }

    @POST
    @Path("/laptop")
    public Response createLaptop(Laptop laptop) {
         if(deviceManager.addLaptop(laptop)){
             return Response.status(201).build();
         }
        return Response.status(422).build();
    }

    @POST
    @Path("/smartphone")
    public Response createSmartphone(Smartphone smartphone) {
        if(deviceManager.addSmartphone(smartphone)){
            return Response.status(201).build();
        }
        return Response.status(422).build();
    }


    @DELETE
    @Path("{uuid}")
    public Response deleteBabysitter(@PathParam("uuid") String uuid) {
        if (deviceManager.deleteDevice(UUID.fromString(uuid))) {
            return Response.status(204).build();
        }
        return Response.status(422).build();

    }

}
