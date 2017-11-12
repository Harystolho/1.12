package harystolho.uberminer.objects.items;

import java.util.List;

import javax.annotation.Nullable;

import harystolho.uberminer.Main;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BowUber extends Item implements IHasModel {

	public BowUber(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.UBERMINER);
		setMaxStackSize(1);
		addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0.0F;
				} else {
					return entityIn.getActiveItemStack().getItem() != ItemInit.TOOL_UBER ? 0.0F
							: (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
				}
			}
		});
		addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F
						: 0.0F;
			}
		});

		ItemInit.ITEMS.add(this);

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		NBTTagCompound nbttagcompound = playerIn.getHeldItemMainhand().getTagCompound();
		if (nbttagcompound == null) {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setInteger("ToolDurability", 100);
			nbttagcompound1.setInteger("ToolRadius", 1);
			nbttagcompound1.setInteger("ToolSpeed", 1);
			nbttagcompound1.setString("ToolModifiers", "---");

			playerIn.getHeldItemMainhand().setTagCompound(nbttagcompound1);
		}

		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = true;

		if (!playerIn.capabilities.isCreativeMode && !flag) {
			return flag ? new ActionResult(EnumActionResult.PASS, itemstack)
					: new ActionResult(EnumActionResult.FAIL, itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {

		if ((stack.getMaxItemUseDuration() - entityLiving.getItemInUseCount()) / 20.0F > 5.0) {
			NBTTagCompound tag = stack.getTagCompound();
			int radius = tag.getInteger("ToolRadius");
			for (int x = -radius; x < radius + 1; x++) {
				for (int y = -radius; y < radius + 1; y++) {
					for (int z = -radius; z < radius + 1; z++) {
						BlockPos blockpos = new BlockPos(entityLiving.getPosition().getX() + x,
								entityLiving.getPosition().getY() + y, entityLiving.getPosition().getZ() + z);
						IBlockState iblockstate = worldIn.getBlockState(blockpos);
						if (isOre(iblockstate.getBlock())) {
							IBlockState stone = Blocks.STONE.getDefaultState();
							worldIn.setBlockState(blockpos, stone);
							ItemStack item = new ItemStack(iblockstate.getBlock());
						
							entityLiving.entityDropItem(item, 1F);
						}
					}
				}
			}
		}
	}
	
	/*	EntityItem t = new EntityItem(worldIn);
	t.setItem(item);
	t.setPosition(entityLiving.getPosition().getX(), entityLiving.getPosition().getY()+1, entityLiving.getPosition().getZ());
	t.setPickupDelay(1);
	worldIn.spawnEntity(t);*/

	// @SideOnly(Side.SERVER)
	public boolean isOre(Block block) {
		if (block.getRegistryName().toString().toLowerCase().contains("ore")
				|| block.getUnlocalizedName().toString().toLowerCase().contains("ore")) {
			return true;
		}
		return false;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 400;
	}

	public void UpdateNBT() {

	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound != null) {
			int nbttag_darability = nbttagcompound.getInteger("ToolDurability");
			int nbttag_radius = nbttagcompound.getInteger("ToolRadius");
			int nbttag_speed = nbttagcompound.getInteger("ToolSpeed");
			String nbttag_modifier = nbttagcompound.getString("ToolModifiers");
			tooltip.add("Durability: " + nbttag_darability);
			tooltip.add("Radius: " + nbttag_radius);
			tooltip.add("Speed: " + nbttag_speed);
			tooltip.add("Modifiers: [" + nbttag_modifier + "]");
		}
	}

}
