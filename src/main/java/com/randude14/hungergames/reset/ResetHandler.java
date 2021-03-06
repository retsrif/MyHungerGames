package com.randude14.hungergames.reset;

import com.randude14.hungergames.Config;
import com.randude14.hungergames.games.HungerGame;

public class ResetHandler{
    public static final String INTERNAL = "INTERNAL";
    public static final String LOGBLOCK = "LOGBLOCK";
    public static final String HAWKEYE = "HAWKEYE";
    private static Resetter resetter;
    
    
    public static void setRessetter(String string) {
	    if (string.equalsIgnoreCase(HAWKEYE)) {
		    resetter = new HawkEyeResetter();
	    }
	    else if (string.equalsIgnoreCase(LOGBLOCK)) {
		    resetter = new LogBlockResetter();
	    }
	    else {
		    resetter = new InternalResetter();
	    }
	    resetter.init();
    }
    
    public static void gameStarting(HungerGame game) {
	    resetter.beginGame(game);
    }
    
    private static boolean resetBlockChanges(HungerGame game) {
	    if (!Config.getResetChanges(game.getSetup())) return true;
	    return resetter.resetChanges(game);
    }
    
    public static boolean resetChanges(HungerGame game) {
	return resetBlockChanges(game);
    }
}
