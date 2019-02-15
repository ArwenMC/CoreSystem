package com.arwenmc.api.Entities;

import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;


/*
Example Of VillageTrader

 VillagerTradeBuilder vtb = new VillagerTradeBuilder()
            .setExperienceReward(false)
            .setMaxUses(999)
            .setUses(0);
 vtb.clearTrades(villager);
 vtb.addTrade(yourIngredient, yourResult);
 vtb.addTrade(yourIngredient1, yourIngredient2, yourResult);
 vtb.setTrades(villager);
 player.openMerchant(villager, true);
 */

public class VillagerTradeBuilder {
    private ArrayList<MerchantRecipe> mrs = new ArrayList();
    private boolean expreward = true;
    private int maxUses;
    private int uses;

    public VillagerTradeBuilder setExperienceReward(boolean reward) {
        this.expreward = reward;
        return this;
    }

    public VillagerTradeBuilder setMaxUses(int maxUses) {
        this.maxUses = maxUses;
        return this;
    }

    public VillagerTradeBuilder setUses(int uses) {
        this.uses = uses;
        return this;
    }

    public void clearTrades(Villager v) {
        this.mrs.clear();
        v.setRecipes(this.mrs);
    }

    public void clearTrades(Merchant m) {
        this.mrs.clear();
        m.setRecipes(this.mrs);
    }

    public void addTrade(ItemStack ingredient, ItemStack result) {
        MerchantRecipe mr = new MerchantRecipe(result, this.maxUses);
        mr.addIngredient(ingredient);
        mr.setUses(this.uses);
        mr.setExperienceReward(this.expreward);
        this.mrs.add(mr);
    }

    public void addTrade(ItemStack ingredient1, ItemStack ingredient2, ItemStack result) {
        MerchantRecipe mr = new MerchantRecipe(result, this.maxUses);
        mr.addIngredient(ingredient1);
        mr.addIngredient(ingredient2);
        mr.setUses(this.uses);
        mr.setExperienceReward(this.expreward);
        this.mrs.add(mr);
    }

    public void setTrades(Villager v) {
        v.setRecipes(this.mrs);
    }

    public void setTrades(Merchant m) {
        m.setRecipes(this.mrs);
    }
}