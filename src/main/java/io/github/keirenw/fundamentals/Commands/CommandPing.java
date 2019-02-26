package io.github.keirenw.fundamentals.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPing implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//Checks that the sender of the command is a player and not the console.
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			player.sendMessage(ChatColor.YELLOW + "Pong!");
		}
		return true;
	}
}