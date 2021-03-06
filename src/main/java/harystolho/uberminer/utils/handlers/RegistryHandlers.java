package harystolho.uberminer.utils.handlers;

import java.util.ArrayList;
import java.util.List;

import harystolho.uberminer.Main;
import harystolho.uberminer.crafting.InitCrafting;
import harystolho.uberminer.entity.EntityUberPowder;
import harystolho.uberminer.init.BlockInit;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.tile.TileEntityUberCrafter;
import harystolho.uberminer.tile.TileEntityUberTable;
import harystolho.uberminer.utils.IHasModel;
import harystolho.uberminer.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandlers {

	public static final List<IRecipe> RECIPES = new ArrayList<>();

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));

		GameRegistry.registerTileEntity(TileEntityUberTable.class, Reference.MODID + "_ubertable");
		GameRegistry.registerTileEntity(TileEntityUberCrafter.class, Reference.MODID + "_ubercrafter");
	}

	@SubscribeEvent
	public void onCraftingRegistry(Register<IRecipe> event) {
		InitCrafting.init();

		for (IRecipe recipe : RECIPES) {
			event.getRegistry().register(recipe);
		}
		RECIPES.clear();
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		Main.LOGGER.info("Adding Items");
		for (Item item : ItemInit.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
		Main.LOGGER.info("Adding Blocks");
		for (Block block : BlockInit.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}
		}
	}

}
