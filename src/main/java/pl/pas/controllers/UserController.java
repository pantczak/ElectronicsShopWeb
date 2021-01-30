package pl.pas.controllers;

import pl.pas.managers.UserManager;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.UUID;

@Named
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserManager userManager;

    private Client currentClient;
    private User currentUser;
    private Client newClient;
    private Employee newEmployee;
    private Administrator newAdministrator;
    private UUID userId;
    private String login;

    public Client getNewClient() {
        return newClient;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewClient(Client newClient) {
        this.newClient = newClient;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public void setNewAdministrator(Administrator newAdministrator) {
        this.newAdministrator = newAdministrator;
    }

    public Administrator getNewAdministrator() {
        return newAdministrator;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public UserController() {
        this.newClient = new Client();
        this.newEmployee = new Employee();
        this.newAdministrator = new Administrator();
    }

    public String processNewClient() {
        userManager.addClient(newClient.getLogin(), newClient.getName(), newClient.getLastName(), newClient.getPassword(), newClient.getAge());
        newClient = new Client();
        return "menu";
    }

    public String processNewEmployee() {
        userManager.addEmployee(newEmployee.getLogin(), newEmployee.getName(), newEmployee.getLastName(), newEmployee.getPassword());
        newEmployee = new Employee();
        return "menu";
    }

    public String processNewAdministrator() {
        userManager.addAdministrator(newAdministrator.getLogin(), newAdministrator.getName(), newAdministrator.getLastName(), newAdministrator.getPassword());
        newAdministrator = new Administrator();
        return "menu";
    }

    public String cancelNewClient() {
        newClient = new Client();
        return "menu";
    }

    public String cancelNewEmployee() {
        newEmployee = new Employee();
        return "menu";
    }

    public String cancelNewAdministrator() {
        newAdministrator = new Administrator();
        return "menu";
    }

    public String changeActivity(User user) {
        if (user.isActive()) {
            userManager.deactivateUser(user);
        } else {
            userManager.activateUser(user);
        }
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public String searchId(UUID uuid) {
        User user = userManager.getUser(uuid);
        if (user instanceof Client) {
            currentClient = (Client) user;
            return "client";
        } else {
            currentUser = user;
            return "user";
        }
    }

    public String searchLogin(String login) {
        User user = userManager.getUser(login);
        if (user instanceof Client) {
            currentClient = (Client) user;
            return "client";
        } else {
            currentUser = user;
            return "user";
        }
    }

    public String updateClient() {
        userManager.updateClient(currentClient, currentClient.getLogin(), currentClient.getName(),
                currentClient.getLastName(), currentClient.getPassword(), currentClient.getAge());
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";

    }

    public String updateUser() {
        userManager.updateUser(currentUser, currentUser.getLogin(), currentUser.getName(),
                currentUser.getLastName(), currentUser.getPassword());
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";

    }


}
