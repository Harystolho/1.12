package harystolho.uberminer.objects.blocks;

import harystolho.uberminer.Main;
import harystolho.uberminer.init.BlockInit;
import harystolho.uberminer.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockUberCrafter extends Block {

	public BlockUberCrafter(String name) {
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(2.0F);
		setCreativeTab(Main.UBERMINER);

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
}
