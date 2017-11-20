package harystolho.uberminer.crafting;

import harystolho.uberminer.init.BlockInit;
import harystolho.uberminer.utils.RecipeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCrafting {

	public static void init() {
		
		RecipeHelper.addOldShaped(new ItemStack(BlockInit.BLOCK_UBER_CRAFTER), " A ", "ABA", " A ",
				'A',	new ItemStack(Blocks.IRON_BLOCK),
				'B',	new ItemStack(Blocks.EMERALD_BLOCK));
		
		RecipeHelper.addOldShaped(new ItemStack(BlockInit.BLOCK_UBER_TABLE), "AAA", "ABA", "AAA",
				'A',	new ItemStack(Blocks.STONE),
				'B',	"logWood");

		
	}
	
}
