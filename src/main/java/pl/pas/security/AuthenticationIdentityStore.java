package pl.pas.security;

import pl.pas.managers.UserManager;
import pl.pas.model.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Collections;
import java.util.HashSet;

@ApplicationScoped
public class AuthenticationIdentityStore implements IdentityStore {

    @Inject
    UserManager usersManager;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePasswordCredential =
                    (UsernamePasswordCredential) credential;
            User user = usersManager
                    .getUserByLoginPassword(usernamePasswordCredential.getCaller(),
                            usernamePasswordCredential.getPasswordAsString());
            if (user != null) {
                return new CredentialValidationResult(user.getLogin(), new HashSet<>(
                        Collections.singletonList(user.getRole())));
            }
        }
        return CredentialValidationResult.INVALID_RESULT;
    }

}
