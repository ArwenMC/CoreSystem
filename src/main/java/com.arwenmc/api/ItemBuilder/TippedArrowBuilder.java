package com.arwenmc.api.ItemBuilder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*
 Example For TippedArrowBuilder

 ItemStack itemstack = new TippedArrowBuilder(1)
            .addCustomEffect(PotionEffectType.REGENERATION, durationInTicks, 0)
            .setColor(Color.RED) // Only available in 1.11 - 1.12.2
            .build();
 */


public class TippedArrowBuilder extends ItemBuilder {
    private ItemStack itemStack;
    private PotionMeta potionMeta;

    public TippedArrowBuilder(int amount) {
        super(Material.TIPPED_ARROW, amount);
        this.potionMeta = ((PotionMeta) this.itemStack.getItemMeta());
        this.itemStack.setItemMeta(this.potionMeta);
    }

    @Deprecated
    public TippedArrowBuilder setColor(Color color) {
        this.potionMeta.setColor(color);
        return this;
    }

    public TippedArrowBuilder addCustomEffect(PotionEffectType type, int duration, int amplifier) {
        PotionEffect effect = new PotionEffect(type, duration, amplifier, true);
        this.potionMeta.addCustomEffect(effect, true);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.potionMeta);
        return this.itemStack;
    }
}
