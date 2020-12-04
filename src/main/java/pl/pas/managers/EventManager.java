package pl.pas.managers;

import pl.pas.model.Event;
import pl.pas.model.resource.Device;
import pl.pas.model.user.Client;
import pl.pas.model.user.User;
import pl.pas.repositories.interfaces.IDeviceRepository;
import pl.pas.repositories.interfaces.IEventRepository;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class EventManager {
    @Inject
    private IDeviceRepository deviceRepository;
    @Inject
    private IEventRepository eventRepository;
    @Inject
    private IUserRepository userRepository;

    public EventManager() {
    }

    public EventManager(IDeviceRepository deviceRepository, IEventRepository eventRepository, IUserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public boolean borrowDevice(UUID deviceID, UUID userID) {
        Device device = deviceRepository.getDevice(deviceID);
        User user = userRepository.getUser(userID);
        if (device == null || user == null) {
            return false;
        }
        if (device.isAvailable() && user instanceof Client && user.isActive()) {
            eventRepository.addEvent(new Event(device, (Client) user, new Date()));
            device.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnDevice(Event event) {
        if (event == null || event.getReturnDate() != null || eventRepository.getEvent(event.getId()) == null) {
            return false;
        }
        eventRepository.endEvent(event.getId());
        event.getDevice().setAvailable(true);
        return true;
    }

    public boolean cancelEvent(Event event) {
        if (event == null || event.getReturnDate() != null || eventRepository.getEvent(event.getId()) == null) {
            return false;
        }
        return eventRepository.deleteEvent(event.getId());
    }

    public Event getEvent(UUID uuid) {
        return eventRepository.getEvent(uuid);
    }

    public List<Event> getEventsForClient(UUID uuid) {
        return eventRepository.getEventsByUser(uuid);
    }

    public List<Event> getEventsForClient(User user) {
        return eventRepository.getEventsByUser(user.getId());
    }

    public List<Event> getEventsForDevice(UUID uuid) {
        return eventRepository.getEventsByDevice(uuid);
    }

    public List<Event> getEventsForDevice(Device device) {
        return eventRepository.getEventsByDevice(device.getId());
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }
}
