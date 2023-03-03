package com.keirenw.fundamentals.fundamentals;

import com.keirenw.fundamentals.fundamentals.commands.admin.*;
import com.keirenw.fundamentals.fundamentals.commands.homes.*;
import com.keirenw.fundamentals.fundamentals.commands.utils.*;
import com.keirenw.fundamentals.fundamentals.events.*;
import com.keirenw.fundamentals.fundamentals.recipes.*;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Fundamentals extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting...");

        CheckForConfigYml();

        RegisterCommands();

        List<NamespacedKey> keys = new ArrayList<>();
        keys = RegisterRecipes(keys);

        RegisterEventListeners(keys);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Destroying Fundamentals...");
    }

    /**
     * Checks for and then reads or creates the config.yml if it is missing
     */
    private void CheckForConfigYml() {
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
        {
            getLogger().info("config.yml not found, generating...");
            saveDefaultConfig();
        } else
        {
            getLogger().info("config.yml found, loading...");
            saveConfig();
        }
    }

    /**
     * Register all the plugins commands
     */
    private void RegisterCommands() {
        this.getCommand("ping").setExecutor(new CommandPing());
        this.getCommand("pong").setExecutor(new CommandPong());
        this.getCommand("sethome").setExecutor(new CommandSetHome(this));
        this.getCommand("delhome").setExecutor(new CommandDelHome(this));
        this.getCommand("home").setExecutor(new CommandHome(this));
        this.getCommand("homes").setExecutor(new CommandHomes(this));
        this.getCommand("slap").setExecutor(new CommandSlap(this));
    }

    /**
     * Register any custom recipes
     * @param keys NameSpacedKeys tied to each recipe used to register it
     * @return List\<NameSpacedKeys\> list of registered keys
     */
    private List<NamespacedKey> RegisterRecipes(List<NamespacedKey> keys) {
        // 9 Rotten Flesh --> Leather
        NamespacedKey rf2lKey = new NamespacedKey(this, "leather");
        getServer().addRecipe(new RecipeLeather(getLogger(), rf2lKey).Initialise());
        keys.add(rf2lKey);

        return keys;
    }

    /**
     * Register event listeners
     * @param recipeKeys NameSpacedKeys tied to custom recipes
     */
    private void RegisterEventListeners(List<NamespacedKey> recipeKeys) {
        getServer().getPluginManager().registerEvents(new EventPlayerJoin(getLogger(), recipeKeys), this);
    }
}
