package pl.pas.controllers;

import pl.pas.managers.DeviceManager;
import pl.pas.managers.EventManager;
import pl.pas.model.resource.Device;
import pl.pas.repositories.EventRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@SessionScoped
@Named
public class UserRentController implements Serializable {
    private List<Device> availableDevices;

    @Inject
    private EventManager eventManager;

    @Inject
    private DeviceManager deviceManager;

    private UUID deviceUUID;

    public UUID getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(UUID deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    @PostConstruct
    public void init(){availableDevices = deviceManager.getAvailableDevices();}

    //TODO RENT , SHOW  AVAILABLE DEVICES

}
