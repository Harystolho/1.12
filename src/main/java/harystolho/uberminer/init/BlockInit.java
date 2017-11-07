package harystolho.uberminer.init;

import java.util.ArrayList;
import java.util.List;

import harystolho.uberminer.objects.blocks.BlockUberTable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	
	public static final Block BLOCK_UBER_TABLE = new BlockUberTable("block_ubertable", Material.IRON);
	
}