package com.keirenw.fundamentals.fundamentals.commands.homes;

import com.keirenw.fundamentals.fundamentals.Fundamentals;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandDelHome implements CommandExecutor, TabCompleter {
    private final Fundamentals plugin;

    public CommandDelHome(Fundamentals instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            player.getUniqueId();

            try {
                String basePath = getBasePath(args, player);
                plugin.getConfig().set(basePath, null);
                player.sendMessage(ChatColor.YELLOW + "Deleting Home '" + args[0] + "'");
            } catch (Exception e) {
                Bukkit.getLogger().info(e.getMessage());
                player.sendMessage(ChatColor.RED + "Unable to delete home " + args[0]);
                return false;
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        Player player = (Player) sender;

        final String PATH = "player." + player.getUniqueId();
        FileConfiguration config = plugin.getConfig();
        Map<String, Object> homesMap = config.getConfigurationSection(PATH).getValues(false);

        List<String> homes = new ArrayList<>();
        homesMap.keySet().iterator()
                .forEachRemaining(home -> homes.add(home));
        return homes;
    }

    private String getBasePath(String[] args, Player player) throws Exception {
        String basePath = "player.";
        if (args.length == 1) {
            basePath = basePath.concat(player.getUniqueId() + "." + args[0].toLowerCase());
        } else {
            throw new Exception("Argument list not recognised");
        }
        return basePath;
    }
}
