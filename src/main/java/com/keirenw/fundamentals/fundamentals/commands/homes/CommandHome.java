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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandHome implements CommandExecutor, TabCompleter {
    private final Fundamentals plugin;

    public CommandHome(Fundamentals instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, final @NotNull Command cmd, final @NotNull String label, final String[] args) {
        if (sender instanceof Player player) {
            player.getUniqueId();

            try {
                String basePath = getBasePath(args, player);

                World world = Bukkit.getWorld(plugin.getConfig().getString(basePath + "world"));
                double x = plugin.getConfig().getDouble(basePath + "x");
                double y = plugin.getConfig().getDouble(basePath + "y");
                double z = plugin.getConfig().getDouble(basePath + "z");
                double yaw = plugin.getConfig().getDouble(basePath + "yaw");
                double pitch = plugin.getConfig().getDouble(basePath + "pitch");

                player.teleport(new Location(world, x, y, z, (float) yaw, (float) pitch));

                if (args.length == 0) player.sendMessage(ChatColor.YELLOW + "Teleporting Home...");
                else player.sendMessage(ChatColor.YELLOW + "Teleporting to " + args[0] + "...");

            } catch (Exception e) {
                Bukkit.getLogger().info(e.getMessage());
                player.sendMessage(ChatColor.RED + "You don't have a home set!");
                return false;
            }
        }

        return true;
    }

    private String getBasePath(String[] args, Player player) throws Exception {
        String basePath = "player.";
        if (args.length == 0 || args[0].toLowerCase() == "home") {
            // Home name is 'home' or blank
            basePath = basePath.concat(player.getUniqueId() + ".home.");
        } else if (args.length == 1) {
            basePath = basePath.concat(player.getUniqueId() + "." + args[0].toLowerCase() + ".");
        } else {
            throw new Exception("Argument list not recognised");
        }
        return basePath;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;

        final String PATH = "player." + player.getUniqueId();
        FileConfiguration config = plugin.getConfig();
        Map<String, Object> homesMap = config.getConfigurationSection(PATH).getValues(false);

        List<String> homes = new ArrayList<>();
        homesMap.keySet().iterator()
                .forEachRemaining(home -> homes.add(home));
        return homes;
    }
}
