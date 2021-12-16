package com.keirenw.fundamentals.fundamentals.commands.homes;

import com.keirenw.fundamentals.fundamentals.Fundamentals;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class CommandHomes implements CommandExecutor {
    private final Fundamentals plugin;

    public CommandHomes(Fundamentals instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (sender instanceof Player player) {

            try {
                final String PATH = "player." + player.getUniqueId();
                FileConfiguration config = plugin.getConfig();
                Map<String, Object> homesMap = config.getConfigurationSection(PATH).getValues(false);

                player.sendMessage(ChatColor.YELLOW + "HOMES:");

                List<String> homes = new ArrayList<>();
                homesMap.keySet().iterator()
                        .forEachRemaining(home -> player.sendMessage("  - " + home));

                return true;
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.SEVERE, e.getMessage());
                return false;
            }

        }

        return false;
    }

    public String convertWithStream(Map<String, ?> map) {
        Iterator<String> itr = map.keySet().iterator();
        while (map.keySet().iterator().hasNext()) {
            plugin.getLogger().log(Level.INFO, map.keySet().stream().toList().toString());
            plugin.getLogger().log(Level.INFO, itr.next());
        }
        return "";
    }
}
