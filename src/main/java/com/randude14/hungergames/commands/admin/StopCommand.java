package com.randude14.hungergames.commands.admin;

import com.randude14.hungergames.Config;
import com.randude14.hungergames.Defaults.Perm;
import com.randude14.hungergames.GameManager;
import com.randude14.hungergames.HungerGames;
import com.randude14.hungergames.commands.Command;
import com.randude14.hungergames.utils.ChatUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand extends Command {

	public StopCommand() {
		super(Perm.ADMIN_STOP, "stop", ADMIN_COMMAND);
	}

	@Override
	public void handle(CommandSender cs, String label, String[] args) {
		Player player = (Player) cs;
		
		String name = (args.length < 1) ? Config.getDefaultGame() : args[0];
		if (name == null) {
			ChatUtils.helpCommand(player, getUsage(), HungerGames.CMD_ADMIN);
			return;
		}
		
		game = GameManager.INSTANCE.getRawGame(name);
		if (game == null) {
		    ChatUtils.error(player, "%s does not exist.", name);
		    return;
		}
		if (!game.checkForGameOver(false)) game.stopGame(player, false);
	}

	@Override
	public String getInfo() {
		return "manually stop a game";
	}

	@Override
	public String getUsage() {
		return "/%s stop [game name]";
	}
    
}
