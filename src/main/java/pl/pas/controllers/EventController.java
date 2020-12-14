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

    private UUID clientUuid;
    private UUID deviceUuid;
    private UUID newEventClientUuid;
    private UUID newEventDeviceUuid;
    private Date borrowDate;
    private UUID eventUuid;
    private Event currentEvent;
    private List<Event> currentEvents;

    public EventController() {
        this.borrowDate = new Date();
        this.clientUuid = null;
        this.deviceUuid = null;
        this.eventUuid = null;
        this.newEventDeviceUuid = null;
        this.newEventClientUuid = null;
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

    public UUID getClientUuid() {
        return clientUuid;
    }

    public void setClientUuid(UUID clientUuid) {
        this.clientUuid = clientUuid;
    }

    public UUID getDeviceUuid() {
        return deviceUuid;
    }

    public UUID getNewEventClientUuid() {
        return newEventClientUuid;
    }

    public void setNewEventClientUuid(UUID newEventClientUuid) {
        this.newEventClientUuid = newEventClientUuid;
    }

    public UUID getNewEventDeviceUuid() {
        return newEventDeviceUuid;
    }

    public void setNewEventDeviceUuid(UUID newEventDeviceUuid) {
        this.newEventDeviceUuid = newEventDeviceUuid;
    }

    public void setDeviceUuid(UUID deviceUuid) {
        this.deviceUuid = deviceUuid;
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
        this.eventManager.borrowDevice(newEventDeviceUuid, newEventClientUuid, borrowDate);
        this.newEventClientUuid = null;
        this.newEventDeviceUuid = null;
        this.borrowDate = new Date();
        updateEventList();
        return "menu";
    }

    public String search(UUID eventId) {
        currentEvent = eventManager.getEvent(eventId);
        return "event";
    }

    public void searchByClient(UUID clientId) {
        currentEvents = eventManager.getEventsForClient(clientId);
    }

    public void searchByDevice(UUID deviceId) {
        currentEvents = eventManager.getEventsForDevice(deviceId);
    }

    public String returnDevice(Event event) {
        eventManager.returnDevice(event);
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";

    }

    public String deleteEvent(Event event) {
        eventManager.deleteEvent(event);
        updateEventList();
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";

    }

    public void updateEventList() {
        initList();
    }

    public String deleteEvent() {
        eventManager.deleteEvent(currentEvent);
        updateEventList();
        currentEvent = new Event();
        return "events";
    }
}
