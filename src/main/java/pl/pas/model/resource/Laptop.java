package pl.pas.model.resource;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Laptop extends Device {
    private int memoryInGb;

    public Laptop(String brand, String model, int weightInGrams, int memoryInGb) {
        super(brand, model, weightInGrams);
        this.memoryInGb = memoryInGb;
    }

    public Laptop() {
        super();
        this.memoryInGb = 0;
    }

    public int getMemoryInGb() {
        return memoryInGb;
    }

    public void setMemoryInGb(int memoryInGb) {
        this.memoryInGb = memoryInGb;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("memoryInMb", memoryInGb)
                .toString();
    }
}
