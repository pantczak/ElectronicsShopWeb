package pl.pas.controllers;

import pl.pas.managers.DeviceManager;
import pl.pas.model.resource.Device;
import pl.pas.model.resource.Laptop;
import pl.pas.model.resource.Smartphone;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@SessionScoped
public class DeviceController implements Serializable {
    @Inject
    private DeviceManager deviceManager;

    private Laptop newLaptop;
    private Smartphone newSmartphone;
    private UUID deviceUuid;
    private Laptop currentLaptop;
    private Smartphone currentSmartphone;
    private List<Laptop> currentLaptops;
    private List<Smartphone> currentSmartphones;

    public Laptop getNewLaptop() {
        return newLaptop;
    }

    public void setNewLaptop(Laptop newLaptop) {
        this.newLaptop = newLaptop;
    }

    public Smartphone getNewSmartphone() {
        return newSmartphone;
    }

    public void setNewSmartphone(Smartphone newSmartphone) {
        this.newSmartphone = newSmartphone;
    }

    public UUID getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(UUID deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public Laptop getCurrentLaptop() {
        return currentLaptop;
    }

    public void setCurrentLaptop(Laptop currentLaptop) {
        this.currentLaptop = currentLaptop;
    }

    public Smartphone getCurrentSmartphone() {
        return currentSmartphone;
    }

    public void setCurrentSmartphone(Smartphone currentSmartphone) {
        this.currentSmartphone = currentSmartphone;
    }

    public List<Smartphone> getCurrentSmartphones() {
        return currentSmartphones;
    }

    public void setCurrentSmartphones(List<Smartphone> currentSmartphones) {
        this.currentSmartphones = currentSmartphones;
    }

    public List<Laptop> getCurrentLaptops() {
        return currentLaptops;
    }

    public void setCurrentLaptops(List<Laptop> currentLaptops) {
        this.currentLaptops = currentLaptops;
    }

    public DeviceController() {
        newLaptop = new Laptop();
        newSmartphone = new Smartphone();
        this.deviceUuid = null;
    }

    @PostConstruct
    public void initList() {
        currentLaptops = deviceManager.getAllLaptops();
        currentSmartphones = deviceManager.getAllSmartphones();

    }

    public String processNewLaptop() {
        this.deviceManager.addLaptop(newLaptop.getBrand(), newLaptop.getModel(), newLaptop.getWeightInGrams(), newLaptop.getMemoryInGb());
        this.newLaptop = new Laptop();
        updateDeviceList();
        return "menu";
    }

    public String processNewSmartphone() {
        this.deviceManager.addSmartphone(newSmartphone.getBrand(), newSmartphone.getModel(), newSmartphone.getWeightInGrams(), newSmartphone.getBatteryLifetime());
        this.newSmartphone = new Smartphone();
        updateDeviceList();
        return "menu";
    }

    public String cancelNewLaptop() {
        this.newLaptop = new Laptop();
        return "menu";
    }

    public String cancelNewSmartphone() {
        this.newSmartphone = new Smartphone();
        return "menu";
    }

    public String findDevice(UUID uuid) {
        Device device = deviceManager.getDevice(uuid);
        if (device instanceof Laptop) {
            currentLaptop = (Laptop) device;
            return "laptop";
        } else {
            currentSmartphone = (Smartphone) device;
            return "smartphone";
        }
    }

    public String removeDevice(Device device) {
        deviceManager.deleteDevice(device.getUuid());
        updateDeviceList();
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public String removeLaptop() {
        removeDevice(currentLaptop);
        currentLaptop = new Laptop();
        return "devices";
    }

    public String removeSmartphone() {
        removeDevice(currentSmartphone);
        currentSmartphone = new Smartphone();
        return "devices";
    }

    public String updateLaptop() {
        deviceManager.updateLaptop(currentLaptop, currentLaptop.getBrand(), currentLaptop.getModel(), currentLaptop.getWeightInGrams(), currentLaptop.getMemoryInGb());
        updateDeviceList();
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public String updateSmartphone() {
        deviceManager.updateSmartphone(currentSmartphone, currentSmartphone.getBrand(), currentSmartphone.getModel(), currentSmartphone.getWeightInGrams(), currentSmartphone.getBatteryLifetime());
        updateDeviceList();
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public void updateDeviceList() {
        initList();
    }
}
