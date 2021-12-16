package com.keirenw.fundamentals.fundamentals.commands.admin;

import com.keirenw.fundamentals.fundamentals.Fundamentals;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CommandSlap implements CommandExecutor, TabCompleter
{
    private final Fundamentals plugin;

    public CommandSlap(Fundamentals instance) {
        plugin = instance;
    }

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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<Player> players = plugin.getServer().getOnlinePlayers().stream().collect(Collectors.toList());
        List<String> playerNames = new ArrayList<>();

        for (Player player : players) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }
}
