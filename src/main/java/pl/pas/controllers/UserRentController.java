package pl.pas.controllers;

import pl.pas.managers.DeviceManager;
import pl.pas.managers.EventManager;
import pl.pas.model.resource.Device;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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

    private UUID searchDeviceUUID;

    public UUID getSearchDeviceUUID() {
        return searchDeviceUUID;
    }

    public void setSearchDeviceUUID(UUID searchDeviceUUID) {
        this.searchDeviceUUID = searchDeviceUUID;
    }

    public List<Device> getAvailableDevices() {
        return availableDevices;
    }

    @PostConstruct
    public void init() {
        availableDevices = deviceManager.getAvailableDevices();
    }

    public void findDevice() {
        if (searchDeviceUUID != null) {
            Device device = availableDevices.stream().filter(x -> x.getUuid().equals(searchDeviceUUID)).findFirst().orElse(null);
            if (device == null) {
                availableDevices = new ArrayList<>();
            } else
                availableDevices = Collections.singletonList(device);
        }
    }

    public void borrowDevice(Device device) {
        String userLogin = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        eventManager.borrowDeviceByLogin(device.getUuid(), userLogin);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            init();
        }
    }

    public void updateDeviceList() {
        init();
    }


    //TODO RENT , SHOW  AVAILABLE DEVICES

}
