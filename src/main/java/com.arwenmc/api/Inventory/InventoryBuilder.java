package com.arwenmc.api.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/*
 Example Of Inventory Builder

 Inventory inventory = new InventoryBuilder(player, yourSize, "yourTitle")
            .setItem(0, yourItemStack)
            .addItem(yourItemStack)
            .create();
 player.openInventory(inventory);


 To save an Inventory in a String

 String data = new InventoryBuilder(anInventory)
            .toBase64();

 To load an Inventory form a String

 InventoryBuilder ib = new InventoryBuilder(player, yourSize, "yourTitle");
 ib.setItems(ib.fromBase64(from);
 player.openInventory(ib.create());
 */

public class InventoryBuilder {
    private Inventory inv;

    public InventoryBuilder(InventoryHolder owner, int size) {
        this.inv = Bukkit.createInventory(owner, size);
    }

    public InventoryBuilder(InventoryHolder owner, InventoryType type) {
        this.inv = Bukkit.createInventory(owner, type);
    }

    public InventoryBuilder(InventoryHolder owner, int size, String title) {
        this.inv = Bukkit.createInventory(owner, size, title);
    }

    public InventoryBuilder(InventoryHolder owner, InventoryType type, String title) {
        this.inv = Bukkit.createInventory(owner, type, title);
    }

    public InventoryBuilder(Inventory inv) {
        this.inv = inv;
    }

    public InventoryBuilder setItem(int slot, ItemStack itemstack) {
        this.inv.setItem(slot, itemstack);
        return this;
    }

    public InventoryBuilder setItems(Map<Integer, ItemStack> items) {
        int i = 0;
        for (ItemStack is : items.values()) {
            if (is != null) {
                this.inv.setItem(i, is);
            }
            i++;
        }
        return this;
    }

    public InventoryBuilder addItem(ItemStack itemstack) {
        this.inv.addItem(itemstack);
        return this;
    }

    public String toBase64() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(this.inv.getSize());
            for (int i = 0; i < this.inv.getSize(); i++) {
                dataOutput.writeObject(this.inv.getItem(i));
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks", e);
        }
    }

    public Map<Integer, ItemStack> fromBase64(String from) {
        Map<Integer, ItemStack> items = new HashMap();
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(from));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            int size = dataInput.readInt();
            for (int i = 0; i < size; i++) {
                items.put(Integer.valueOf(i), (ItemStack) dataInput.readObject());
            }
            dataInput.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Inventory create() {
        return this.inv;
    }
}