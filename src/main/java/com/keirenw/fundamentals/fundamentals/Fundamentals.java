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

        //Check for config file, if not found generate from default in jar file
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

        // Commands
        this.getCommand("ping").setExecutor(new CommandPing());
        this.getCommand("pong").setExecutor(new CommandPong());
        this.getCommand("spawn").setExecutor(new CommandSpawn());
        this.getCommand("sethome").setExecutor(new CommandSetHome(this));
        this.getCommand("delhome").setExecutor(new CommandDelHome(this));
        this.getCommand("home").setExecutor(new CommandHome(this));
        this.getCommand("homes").setExecutor(new CommandHomes(this));
        this.getCommand("slap").setExecutor(new CommandSlap(this));

        // Recipes
        List<NamespacedKey> keys = new ArrayList<>();
            // 9 Rotten Flesh -> Leather
            NamespacedKey rf2lKey = new NamespacedKey(this, "leather");
            getServer().addRecipe(new RecipeLeather(getLogger(), rf2lKey).Initialise());
            keys.add(rf2lKey);

        // Event Listeners
        getServer().getPluginManager().registerEvents(new EventPlayerJoin(getLogger(), keys), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Destroying Fundamentals...");
    }
}
