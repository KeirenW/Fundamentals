package io.github.keirenw.fundamentals.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public class CommandSlap implements CommandExecutor
{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//Checks that the sender of the command is a player and not the console.
		if (sender instanceof Player && args.length == 1)
		{
			Player player = (Player) sender;
			Player slapped = Bukkit.getPlayer(args[0]);
			//Check if player is online
			if (slapped == null)
			{
				player.sendMessage(ChatColor.RED + "That player is not online");
				return false;
			}
			
			//Create a vector for giving the player velocity
			Random rnd = new Random();
			Vector vel = new Vector(rnd.nextFloat() * (0.4f), 0, rnd.nextFloat() * (0.4f));
			slapped.setVelocity(vel);
			
			Bukkit.broadcastMessage(ChatColor.RED + player.getDisplayName() + ChatColor.YELLOW + " slapped " + slapped.getDisplayName());
			
			return true;
		}
		return false;
	}
}