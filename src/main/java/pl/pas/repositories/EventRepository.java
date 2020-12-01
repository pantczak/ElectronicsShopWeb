package pl.pas.repositories;

import pl.pas.model.Event;
import pl.pas.repositories.interfaces.IEventRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventRepository implements IEventRepository {
    private List<Event> events;

    public EventRepository() {
        this.events = new ArrayList<>();
    }

    @Override
    public boolean addEvent(Event event) {
        return false;
    }

    @Override
    public Event getEvent(UUID uuid) {
        return null;
    }

    @Override
    public List<Event> getEventsByUser(UUID uuid) {
        return null;
    }

    @Override
    public List<Event> getEventsByDevice(UUID uuid) {
        return null;
    }

    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public void updateEvent(UUID uuid, Event newEvent) {

    }

    @Override
    public boolean deleteEvent(UUID uuid) {
        return false;
    }

    @Override
    public void endEvent(UUID uuid) {

    }
}
