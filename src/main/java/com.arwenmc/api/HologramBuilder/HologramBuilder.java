package com.arwenmc.api.HologramBuilder;

import com.arwenmc.api.Entities.EntityBuilder;
import com.arwenmc.api.FileBuilder.FileBuilder;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/*
 HologramBuilder Example

 HologramBuilder hb = new HologramBuilder("yourName", player.getLocation().add(0.0, -2.0, 0.0))
            .setLine(0, "yourText")
            .addLine("yourText")
            .addLines(Arrays.asList("yourLine", "yourNextLine"))
            .spawn();

 hb.saveInFile("plugins/yourFolder", "yourFile.yml");


 HologramManager hm = new HologramManager();
 hm.loadHologramsFromFile("plugins/yourFolder", "yourFile.yml");
 */

public class HologramBuilder {
    private String name;
    private Location loc;
    private ArrayList<String> lines = new ArrayList();
    private HashMap<Location, Entity> entities = new HashMap();

    public HologramBuilder(String name, Location loc) {
        this.name = name;
        this.loc = loc;
    }

    public HologramBuilder(String name, World world, double x, double y, double z) {
        Location loc = new Location(world, x, y, z);
        this.name = name;
        this.loc = loc;
    }

    public HologramBuilder(String name, String world, double x, double y, double z) {
        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
        this.name = name;
        this.loc = loc;
    }

    public HologramBuilder setLine(int line, String text) {
        this.lines.set(line, text);
        return this;
    }

    public HologramBuilder addLines(List<String> lines) {
        for (String line : lines) {
            this.lines.add(line);
        }
        return this;
    }

    public HologramBuilder addLine(String text) {
        this.lines.add(text);
        return this;
    }

    public HologramBuilder removeLine(String line) {
        this.lines.remove(line);
        return this;
    }

    public HologramBuilder spawn() {
        Location loc = this.loc;
        for (String line : Lists.reverse(this.lines)) {
            ArmorStand entity = (ArmorStand) new EntityBuilder(EntityType.ARMOR_STAND, this.loc).setCustomName(line).setCustomNameVisible(true).spawn();
            entity.setVisible(false);
            entity.setGravity(false);
            entity.setBasePlate(false);
            this.entities.put(loc, entity);
            loc = loc.add(0.0D, 0.25D, 0.0D);
        }
        return this;
    }

    public void remove(Location loc) {
        if (this.entities.containsKey(loc)) {
            this.entities.get(loc).remove();
            this.entities.remove(loc);
        }
    }

    public void saveInFile(String path, String file) {
        FileBuilder fb = new FileBuilder(path, file);
        fb.set(this.name + ".World", this.loc.getWorld().getName());
        fb.set(this.name + ".X", Double.valueOf(this.loc.getX()));
        fb.set(this.name + ".Y", Double.valueOf(this.loc.getY()));
        fb.set(this.name + ".Z", Double.valueOf(this.loc.getZ()));
        fb.set(this.name + ".Lines", this.lines);
        fb.save();
    }

    public void removeFromFile(String path, String file) {
        FileBuilder fb = new FileBuilder(path, file);
        fb.set(this.name + ".World", null);
        fb.set(this.name + ".X", null);
        fb.set(this.name + ".Y", null);
        fb.set(this.name + ".Z", null);
        fb.set(this.name + ".Lines", null);
        fb.save();
    }
}
