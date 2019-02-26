package io.github.keirenw.fundamentals;

import io.github.keirenw.fundamentals.Commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Fundamentals extends JavaPlugin
{
	
	@Override
	public void onEnable()
	{
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
		
		//Setup commands and event listener
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		this.getCommand("ping").setExecutor(new CommandPing());
		this.getCommand("pong").setExecutor(new CommandPong());
		this.getCommand("sethome").setExecutor(new CommandSetHome(this));
		this.getCommand("home").setExecutor(new CommandHome(this));
		this.getCommand("slap").setExecutor(new CommandSlap());
	}
	
	@Override
	public void onDisable()
	{
	
	}
}
