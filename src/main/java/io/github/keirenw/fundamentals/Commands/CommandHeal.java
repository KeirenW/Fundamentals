package io.github.keirenw.fundamentals.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHeal implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(player.getHealth() < 20) {
				player.setHealth(20);
				player.sendMessage(ChatColor.YELLOW + "You have been healed!");
			} else {
				player.sendMessage(ChatColor.YELLOW + "You don't need healed!");
			}
		}
		
		return true;
	}
}
