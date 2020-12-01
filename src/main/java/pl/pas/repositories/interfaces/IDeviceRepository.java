package pl.pas.repositories.interfaces;

import pl.pas.model.resource.Device;
import pl.pas.model.resource.Laptop;
import pl.pas.model.resource.Smartphone;

import java.util.List;
import java.util.UUID;

public interface IDeviceRepository {
    boolean addDevice(Device resource);
    Device getDevice(UUID uuid);
    List<Device> getAllDevices();
    void updateDevice(UUID uuid, Device newDevice);
    boolean deleteDevice(long uuid);
    List<Laptop> getAllLaptops();
    List<Smartphone> getAllSmartphones();
}
