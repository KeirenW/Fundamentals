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
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			double xCoord = player.getLocation().getX();
			double yCoord = player.getLocation().getY();
			double zCoord = player.getLocation().getZ();
			
			plugin.getConfig().set("players." + player.getName() + ".home.world", player.getWorld().getName());
			plugin.getConfig().set("players." + player.getName() + ".home.x", xCoord);
			plugin.getConfig().set("players." + player.getName() + ".home.y", yCoord);
			plugin.getConfig().set("players." + player.getName() + ".home.z", zCoord);
			plugin.saveConfig();
			plugin.reloadConfig();
			
			player.sendMessage(ChatColor.YELLOW + "Home set!");
			return true;
		}
		return false;
	}
}
