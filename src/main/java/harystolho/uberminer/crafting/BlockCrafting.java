package harystolho.uberminer.crafting;

import harystolho.uberminer.init.BlockInit;
import harystolho.uberminer.utils.RecipeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class BlockCrafting {

	public static void init() {
		
		RecipeHelper.addOldShaped(new ItemStack(BlockInit.BLOCK_UBER_CRAFTER), " A ", "ABA", " A ",
				'A',	new ItemStack(Blocks.DIAMOND_BLOCK, 1),
				'B',	new ItemStack(Blocks.EMERALD_BLOCK));
		
	}
	
}
