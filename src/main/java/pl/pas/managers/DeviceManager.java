package pl.pas.managers;

import pl.pas.model.Event;
import pl.pas.model.resource.Device;
import pl.pas.model.resource.Laptop;
import pl.pas.model.resource.Smartphone;
import pl.pas.model.user.Employee;
import pl.pas.repositories.interfaces.IDeviceRepository;
import pl.pas.repositories.interfaces.IEventRepository;
import pl.pas.repositories.interfaces.IUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
public class DeviceManager implements Serializable {
    @Inject
    private IDeviceRepository deviceRepository;
    @Inject
    private IEventRepository eventRepository;
    @Inject
    private IUserRepository userRepository;

    public DeviceManager(IDeviceRepository deviceRepository, IEventRepository eventRepository, IUserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public DeviceManager() {
    }

    public Device getDevice(UUID uuid) {
        return deviceRepository.getDevice(uuid);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.getAllDevices();
    }

    public void updateDevice(UUID uuid, Device newDevice) {
        deviceRepository.updateDevice(uuid, newDevice);
    }

    public boolean deleteDevice(Device device) {
        return deviceRepository.deleteDevice(device.getId());
    }

    public boolean deleteDevice(UUID uuid) {
        if (deviceRepository.getDevice(uuid) == null) {
            return false;
        }
        if (!deviceRepository.getDevice(uuid).isAvailable()) {
            return false;
        }
        for (Event event : eventRepository.getAllEvents()) {
            if (event.getDevice() == deviceRepository.getDevice(uuid)) {
                event.setDevice(null);
            }
        }
        return deviceRepository.deleteDevice(uuid);
    }

    public List<Laptop> getAllLaptops() {
        return deviceRepository.getAllLaptops();
    }

    public List<Smartphone> getAllSmartphones() {
        return deviceRepository.getAllSmartphones();
    }

    public boolean addLaptop(String brand, String model, int weightInGrams, int memoryInGb) {
        if (brand == null || model == null || weightInGrams <= 0 || memoryInGb <= 0) {
            return false;
        }
        return deviceRepository.addDevice(new Laptop(brand, model, weightInGrams, memoryInGb));
    }

    public boolean addSmartphone(String brand, String model, int weightInGrams, double batteryLifetime) {
        if (brand == null || model == null || weightInGrams <= 0 || batteryLifetime <= 0) {
            return false;
        }
        return deviceRepository.addDevice(new Smartphone(brand, model, weightInGrams, batteryLifetime));
    }

    public boolean updateLaptop(Device old, String brand, String model, int weightInGrams, int memoryInGb) {
        if (old == null || brand == null || model == null || weightInGrams <= 0 || memoryInGb <= 0 ||
                !deviceRepository.getAllDevices().contains(old) ||
                !(old instanceof Laptop) || !old.isAvailable()){
            return false;
        }
        deviceRepository.updateDevice(old.getId(),new Laptop(brand, model, weightInGrams, memoryInGb));
        return true;
    }

    public boolean updateSmartphone(Device old, String brand, String model, int weightInGrams, double batteryLifetime) {
        if (old == null || brand == null || model == null || weightInGrams <= 0 || batteryLifetime <= 0 ||
                !deviceRepository.getAllDevices().contains(old) ||
                !(old instanceof Laptop) || !old.isAvailable()){
            return false;
        }
        deviceRepository.updateDevice(old.getId(),new Smartphone(brand, model, weightInGrams, batteryLifetime));
        return true;
    }
}
