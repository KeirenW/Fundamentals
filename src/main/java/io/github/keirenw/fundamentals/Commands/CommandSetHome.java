package io.github.keirenw.fundamentals.Commands;

import io.github.keirenw.fundamentals.Fundamentals;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetHome implements CommandExecutor {
	private Fundamentals plugin;
	
	public CommandSetHome(Fundamentals instance)
	{
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//Checks that the sender of the command is a player and not the console.
		if(sender instanceof Player)
		{
			//Create a new instance of Player as the command sender.
			Player player = (Player) sender;
			
			//Create an array to store the position and direction of the player
			double[] coords = {player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()};
			
			if (args.length == 0) {
				plugin.getConfig().set("player." + player.getName() + ".home.world", player.getWorld().getName());
				plugin.getConfig().set("player." + player.getName() + ".home.x", coords[0]);
				plugin.getConfig().set("player." + player.getName() + ".home.y", coords[1]);
				plugin.getConfig().set("player." + player.getName() + ".home.z", coords[2]);
				plugin.getConfig().set("player." + player.getName() + ".home.yaw", coords[3]);
				plugin.getConfig().set("player." + player.getName() + ".home.pitch", coords[4]);
			} else if (args.length == 1) {
				plugin.getConfig().set("player." + player.getName() + "." + args[0] + ".world", player.getWorld().getName());
				plugin.getConfig().set("player." + player.getName() + "." + args[0] + ".x", coords[0]);
				plugin.getConfig().set("player." + player.getName() + "." + args[0] + ".y", coords[1]);
				plugin.getConfig().set("player." + player.getName() + "." + args[0] + ".z", coords[2]);
				plugin.getConfig().set("player." + player.getName() + "." + args[0] + ".yaw", coords[3]);
				plugin.getConfig().set("player." + player.getName() + "." + args[0] + ".pitch", coords[4]);
			} else {
				return false;
			}
			
			plugin.saveConfig();
			plugin.reloadConfig();
			
			player.sendMessage(ChatColor.YELLOW + "Home set!");
			return true;
		}
		return false;
	}
}
