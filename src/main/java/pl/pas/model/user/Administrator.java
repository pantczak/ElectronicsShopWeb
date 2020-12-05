package pl.pas.model.user;

import java.util.UUID;

public class Administrator extends User {
    public Administrator( String name, String lastName, String login) {
        super(name, lastName, login);
    }

    public Administrator() {
        super();
    }
}
