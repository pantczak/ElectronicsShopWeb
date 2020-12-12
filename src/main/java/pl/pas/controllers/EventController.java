package pl.pas.controllers;

import pl.pas.managers.EventManager;
import pl.pas.model.Event;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Named
@SessionScoped
public class EventController implements Serializable {

    @Inject
    private EventManager eventManager;

    private UUID clientId;
    private UUID deviceId;
    private Date borrowDate;
    private UUID eventUuid;
    private Event currentEvent;
    private List<Event> currentEvents;

    public EventController(Date borrowDate) {
        this.borrowDate = new Date();
    }

    public EventController() {

    }

    @PostConstruct
    public void initList() {
        currentEvents = eventManager.getAllEvents();
    }

    public List<Event> getCurrentEvents() {
        return currentEvents;
    }

    public void setCurrentEvents(List<Event> currentEvents) {
        this.currentEvents = currentEvents;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public UUID getEventUuid() {
        return eventUuid;
    }

    public void setEventUuid(UUID eventUuid) {
        this.eventUuid = eventUuid;
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

    public String search(UUID eventId) {
        if (eventId ==null){
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            return viewId + "?faces-redirect=true";
        }
        currentEvent = eventManager.getEvent(eventId);
        return "event";
    }

    public String returnDevice(Event event) {
        eventManager.returnDevice(event);
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String deleteEvent(Event event) {
        eventManager.deleteEvent(event);
        updateEventList();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    private String updateEventList() {
        initList();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public String deleteEvent() {
        eventManager.deleteEvent(currentEvent);
        updateEventList();
        currentEvent = new Event();
        return "events";
    }



}
