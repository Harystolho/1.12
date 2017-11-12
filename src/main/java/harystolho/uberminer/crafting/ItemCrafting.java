package harystolho.uberminer.crafting;

import harystolho.uberminer.init.BlockInit;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.utils.RecipeHelper;
import harystolho.uberminer.utils.handlers.RegistryHandlers;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class ItemCrafting {

	public static IRecipe recipeTool;

	public static void init() {

		System.out.println("=======================---------------------------------");
		
		RecipeHelper.addOldShaped(new ItemStack(BlockInit.BLOCK_UBER_CRAFTER), " A ", "ABA", " A ",
				'A',	new ItemStack(Blocks.DIAMOND_BLOCK, 1),
				'B',	new ItemStack(Blocks.EMERALD_BLOCK));

	}

}
