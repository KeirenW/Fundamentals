package com.keirenw.fundamentals.fundamentals.commands.homes;

import com.keirenw.fundamentals.fundamentals.Fundamentals;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetHome implements CommandExecutor {
    private final Fundamentals plugin;

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
            final Location playerLoc = player.getLocation();

            String basePath = "player.";
            if (args.length == 0 || args[0].toLowerCase() == "home") {
                // Home name is 'home' or blank
                basePath = basePath.concat(player.getUniqueId() + ".home.");
            } else if (args.length == 1) {
                basePath = basePath.concat(player.getUniqueId() + "." + args[0].toLowerCase() + ".");
            } else {
                return false;
            }

            plugin.getConfig().set(basePath + "world", player.getWorld().getName());
            plugin.getConfig().set(basePath + "x", playerLoc.getX());
            plugin.getConfig().set(basePath + "y", playerLoc.getY());
            plugin.getConfig().set(basePath + "z", playerLoc.getZ());
            plugin.getConfig().set(basePath + "yaw", playerLoc.getYaw());
            plugin.getConfig().set(basePath + "pitch", playerLoc.getPitch());

            plugin.saveConfig();
            plugin.reloadConfig();

            player.sendMessage(ChatColor.YELLOW + "Home set!");
            return true;
        }
        return false;
    }
}
