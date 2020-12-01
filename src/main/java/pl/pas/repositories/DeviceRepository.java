package pl.pas.repositories;

import pl.pas.model.resource.Device;
import pl.pas.model.resource.Laptop;
import pl.pas.model.resource.Smartphone;
import pl.pas.repositories.interfaces.IDeviceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeviceRepository implements IDeviceRepository {

    private final List<Device> devices;

    public DeviceRepository() {
        this.devices = new ArrayList<>();
    }

    @Override
    public boolean addDevice(Device resource) {
        return false;
    }

    @Override
    public Device getDevice(UUID uuid) {
        return null;
    }

    @Override
    public List<Device> getAllDevices() {
        return null;
    }

    @Override
    public void updateDevice(UUID uuid, Device newDevice) {

    }

    @Override
    public boolean deleteDevice(long uuid) {
        return false;
    }

    @Override
    public List<Laptop> getAllLaptops() {
        return null;
    }

    @Override
    public List<Smartphone> getAllSmartphones() {
        return null;
    }
}
