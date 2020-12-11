package pl.pas.model.resource;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Smartphone extends Device {
    private double batteryLifetime;

    public Smartphone(String brand, String model, int weightInGrams, double batteryLifetime) {
        super(brand, model, weightInGrams);
        this.batteryLifetime = batteryLifetime;
    }

    public Smartphone() {
        super();
        this.batteryLifetime = 0;
    }

    public double getBatteryLifetime() {
        return batteryLifetime;
    }

    public void setBatteryLifetime(double batteryLifetime) {
        this.batteryLifetime = batteryLifetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("batteryLifetime", batteryLifetime)
                .toString();
    }
}
