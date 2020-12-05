package pl.pas.repositories;

import pl.pas.model.Event;
import pl.pas.repositories.interfaces.IEventRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EventRepository implements IEventRepository, Serializable {
    private final List<Event> events;

    public EventRepository() {
        this.events = new ArrayList<>();
    }

    @Override
    public boolean addEvent(Event event) {
        synchronized (events) {
            event.setId(UUID.randomUUID());
            return events.add(event);
        }
    }

    @Override
    public Event getEvent(UUID uuid) {
        synchronized (events) {
            return events.stream().filter(e -> e.getId() == uuid).findFirst().orElse(null);
        }
    }

    @Override
    public List<Event> getEventsByUser(UUID uuid) {
        List<Event> userEvents = new ArrayList<>();
        synchronized (events) {
            for (Event e : events) {
                if (e.getClient().getId() == uuid) {
                    userEvents.add(e);
                }
            }
            return userEvents;
        }

    }

    @Override
    public List<Event> getEventsByDevice(UUID uuid) {
        List<Event> deviceEvents = new ArrayList<>();
        synchronized (events) {
            for (Event e : events) {
                if (e.getDevice().getId() == uuid) {
                    deviceEvents.add(e);
                }
            }
            return deviceEvents;
        }
    }

    @Override
    public List<Event> getAllEvents() {
        synchronized (events) {
            return new ArrayList<>(events);
        }
    }

    @Override
    public void updateEvent(UUID uuid, Event newEvent) {
        synchronized (events) {
            for (Event e : events) {
                if (e.getId() == uuid) {
                    newEvent.setId(uuid);
                    events.set(events.indexOf(e), newEvent);
                }
            }
        }
    }

    @Override
    public boolean deleteEvent(UUID uuid) {
        synchronized (events){
            return events.remove(getEvent(uuid));
        }
    }

    @Override
    public void endEvent(UUID uuid) {
        synchronized (events){
            getEvent(uuid).setReturnDate(new Date());
        }
    }
}
