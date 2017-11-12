package harystolho.uberminer.crafting;

import harystolho.uberminer.Main;

public class InitCrafting {

	public static void init() {
		Main.LOGGER.info("Adding recipes");
		
		BlockCrafting.init();
		ItemCrafting.init();
		
	}
	
}
