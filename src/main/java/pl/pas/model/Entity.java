package pl.pas.model;

import java.util.UUID;

public abstract class Entity {
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Entity() {
        this.uuid = null;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", uuid)
                .toString();
    }
}
