package com.keirenw.fundamentals.fundamentals.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class BaseRecipe {
    ItemStack item;
    ShapedRecipe shapedRecipe;
    public NamespacedKey key;

    BaseRecipe(NamespacedKey key, Material material ) {
        item = new ItemStack(material);
        shapedRecipe = new ShapedRecipe(key, item);
    }

}
