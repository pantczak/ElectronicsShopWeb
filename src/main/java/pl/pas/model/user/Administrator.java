package pl.pas.model.user;

import java.util.UUID;

public class Administrator extends User {
    public Administrator(String name, String lastName, String login, String password) {
        super(name, lastName, login, password);
    }

    public Administrator() {
        super();
    }

    @Override
    public String getRole() {
        return "Administrator";
    }
}
