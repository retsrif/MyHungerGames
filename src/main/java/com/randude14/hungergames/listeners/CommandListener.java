package com.randude14.hungergames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.randude14.hungergames.Config;
import com.randude14.hungergames.GameManager;
import com.randude14.hungergames.HungerGamesBukkit;
import com.randude14.hungergames.games.HungerGame;
import com.randude14.hungergames.utils.ChatUtils;

public class CommandListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.startsWith("/" + HungerGamesBukkit.CMD_ADMIN) || message.startsWith("/" + HungerGamesBukkit.CMD_USER)) return;
		HungerGame session = GameManager.INSTANCE.getRawPlayingSession(player);
		if(session == null) return;
		message = message.split(" ")[0];
		if(Config.getUseCommand(session.getSetup()) ^ Config.getSpecialCommands(session.getSetup()).contains("/" + message)) {
			ChatUtils.error(player, "Cannot use that command while in game %s.", session.getName());
			event.setCancelled(true);
		}
		
	}

}
