import pl.pas.managers.EventManager;
import pl.pas.model.resource.Device;
import pl.pas.model.resource.Laptop;
import pl.pas.model.resource.Smartphone;
import pl.pas.model.user.Administrator;
import pl.pas.model.user.Client;
import pl.pas.model.user.Employee;
import pl.pas.model.user.User;
import pl.pas.repositories.interfaces.IDeviceRepository;
import pl.pas.repositories.interfaces.IEventRepository;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class InitRepositories implements Serializable {
    @Inject
    private IDeviceRepository deviceRepository;
    @Inject
    private IUserRepository userRepository;
    @Inject
    private IEventRepository eventRepository;
    @Inject
    private EventManager eventManager;

    public InitRepositories() {
    }

    public InitRepositories(IDeviceRepository deviceRepository, IUserRepository userRepository,
                            IEventRepository eventRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.eventManager = new EventManager(this.deviceRepository, this.eventRepository, this.userRepository);
    }

    public void initRepo(@Observes @Initialized(ApplicationScoped.class) Object init) {
        initRepo();
    }

    public void initRepo() {
        Device smartphone1 = new Smartphone("Samsung", "S10", 450, 8.5);
        Device smartphone2 = new Smartphone("Apple", "Iphone X", 411, 9.5);
        Device smartphone3 = new Smartphone("Sony", "Lumia", 250, 5.5);
        Device smartphone4 = new Smartphone("Apple", "Iphone 12", 405, 11.5);
        Device smartphone5 = new Smartphone("Xiaomi", "Mi 10", 470, 14.5);
        Device laptop1 = new Laptop("Lenovo", "Thinkpad X230", 3570, 8);
        Device laptop2 = new Laptop("HP", "Probook 15", 3270, 12);
        Device laptop3 = new Laptop("Apple", "Macbook Pro 15", 2900, 16);
        Device laptop4 = new Laptop("Dell", "Lattitude 13", 2270, 4);

        deviceRepository.addDevice(smartphone1);
        deviceRepository.addDevice(smartphone2);
        deviceRepository.addDevice(smartphone3);
        deviceRepository.addDevice(smartphone4);
        deviceRepository.addDevice(smartphone5);
        deviceRepository.addDevice(laptop1);
        deviceRepository.addDevice(laptop2);
        deviceRepository.addDevice(laptop3);
        deviceRepository.addDevice(laptop4);

        User user1 = new Administrator("admin1", "Adam", "Nowak");
        User user2 = new Employee("aga123", "Agnieszka", "Tylan");
        User user3 = new Client("123456", "Jan", "Kowalski", 44);
        User user4 = new Client("joasia99", "Joanna", "Kulczyk", 21);
        User user5 = new Client("tomtom", "Tomasz", "Tomczyk", 18);
        user5.setActive(false);

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        userRepository.addUser(user4);
        userRepository.addUser(user5);

        eventManager.borrowDevice(smartphone1.getUuid(), user3.getUuid());
        eventManager.borrowDevice(smartphone2.getUuid(), user3.getUuid());
        eventManager.borrowDevice(laptop1.getUuid(), user4.getUuid());
        eventManager.borrowDevice(smartphone3.getUuid(), user3.getUuid());
        eventManager.borrowDevice(smartphone4.getUuid(), user4.getUuid());
        eventManager.borrowDevice(laptop3.getUuid(), user5.getUuid());

    }


    public void noAction() {
    }

}
