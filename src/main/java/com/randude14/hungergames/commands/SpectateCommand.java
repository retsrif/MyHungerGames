package com.randude14.hungergames.commands;

import com.randude14.hungergames.Defaults.CommandUsage;
import com.randude14.hungergames.Defaults.Perm;
import com.randude14.hungergames.GameManager;
import com.randude14.hungergames.HungerGames;
import com.randude14.hungergames.games.HungerGame;

import com.randude14.hungergames.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 */
public class SpectateCommand extends SubCommand{

    @Override
    public boolean execute(CommandSender cs, Command cmd, String[] args) {
	    Player player = (Player) cs;
	    if (!HungerGames.checkPermission(player, Perm.USER_SPECTATE)) return true;
	    if (GameManager.getGame(HungerGames.getSpectating(player)) != null) {
		    GameManager.getGame(HungerGames.getSpectating(player)).removeSpectator(player);
		    HungerGames.removeSpectator(player);
		    return true;
	    }
	    if (args.length < 1) {
		    ChatUtils.send(player, CommandUsage.USER_SPONSOR.getUsage(), cmd.getLabel());
		    return true;
	    }
	    HungerGame game = GameManager.getGame(args[0]);
	    if (game == null || !game.isRunning()) {
		    ChatUtils.error(player, "%s is not a running game.", game.getName());
		    return true;
	    }
	    game.addSpectator(player);
	    HungerGames.addSpectator(player, game.getName());
	    return true;
    }
    
}
