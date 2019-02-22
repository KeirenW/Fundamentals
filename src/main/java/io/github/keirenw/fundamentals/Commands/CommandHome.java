package io.github.keirenw.fundamentals.Commands;

import io.github.keirenw.fundamentals.Fundamentals;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHome implements CommandExecutor {
	private Fundamentals plugin;
	
	public CommandHome(Fundamentals instance)
	{
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			//Check to see if player has a home set and catch exception if not
			try
			{
				//Get the world the home is in
				String homeWorld = plugin.getConfig().getString("players." + player.getName() + ".home.world");
				World world = Bukkit.getWorld(homeWorld);
				//Get X, Y, Z coords for home
				double xCoord = plugin.getConfig().getDouble("players." + player.getName() + ".home.x");
				double yCoord = plugin.getConfig().getDouble("players." + player.getName() + ".home.y");
				double zCoord = plugin.getConfig().getDouble("players." + player.getName() + ".home.z");
				
				player.teleport(new Location(world, xCoord, yCoord, zCoord));
				
				player.sendMessage(ChatColor.YELLOW + "Teleporting Home...");
			} catch(Exception e) {
				player.sendMessage(ChatColor.RED + "You don't have a home set!");
			}
		}
		return true;
	}
}