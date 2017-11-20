package harystolho.uberminer.items;

import java.util.List;

import javax.annotation.Nullable;

import harystolho.uberminer.Main;
import harystolho.uberminer.entity.EntityUberPowder;
import harystolho.uberminer.entity.EntityUberPowder2;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.utils.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UberPowderTier2 extends Item implements IHasModel {
	
	public UberPowderTier2(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = 16;
		this.setCreativeTab(Main.UBERMINER);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	/**
	 * Called when the equipped item is right clicked.
	 */
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (!playerIn.capabilities.isCreativeMode) {
			itemstack.shrink(1);
		}

		worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
				SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.5F,
				0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		playerIn.getCooldownTracker().setCooldown(this, 20);

		if (!worldIn.isRemote) {
			EntityUberPowder2 entityuberpowder = new EntityUberPowder2(worldIn, playerIn);
			entityuberpowder.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F,
					1.0F);
			worldIn.spawnEntity(entityuberpowder);
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("5x5");
		tooltip.add("Drop change: 80%");
	}
}