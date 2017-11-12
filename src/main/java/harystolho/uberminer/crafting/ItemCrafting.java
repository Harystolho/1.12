package harystolho.uberminer.crafting;

import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.utils.RecipeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ItemCrafting {

	public static void init() {
		
		RecipeHelper.addOldShaped(new ItemStack(ItemInit.TOOL_UBER), "   ", " B ", "   ",
				'B',	new ItemStack(Blocks.DRAGON_EGG));
		
	}

}
