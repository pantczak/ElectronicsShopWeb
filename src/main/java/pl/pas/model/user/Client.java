package pl.pas.model.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class Client extends User {
    private int age;

    public Client(String name, String lastName, String login, String password, int age) {
        super(name, lastName, login, password);
        this.age = age;
    }

    public Client() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("age", age)
                .toString();
    }

    @Override
    public String getRole() {
        return "Client";
    }
}
