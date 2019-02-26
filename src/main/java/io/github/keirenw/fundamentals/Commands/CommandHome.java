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

public class CommandHome implements CommandExecutor
{
	private Fundamentals plugin;
	
	public CommandHome(Fundamentals instance)
	{
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//Checks that the sender of the command is a player and not the console.
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			World world;
			double x;
			double y;
			double z;
			double yaw;
			double pitch;
			
			//Check to see if player has a home set and catch exception if not
			try
			{
				//Check if a home name is given
				if (args.length == 0)
				{
					//No name given, default to home
					world = Bukkit.getWorld(plugin.getConfig().getString("player." + player.getName() + ".home.world"));
					x = plugin.getConfig().getDouble("player." + player.getName() + ".home.x");
					y = plugin.getConfig().getDouble("player." + player.getName() + ".home.y");
					z = plugin.getConfig().getDouble("player." + player.getName() + ".home.z");
					yaw = plugin.getConfig().getDouble("player." + player.getName() + ".home.yaw");
					pitch = plugin.getConfig().getDouble("player." + player.getName() + ".home.pitch");
					
					player.teleport(new Location(world, x, y, z, (float) yaw, (float) pitch));
				} else if (args.length == 1)
				{
					//Name given tp to named home
					world = Bukkit.getWorld(plugin.getConfig().getString("player." + player.getName() + "." + args[0] + ".world"));
					x = plugin.getConfig().getDouble("player." + player.getName() + "." + args[0] + ".x");
					y = plugin.getConfig().getDouble("player." + player.getName() + "." + args[0] + ".y");
					z = plugin.getConfig().getDouble("player." + player.getName() + "." + args[0] + ".z");
					yaw = plugin.getConfig().getDouble("player." + player.getName() + "." + args[0] + ".yaw");
					pitch = plugin.getConfig().getDouble("player." + player.getName() + "." + args[0] + ".pitch");
					
					player.teleport(new Location(world, x, y, z, (float) yaw, (float) pitch));
				} else if (args.length == 2)
				{
					//TP to others home
					//TODO permissions for this and implement.
					return false;
				}
				
				player.sendMessage(ChatColor.YELLOW + "Teleporting Home...");
			} catch (Exception e)
			{
				player.sendMessage(ChatColor.RED + "You don't have a home set!");
				return false;
			}
		}
		return true;
	}
}