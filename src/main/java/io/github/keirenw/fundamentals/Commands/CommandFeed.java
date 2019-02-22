package io.github.keirenw.fundamentals.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFeed implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.getFoodLevel() < 20) {
				player.setFoodLevel(20);
				player.setSaturation(20);
				player.sendMessage(ChatColor.YELLOW + "Appetite sated!");
			} else {
				player.sendMessage(ChatColor.YELLOW + "You are not hungry!");
			}
		}
		
		return true;
	}
}
