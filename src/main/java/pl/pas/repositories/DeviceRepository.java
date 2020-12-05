package pl.pas.repositories;

import pl.pas.model.resource.Device;
import pl.pas.model.resource.Laptop;
import pl.pas.model.resource.Smartphone;
import pl.pas.repositories.interfaces.IDeviceRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeviceRepository implements IDeviceRepository, Serializable {

    private final List<Device> devices;

    public DeviceRepository() {
        this.devices = new ArrayList<>();
    }

    @Override
    public boolean addDevice(Device device) {
        synchronized (devices) {
            device.setId(UUID.randomUUID());
            return devices.add(device);
        }
    }

    @Override
    public Device getDevice(UUID uuid) {
        synchronized (devices) {
            return devices.stream().filter(d -> d.getId() == uuid).findFirst().orElse(null);
        }
    }


    @Override
    public List<Device> getAllDevices() {
        synchronized (devices) {
            return new ArrayList<>(devices);
        }

    }

    @Override
    public void updateDevice(UUID uuid, Device newDevice) {
        synchronized (devices) {
            for (Device r : devices) {
                if (r.getId() == uuid) {
                    newDevice.setId(uuid);
                    devices.set(devices.indexOf(r), newDevice);
                }
            }
        }
    }

    @Override
    public boolean deleteDevice(UUID uuid) {
        synchronized (devices) {
            return devices.remove(getDevice(uuid));
        }
    }

    @Override
    public List<Laptop> getAllLaptops() {
        synchronized (devices) {
            List<Laptop> laptops = new ArrayList<>();
            for (Device d : devices) {
                if (d instanceof Laptop) {
                    laptops.add((Laptop) d);
                }
            }
            return laptops;
        }
    }

    @Override
    public List<Smartphone> getAllSmartphones() {
        synchronized (devices) {
            List<Smartphone> smartphones = new ArrayList<>();
            for (Device d : devices) {
                if (d instanceof Smartphone) {
                    smartphones.add((Smartphone) d);
                }
            }
            return smartphones;
        }
    }
}
