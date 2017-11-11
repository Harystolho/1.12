package harystolho.uberminer.objects.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import harystolho.uberminer.Main;
import harystolho.uberminer.init.BlockInit;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.inventory.ContainerUberTable;
import harystolho.uberminer.tile.TileEntityUberTable;
import harystolho.uberminer.tile.TileEntityUberTable;
import harystolho.uberminer.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.SidedProxy;

public class BlockUberTable extends Block implements IHasModel{

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
    public TileEntity createTileEntity(World world, IBlockState state)
    {
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

			if (!tileentityiubertable.isEmpty()) {
				ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this), 1, 0);
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();

				nbttagcompound.setTag("BlockEntityTag", ((TileEntityUberTable) tileentity).writeToNBT(nbttagcompound1));
				itemstack.setTagCompound(nbttagcompound);

				if (tileentityiubertable.hasCustomName()) {
					itemstack.setStackDisplayName(tileentityiubertable.getName());

					tileentityiubertable.setCustomName("Uber Table");
				}
				System.out.println("Breaking...");
				spawnAsEntity(worldIn, pos, itemstack);

				worldIn.updateComparatorOutputLevel(pos, state.getBlock());
			} else {
				super.getDrops(worldIn, pos, state, 0);
			}
		}
	}

}
