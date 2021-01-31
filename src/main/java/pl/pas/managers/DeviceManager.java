package pl.pas.managers;

import pl.pas.model.Event;
import pl.pas.model.resource.Device;
import pl.pas.model.resource.Laptop;
import pl.pas.model.resource.Smartphone;
import pl.pas.repositories.interfaces.IDeviceRepository;
import pl.pas.repositories.interfaces.IEventRepository;

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

    public DeviceManager(IDeviceRepository deviceRepository, IEventRepository eventRepository) {
        this.deviceRepository = deviceRepository;
        this.eventRepository = eventRepository;
    }

    public DeviceManager() {
    }

    public Device getDevice(UUID uuid) {
        return deviceRepository.getDevice(uuid);
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

    public boolean addLaptop(Laptop laptop) {
        return addLaptop(laptop.getBrand(), laptop.getModel(), laptop.getWeightInGrams(), laptop.getMemoryInGb());
    }

    public boolean addLaptop(String brand, String model, int weightInGrams, int memoryInGb) {
        if (brand == null || model == null || weightInGrams <= 0 || memoryInGb <= 0) {
            return false;
        }
        return deviceRepository.addDevice(new Laptop(brand, model, weightInGrams, memoryInGb));
    }

    public boolean addSmartphone(Smartphone smartphone) {
        return addSmartphone(smartphone.getBrand(), smartphone.getModel(), smartphone.getWeightInGrams(), smartphone.getBatteryLifetime());
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
                !(old instanceof Laptop) || !old.isAvailable()) {
            return false;
        }
        deviceRepository.updateDevice(old.getUuid(), new Laptop(brand, model, weightInGrams, memoryInGb));
        return true;
    }

    public boolean updateSmartphone(Device old, String brand, String model, int weightInGrams, double batteryLifetime) {
        if (old == null || brand == null || model == null || weightInGrams <= 0 || batteryLifetime <= 0 ||
                !deviceRepository.getAllDevices().contains(old) ||
                !(old instanceof Laptop) || !old.isAvailable()) {
            return false;
        }
        deviceRepository.updateDevice(old.getUuid(), new Smartphone(brand, model, weightInGrams, batteryLifetime));
        return true;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.getAllDevices();
    }


}
