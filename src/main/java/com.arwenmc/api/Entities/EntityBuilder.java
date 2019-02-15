package com.arwenmc.api.Entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;


/*
      Example For Spawning With the builder

      Entity entity = new EntityBuilder(EntityType.VILLAGER, player.getLocation())
            .setAge(EntityAge.ADULT)
            .setCustomName("Villager")
            .setCustomNameVisible(true)
            .setPassenger(otherEntity)
            .spawn();

 */

public class EntityBuilder {
    private Entity en;

    public EntityBuilder(EntityType entity, Location loc) {
        this.en = loc.getWorld().spawnEntity(loc, entity);
    }

    public EntityBuilder(EntityType entity, World world, double x, double y, double z) {
        Location loc = new Location(world, x, y, z);
        this.en = loc.getWorld().spawnEntity(loc, entity);
    }

    public EntityBuilder(EntityType entity, String world, double x, double y, double z) {
        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
        this.en = loc.getWorld().spawnEntity(loc, entity);
    }

    public EntityBuilder setCustomName(String name) {
        this.en.setCustomName(name);
        return this;
    }

    public EntityBuilder setCustomNameVisible(boolean visible) {
        this.en.setCustomNameVisible(visible);
        return this;
    }

    @Deprecated
    public EntityBuilder setPassenger(Entity entity) {
        this.en.setPassenger(entity);
        return this;
    }

    public EntityBuilder setAge(EntityAge age) {
        if ((this.en instanceof Ageable)) {
            switch (age) {
                case ADULT:
                    ((Ageable) this.en).setBaby();
                    break;
                case BABY:
                    ((Ageable) this.en).setAdult();
            }
        } else {
            throw new IllegalArgumentException("Entity's age cannot be modified!");
        }
        return this;
    }

    public Entity spawn() {
        return this.en;
    }

    public enum EntityAge {
        BABY, ADULT
    }
}
