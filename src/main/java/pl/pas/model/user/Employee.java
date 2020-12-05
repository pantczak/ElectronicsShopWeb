package pl.pas.model.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Employee extends User {
    public Employee(String name, String lastName, String login) {
        super(name, lastName, login);
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
