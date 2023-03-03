package com.keirenw.fundamentals.fundamentals.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import java.util.logging.Logger;

public class RecipeLeather extends BaseRecipe {
    private final Logger _logger;


    public RecipeLeather(Logger logger, NamespacedKey key) {
        super(key, Material.LEATHER);
        this._logger = logger;
    }

    public ShapedRecipe Initialise() {
        shapedRecipe.shape("RRR","RRR","RRR");
        shapedRecipe.setIngredient('R', Material.ROTTEN_FLESH);

        _logger.info("Recipe loaded :: Leather");
        return shapedRecipe;
    }
}
