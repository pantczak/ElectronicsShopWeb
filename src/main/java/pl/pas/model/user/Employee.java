package pl.pas.model.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Employee extends User {
    public Employee(String name, String lastName, String login, String password) {
        super(name, lastName, login, password);
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

    @Override
    public String getRole() {
        return "Employee";
    }
}
