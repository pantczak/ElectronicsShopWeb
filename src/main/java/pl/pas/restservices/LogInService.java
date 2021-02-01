package pl.pas.restservices;


import com.nimbusds.jwt.SignedJWT;
import pl.pas.managers.UserManager;
import pl.pas.security.JWTAuthenticationMechanism;
import pl.pas.security.JWTGeneratorVerifier;
import pl.pas.security.LoginData;

import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)

@Path("/auth")
public class LogInService {
    @Inject
    IdentityStoreHandler identityStoreHandler;

    @Inject
    private UserManager usersManager;


    @POST
    public Response logIn(LoginData loginData) {
        Credential credential = new UsernamePasswordCredential(loginData.getLogin(),
                new Password(loginData.getPassword()));
        CredentialValidationResult result = identityStoreHandler.validate(credential);
        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            return Response.status(202)
                    .type("application/jwt")
                    .entity(JWTGeneratorVerifier.generateJWTString(result))
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/update")
    public Response updateToken(@Context HttpServletRequest httpServletRequest) {
        String authHeader = httpServletRequest.getHeader(JWTAuthenticationMechanism.AUTHORIZATION);
        String tokenToUpdate = authHeader.substring(JWTAuthenticationMechanism.BEARER.length());
        try {
            String login = SignedJWT.parse(tokenToUpdate).getJWTClaimsSet().getSubject();
            if (usersManager.isUserActive(login)) {
                return Response.status(202)
                        .type("application/jwt")
                        .entity(JWTGeneratorVerifier.updateJWTString(tokenToUpdate))
                        .build();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }



}
