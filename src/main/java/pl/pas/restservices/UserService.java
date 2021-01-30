package pl.pas.restservices;

import pl.pas.managers.UserManager;
import pl.pas.security.EntityIdentitySignerVerifier;
import pl.pas.security.EntityToSign;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.lang.reflect.InvocationTargetException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserService {

    @Inject
    UserManager userManager;

    @GET
    @Path("_self")
    public Response findSelf(@Context SecurityContext securityContext) {
        return Response.status(Response.Status.OK).entity(userManager.
                getUser(securityContext.getUserPrincipal().getName())).build();
    }

    @GET
    @Path("{uuid}")
    public Response getUser(@PathParam("uuid") String uuid) {
        try {
            return Response.status(Response.Status.OK).header("ETag", EntityIdentitySignerVerifier.calculateETag((EntityToSign) userManager.getUser(uuid)))
                    .entity(userManager.getUser(uuid)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    public Response getAllUsers() {
        return Response.status(200).entity(userManager.getAll())
                .build();
    }



//    @PUT
//    @Path("/admin/{uuid}")
//    @EntitySignatureValidatorFilterBinding
//    public Response updateAdmin(@PathParam("uuid") String uuid, @HeaderParam("If-Match") String header, Admin admin) {
//        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(header, admin)) {
//            return Response.status(406).build();
//        }
//        try {
//            validation(admin);
//            BeanUtils.copyProperties(usersManager.findByKey(uuid), admin);
//        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
//            e.printStackTrace();
//            return Response.status(422).build();
//        }
//        return Response.status(200).build();
//    }



}
