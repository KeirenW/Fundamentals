package com.keirenw.fundamentals.fundamentals.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CommandSlap implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args)
    {
        //Checks that the sender of the command is a player and not the console.
        if (sender instanceof Player player && args.length == 1)
        {
            Player slapped = Bukkit.getPlayer(args[0]);
            //Check if player is online
            if (slapped == null)
            {
                player.sendMessage(ChatColor.RED + "That player is not online");
            } else {
                //Create a vector for giving the player velocity
                Random rnd = new Random();
                Vector vel = new Vector(rnd.nextFloat(-0.4f,0.4f), 0, rnd.nextFloat(-0.4f,0.4f));

                slapped.setVelocity(vel);
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " slapped " + slapped.getName());
            }

            return true;
        }
        return false;
    }
}
