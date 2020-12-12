package pl.pas.controllers;

import pl.pas.managers.EventManager;
import pl.pas.model.Event;
import pl.pas.model.resource.Laptop;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Named
@ApplicationScoped
public class EventController implements Serializable {

    @Inject
    private EventManager eventManager;

    private UUID deviceId;
    private UUID clientId;
    private Date borrowDate;
    private UUID eventId;
    private Event currentEvent;

    public EventController(Date borrowDate) {
        this.borrowDate = new Date();
    }

    public EventController() {

    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public String processEvent() {
        this.eventManager.borrowDevice(deviceId, clientId, borrowDate);
        this.deviceId = null;
        this.clientId = null;
        this.borrowDate = new Date();
        return "main";
    }

    public String search() {
        currentEvent = eventManager.getEvent(eventId);
        return "event";
    }

    public String returnDevice(Event event) {
        eventManager.returnDevice(event);
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }
}
