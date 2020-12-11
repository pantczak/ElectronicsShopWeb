package pl.pas.model.resource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.pas.model.Entity;

public abstract class Device extends Entity {
    private String brand;
    private String model;
    private boolean isAvailable;
    private int weightInGrams;

    public Device(String brand, String model, int weightInGrams) {
        super();
        this.brand = brand;
        this.model = model;
        this.isAvailable = true;
        this.weightInGrams = weightInGrams;
    }

    public Device() {
        super();
        this.brand = "";
        this.model = "";
        this.isAvailable = true;
        this.weightInGrams = 0;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(int weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("brand", brand)
                .append("model", model)
                .append("isAvailable", isAvailable)
                .append("weightInGrams", weightInGrams)
                .toString();
    }
}
