package io.github.keirenw.fundamentals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Keiren Waddell
 * @version v0.1
 */

public class EventListener implements Listener
{
	//Welcome player when they join the server
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		if(!e.getPlayer().hasPlayedBefore()) {
			Bukkit.broadcastMessage(ChatColor.YELLOW + "Welcome " + ChatColor.DARK_PURPLE + e.getPlayer().getName() + ChatColor.YELLOW + " to the server!");
		} else {
			e.getPlayer().sendMessage(ChatColor.YELLOW + "Welcome back " + ChatColor.DARK_PURPLE + e.getPlayer().getName() + ChatColor.YELLOW + "!");
		}
	}
}
