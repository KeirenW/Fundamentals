package com.keirenw.fundamentals.fundamentals.commands.homes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandSpawn implements CommandExecutor {

    public CommandSpawn() {
    }

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, final @NotNull Command cmd, final @NotNull String label, final String[] args) {
        if (sender instanceof Player player) {
            try {
                Location spawn = Bukkit.getWorld("world").getSpawnLocation();
                player.teleport(spawn);

                player.sendMessage(ChatColor.YELLOW + "Teleporting to spawn...");

            } catch (Exception e) {
                Bukkit.getLogger().throwing(this.toString(), "onCommand()", e);
                player.sendMessage(ChatColor.RED + "Error teleporting to spawn");
                return false;
            }
        }

        return true;
    }
}
