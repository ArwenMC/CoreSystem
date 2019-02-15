package com.arwenmc.api.ItemBuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

/*
 Example For Book Builder

  ItemStack itemstack = new BookBuilder(1)
            .setAuthor("yourName")
            .addPage("pageContent");
            .build();
 */

public class BookBuilder extends ItemBuilder {
    private BookMeta bookMeta;
    private ItemStack itemStack;

    public BookBuilder(Material material, int amount) {
        super(Material.BOOK, amount);
        this.itemStack = this.getItemStack();
    }

    public BookBuilder setAuthor(String name) {
        this.bookMeta = ((BookMeta) this.itemStack.getItemMeta());
        this.bookMeta.setAuthor(name);
        this.itemStack.setItemMeta(this.bookMeta);
        return this;
    }

    public BookBuilder addPage(String content) {
        this.bookMeta = ((BookMeta) this.itemStack.getItemMeta());
        this.bookMeta.addPage(content);
        this.itemStack.setItemMeta(this.bookMeta);
        return this;
    }

    public BookBuilder addPages(List<String> contents) {
        this.bookMeta = ((BookMeta) this.itemStack.getItemMeta());
        for (String content : contents) {
            this.bookMeta.addPage(content);
        }
        this.itemStack.setItemMeta(this.bookMeta);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.bookMeta);
        return this.itemStack;
    }

    public int getPageCount() {
        return this.bookMeta.getPageCount();
    }
}
