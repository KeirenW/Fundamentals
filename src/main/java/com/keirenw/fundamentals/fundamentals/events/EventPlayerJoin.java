package com.keirenw.fundamentals.fundamentals.events;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.logging.Logger;

public class EventPlayerJoin implements Listener {
    Logger _logger;
    List<NamespacedKey> keys;

    public EventPlayerJoin(Logger logger, List<NamespacedKey> keys) {
        this._logger = logger;
        this.keys = keys;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Discover custom recipes if not already known
        for (NamespacedKey key : keys) {
            if (!player.getDiscoveredRecipes().contains(key))
                player.discoverRecipe(key);

        }
    }
}
