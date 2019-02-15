package com.arwenmc.api.ItemBuilder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*
 Example For PotionBuilder

 ItemStack itemstack = new PotionBuilder(Material.POTION, 1)
            .addCustomEffect(PotionEffectType.REGENERATION, durationInTicks, 0)
            .setColor(Color.RED)
            .build();
 */

public class PotionBuilder extends ItemBuilder {
    private PotionMeta potionMeta;
    private ItemStack itemStack;

    public PotionBuilder(Material material, int amount) {
        super(Material.POTION, amount);
        this.itemStack = this.getItemStack();
        this.potionMeta = (PotionMeta) this.getItemMeta();
    }

    public PotionBuilder setColor(Color color) {
        this.potionMeta.setColor(color);
        this.itemStack.setItemMeta(this.potionMeta);
        return this;
    }

    public PotionBuilder addCustomEffect(PotionEffectType type, int duration, int amplifier) {
        PotionEffect effect = new PotionEffect(type, duration, amplifier, true);
        this.potionMeta.addCustomEffect(effect, true);
        this.itemStack.setItemMeta(this.potionMeta);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.potionMeta);
        return this.itemStack;
    }
}