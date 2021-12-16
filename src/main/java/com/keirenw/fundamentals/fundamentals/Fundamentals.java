package com.keirenw.fundamentals.fundamentals;

import com.keirenw.fundamentals.fundamentals.commands.admin.*;
import com.keirenw.fundamentals.fundamentals.commands.homes.*;
import com.keirenw.fundamentals.fundamentals.commands.utils.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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

        //Setup commands and event listeners
        this.getCommand("ping").setExecutor(new CommandPing());
        this.getCommand("pong").setExecutor(new CommandPong());
        this.getCommand("sethome").setExecutor(new CommandSetHome(this));
        this.getCommand("home").setExecutor(new CommandHome(this));
        this.getCommand("homes").setExecutor(new CommandHomes(this));
        this.getCommand("slap").setExecutor(new CommandSlap());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Destroying Fundamentals...");
    }
}
