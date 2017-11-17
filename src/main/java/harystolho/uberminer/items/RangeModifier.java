package harystolho.uberminer.items;

import java.util.List;

import javax.annotation.Nullable;

import harystolho.uberminer.Main;
import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.utils.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RangeModifier extends Item implements IHasModel{

	public RangeModifier(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setMaxDamage(0);
		setCreativeTab(Main.UBERMINER);
		
		ItemInit.ITEMS.add(this);
	}
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("+1 Range");
	}
	

}
