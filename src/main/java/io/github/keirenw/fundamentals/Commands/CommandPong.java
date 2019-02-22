package io.github.keirenw.fundamentals.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPong implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " likes cute asian boys");
			
			return true;
		}
		return false;
	}
}
