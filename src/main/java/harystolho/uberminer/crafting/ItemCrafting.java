package harystolho.uberminer.crafting;

import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.utils.RecipeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemCrafting {

	public static void init() {
		
		RecipeHelper.addOldShaped(new ItemStack(ItemInit.TOOL_UBER), " A ", "ABA", " A ",
				'A',	new ItemStack(Blocks.IRON_BLOCK),
				'B',	new ItemStack(Items.CLOCK));
		
		RecipeHelper.addOldShaped(new ItemStack(ItemInit.MODIFIER_RANGE), "ABA", "BCB", "ABA",
				'A',	new ItemStack(Blocks.PISTON),
				'B',	new ItemStack(Blocks.DIAMOND_BLOCK),
				'C',	new ItemStack(Blocks.DRAGON_EGG));
		
		RecipeHelper.addOldShaped(new ItemStack(ItemInit.MODIFIER_SPEED), "ABA", "BCB", "ABA",
				'A',	new ItemStack(Items.BLAZE_ROD),
				'B',	new ItemStack(Blocks.REDSTONE_BLOCK),
				'C',	new ItemStack(Blocks.EMERALD_BLOCK));
		
	}

}
