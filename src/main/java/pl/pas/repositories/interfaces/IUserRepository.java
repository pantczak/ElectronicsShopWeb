package pl.pas.repositories.interfaces;

import pl.pas.model.user.Client;
import pl.pas.model.user.User;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
    boolean addUser(User user);

    User getUser(UUID uuid);

    User getUser(String login);

    void updateUser(UUID uuid, User newUser);

    List<Client> getAllClients();

    List<User> getAllEmployees();

    List<User> getAllAdministrators();
}
