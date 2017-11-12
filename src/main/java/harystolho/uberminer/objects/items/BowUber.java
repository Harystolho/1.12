package harystolho.uberminer.objects.items;

import java.util.List;

import javax.annotation.Nullable;
import javax.management.MBeanTrustPermission;

import harystolho.uberminer.Main;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.utils.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

	int durability;
	int radius;
	int fortune;
	int speed;

	public BowUber(String name) {
		durability = 100;
		radius = 1;
		fortune = 0;
		speed = 1;
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
							: (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F * speed;
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
		if(nbttagcompound == null) {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setString("ToolDurability", "100");
			nbttagcompound1.setString("ToolRadius", "1");
			nbttagcompound1.setString("ToolSpeed", "1");
			nbttagcompound1.setString("ToolModifiers", "");
			
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
			for (int x = -radius; x < radius + 1; x++) {
				for (int y = -radius; y < radius + 1; y++) {
					for (int z = -radius; z < radius + 1; z++) {
						BlockPos blockpos = new BlockPos(entityLiving.getPosition().getX() + x,
								entityLiving.getPosition().getY() + y, entityLiving.getPosition().getZ() + z);
						IBlockState iblockstate = worldIn.getBlockState(blockpos);
						System.out.println(iblockstate.getBlock());
					}
				}
			}
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 400;
	}

	public int getDurability() {

		return durability;
	}

	public void UpdateNBT() {

	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound != null) {
			NBTTagCompound nbttag_darability = nbttagcompound.getCompoundTag("ToolDurability");
			NBTTagCompound nbttag_radius = nbttagcompound.getCompoundTag("ToolRadius");
			NBTTagCompound nbttag_speed = nbttagcompound.getCompoundTag("ToolSpeed");
			NBTTagCompound nbttag_modifier = nbttagcompound.getCompoundTag("ToolModifiers");
			tooltip.add("Durability: " + nbttag_darability.toString());
			tooltip.add("Radius: " + nbttag_radius.toString());
			tooltip.add("Speed: " + nbttag_speed.toString());
			tooltip.add("Modifiers: [" + nbttag_modifier.toString() + "]");
		}
	}

}
