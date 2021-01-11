package pl.pas.controllers;

import pl.pas.managers.EventManager;
import pl.pas.managers.UserManager;
import pl.pas.model.Event;
import pl.pas.model.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserDetailsController implements Serializable {
    @Inject
    HttpServletRequest request;

    @Inject
    private UserManager userManager;
    @Inject
    private EventManager eventManager;

    private String login;

    public String getLogin() {
        return login;
    }

    @PostConstruct
    public void init() {
        login = request.getRemoteUser();
    }

    public List<Event> getUserBorrows() {
        return eventManager.getEventsForClient(userManager.getUser(login).getUuid());
    }

    public void endBorrow(Event event) throws IOException {
        eventManager.returnDevice(event);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
