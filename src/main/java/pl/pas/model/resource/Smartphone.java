package pl.pas.model.resource;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Smartphone extends Device {
    private int batteryInMah;

    public Smartphone(String brand, String model, int weightInGrams, int batteryInMah) {
        super(brand, model, weightInGrams);
        this.batteryInMah = batteryInMah;
    }

    public int getBatteryInMah() {
        return batteryInMah;
    }

    public void setBatteryInMah(int batteryInMah) {
        this.batteryInMah = batteryInMah;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("batteryInMah", batteryInMah)
                .toString();
    }
}
