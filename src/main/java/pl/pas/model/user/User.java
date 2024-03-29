package pl.pas.model.user;

import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.pas.model.Entity;

import java.util.UUID;

public abstract class User extends Entity {

    private String name;
    private String lastName;
    private boolean isActive = true;
    private String login;

    public User( String name, String lastName, String login) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.login = login;
    }

    public User() {
        super();
        login = "";
        isActive = true;
        lastName = "";
        name = "";
    }

    @Override
    public UUID getUuid() {
        return super.getUuid();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("lastName", lastName)
                .append("isActive", isActive)
                .append("login", login)
                .toString();
    }

}
