package pl.pas.controllers;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@RequestScoped
@Named
public class LogInController implements Serializable {
    @Inject
    private HttpServletRequest request;

    private String username;
    private String password;

    public String login() {
        try {
            request.login(username, password);
            return "menu";
        } catch (ServletException e) {
            return "auth_err";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
