package pl.pas.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import javax.security.enterprise.identitystore.CredentialValidationResult;
import java.text.ParseException;
import java.util.Date;

public class JWTGeneratorVerifier {

    private static final String SECRET = "09SEVheeEsOTYLDZAJylVmlHb4XadBtgABGKZB5wmKVexgWU";
    private static final long JWT_EXPIRE_TIMEOUT = 15 * 60 * 1000;

    public static String generateJWTString(CredentialValidationResult result) {
        try {
            final JWSSigner signer = new MACSigner(SECRET);

            final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(result.getCallerPrincipal().getName())
                    .claim("role", String.join(",", result.getCallerGroups()))
                    .expirationTime(new Date(new Date().getTime() + JWT_EXPIRE_TIMEOUT))
                    .build();

            final SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
            return "JWT error";
        }
    }

    public static String updateJWTString(String tokenToUpdate) {
        try {
            final JWSSigner signer = new MACSigner(SECRET);
            SignedJWT oldToken = SignedJWT.parse(tokenToUpdate);
            JWTClaimsSet oldClaimSet = oldToken.getJWTClaimsSet();

            final JWTClaimsSet newClaimsSet = new JWTClaimsSet.Builder()
                    .subject(oldClaimSet.getSubject())
                    .claim("role", oldClaimSet.getClaim("role"))
                    .expirationTime(new Date(new Date().getTime() + JWT_EXPIRE_TIMEOUT))
                    .build();

            final SignedJWT newSignedJWT =
                    new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), newClaimsSet);
            newSignedJWT.sign(signer);
            return newSignedJWT.serialize();
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
            return "JWT error";
        }
    }

    public static boolean validateJWT(String tokenToValidate) {
        try {
            JWSObject jwsObject = JWSObject.parse(tokenToValidate);
            JWSVerifier jwsVerifier = new MACVerifier(SECRET);
            return jwsObject.verify(jwsVerifier);
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
            return false;
        }
    }
}
