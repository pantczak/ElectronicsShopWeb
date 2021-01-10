package pl.pas.controllers;

import pl.pas.managers.EventManager;
import pl.pas.managers.UserManager;
import pl.pas.model.Event;
import pl.pas.model.user.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserDetailsController implements Serializable {
    @Inject
    private UserManager userManager;
    @Inject
    private EventManager eventManager;

    private String login;

    private User user;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getUser() {
        return user;
    }

    public void loadUser() throws Exception {
        if (login != null) {
            user = userManager.getUser(login);
            if (user ==null) throw new Exception();
        }
    }

    public List<Event> getUserBorrows() {
        return eventManager.getEventsForClient(user.getUuid());
    }

    public void endBorrow(Event event) throws IOException {
        eventManager.returnDevice(event);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI().concat("?login=" + user.getLogin()));
    }
}
