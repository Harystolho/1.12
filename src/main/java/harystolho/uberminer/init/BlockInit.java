package harystolho.uberminer.init;

import java.util.ArrayList;
import java.util.List;

import harystolho.uberminer.objects.blocks.BlockUberTable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInit {

	@ObjectHolder("ubertable:containerubertable")
	public static BlockUberTable containerubertable;
	
	@SideOnly(Side.CLIENT)
	public static void initModels() {
		containerubertable.initModel();
	}
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_UBER_TABLE = new BlockUberTable("block_ubertable");
	
}
