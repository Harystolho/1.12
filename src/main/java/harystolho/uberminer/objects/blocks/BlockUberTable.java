package harystolho.uberminer.objects.blocks;

import javax.annotation.Nullable;

import harystolho.uberminer.Main;
import harystolho.uberminer.init.BlockInit;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.tile.TileEntityUberTable;
import harystolho.uberminer.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockUberTable extends Block implements IHasModel {

	public BlockUberTable(String name) {
		super(Material.IRON);
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

			playerIn.openGui(Main.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}

	@Override
	@Nullable
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityUberTable();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityUberTable) {
			TileEntityUberTable tileentityiubertable = (TileEntityUberTable) tileentity;

			NonNullList<ItemStack> ret = NonNullList.create();
			getDrops(ret, worldIn, pos, state, 0);
		}
	}

	@Override
	public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos,
			IBlockState state, int fortune) {
		super.getDrops(drops, world, pos, state, fortune);
		TileEntityUberTable te = world.getTileEntity(pos) instanceof TileEntityUberTable
				? (TileEntityUberTable) world.getTileEntity(pos)
				: null;
		if (te != null && te.isEmpty()) {
			drops.add(ItemStack.EMPTY);
		} else if (te != null && !te.isEmpty()) {
			ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this), 1, 0);
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();

			nbttagcompound.setTag("BlockEntityTag", ((TileEntityUberTable) te).writeToNBT(nbttagcompound1));
			itemstack.setTagCompound(nbttagcompound);

			if (te.hasCustomName()) {
				itemstack.setStackDisplayName(te.getName());

				te.setCustomName("Uber Table");
			}
			te.getWorld().updateComparatorOutputLevel(pos, state.getBlock());
			te.getWorld().removeTileEntity(pos);
			drops.remove(0);
			drops.add(itemstack);
		}
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player,
			boolean willHarvest) {
		if (willHarvest)
			return true; // If it will harvest, delay deletion of the block until after getDrops
		return super.removedByPlayer(state, world, pos, player, willHarvest);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te,
			ItemStack tool) {
		super.harvestBlock(world, player, pos, state, te, tool);
		world.setBlockToAir(pos);
	}

}
