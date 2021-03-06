package harystolho.uberminer.blocks;

import javax.annotation.Nullable;

import harystolho.uberminer.Main;
import harystolho.uberminer.init.BlockInit;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.tile.TileEntityUberCrafter;
import harystolho.uberminer.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUberCrafter extends Block implements IHasModel {

	public BlockUberCrafter(String name) {
		super(Material.ANVIL);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(2.0F);
		setCreativeTab(Main.UBERMINER);

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");

	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			playerIn.openGui(Main.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}

	@Override
	@Nullable
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityUberCrafter();
	}

	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
}
