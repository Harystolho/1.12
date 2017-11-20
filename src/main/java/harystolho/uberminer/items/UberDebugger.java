package harystolho.uberminer.items;

import harystolho.uberminer.Main;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.utils.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UberDebugger extends Item implements IHasModel {

	public UberDebugger(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setCreativeTab(Main.UBERMINER);

		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			BlockPos pos2 = new BlockPos(hitX, hitY, hitZ);
			IBlockState blockstate = worldIn.getBlockState(pos);
			System.out.println(blockstate.getBlock().getLocalizedName());

		}

		return EnumActionResult.PASS;
	}

}
