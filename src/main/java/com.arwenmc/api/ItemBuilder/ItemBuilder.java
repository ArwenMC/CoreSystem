package com.arwenmc.api.ItemBuilder;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private Material material;
    private int amount;
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    /**
     * Intial constructor to build an item.
     *
     * @param material The material that you like the amount to help.
     * @param amount   The amount for that item.
     */
    public ItemBuilder(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        this.itemStack = new ItemStack(this.material);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    /**
     * Set the durability for the ItemStack
     *
     * @param durability The int durability, it is casted to a short.
     */
    public ItemBuilder setDurability(int durability) {
        this.itemStack.setDurability((short) durability);
        return this;
    }

    /**
     * Set's the display name, you can pass this with ChatColor or & values
     *
     * @param name The name of the item you would like to use.
     * @return
     */
    public ItemBuilder setDisplayName(String name) {
        this.itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder addEnchants(Map<Enchantment, Integer> enchantments) {
        if (!enchantments.isEmpty()) {
            for (Enchantment enchantment : enchantments.keySet()) {
                this.itemMeta.addEnchant(enchantment, enchantments.get(enchantment).intValue(), true);
            }
        }
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
        return this;
    }

    public ItemBuilder setLore(List<String> lores) {
        ArrayList<String> colouredLores = new ArrayList<>();
        for (String lore : lores) {
            colouredLores.add(ChatColor.translateAlternateColorCodes('&', lore));
        }
        this.itemMeta.setLore(colouredLores);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public String toBase64() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(this.itemStack);
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save ItemStack", e);
        }
    }

    public ItemBuilder fromBase64(String from) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(from));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            this.itemStack = ((ItemStack) dataInput.readObject());
            dataInput.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Builds the Item with your custom options.
     *
     * @return The ItemStack with the custom options.
     */
    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

    /**
     * Add your item to a player's inventory.
     *
     * @param player The player you wan to add to the inventory
     */
    public void buildPlayer(Player player) {
        player.getInventory().addItem(this.build());
    }


    public Material getMaterial() {
        return this.material;
    }

    public int getAmount() {
        return this.amount;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }
}
