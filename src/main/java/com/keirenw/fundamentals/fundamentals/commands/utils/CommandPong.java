package com.keirenw.fundamentals.fundamentals.commands.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandPong implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        //Checks that the sender of the command is a player and not the console.
        if(sender instanceof Player player) {
            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " likes cute asian boys");
            return true;
        }
        return false;
    }
}
