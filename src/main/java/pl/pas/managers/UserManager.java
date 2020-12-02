package pl.pas.managers;

import pl.pas.repositories.interfaces.IDeviceRepository;
import pl.pas.repositories.interfaces.IEventRepository;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserManager {
    @Inject
    private IDeviceRepository deviceRepository;
    @Inject
    private IEventRepository eventRepository;
    @Inject
    private IUserRepository userRepository;

    public UserManager() {
    }

    public UserManager(IDeviceRepository deviceRepository, IEventRepository eventRepository, IUserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

}
