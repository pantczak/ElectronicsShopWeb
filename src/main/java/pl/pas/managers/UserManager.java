package pl.pas.managers;

import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class UserManager implements Serializable {
    @Inject
    private IUserRepository userRepository;

    public UserManager() {
    }

    public UserManager(IUserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public boolean addClient(String login, String name, String lastName, int age) {
        if (login == null || name == null || lastName == null || age < 0) {
            return false;
        }
        return userRepository.addUser(new Client(name, lastName, login, age));
    }

    public boolean addEmployee(String login, String name, String lastName) {
        if (login == null || name == null || lastName == null) {
            return false;
        }
        return userRepository.addUser(new Employee(name, lastName, login));
    }

    public boolean addAdministrator(String login, String name, String lastName) {
        if (login == null || name == null || lastName == null) {
            return false;
        }
        return userRepository.addUser(new Administrator(name, lastName, login));
    }

    public User getUser(UUID uuid) {
        return userRepository.getUser(uuid);
    }

    public User getUser(String login) {
        if (login == null) {
            return null;
        }
        return userRepository.getUser(login);
    }

    public List<Client> getAllClients() {
        return userRepository.getAllClients();
    }

    public List<User> getAllEmployees() {
        return userRepository.getAllEmployees();
    }

    public List<User> getAllAdministrators() {
        return userRepository.getAllAdministrators();
    }

    public boolean updateUser(User old, String login, String name, String lastName) {
        if (old == null || userRepository.getUser(old.getUuid()) == null
                || login == null || name == null || lastName == null) {
            return false;
        }
        if (old instanceof Employee) {
            userRepository.updateUser(old.getUuid(), new Employee(name, lastName, login));
        } else if (old instanceof Administrator) {
            userRepository.updateUser(old.getUuid(), new Administrator(name, lastName, login));
        }
        return true;
    }

    public boolean updateClient(User old, String login, String name, String lastName, int age) {
        if (old == null || userRepository.getUser(old.getUuid()) == null
                || login == null || name == null || lastName == null || age > 0 || !(old instanceof Client)) {
            return false;
        }
        userRepository.updateUser(old.getUuid(), new Client(name, lastName, login, age));
        return true;
    }

    public boolean activateUser(User user){
        if (user == null){
            return false;
        }
        user.setActive(true);
        return true;
    }

    public boolean deactivateUser(User user) {
        if (user == null) {
            return false;
        }
        user.setActive(false);
        return true;
    }

}
