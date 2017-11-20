// This class was created by Ellpeck

package harystolho.uberminer.entity;

import harystolho.uberminer.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class UberPowderRender extends Render<EntityUberPowder> {

	public static final IRenderFactory<EntityUberPowder> FACTORY = new IRenderFactory<EntityUberPowder>() {
		@Override
		public Render<EntityUberPowder> createRenderFor(RenderManager manager) {
			return new UberPowderRender(manager);
		}
	};

	private static ItemStack stack = ItemStack.EMPTY;

	public static void fixItemStack() {
		stack = new ItemStack(ItemInit.POWDER_UBER);
	}

	protected UberPowderRender(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityUberPowder entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	@Override
	public void doRender(EntityUberPowder entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y + 0.7F, z);
		double boop = Minecraft.getSystemTime() / 70D;
		GlStateManager.rotate(-(float) ((boop % 360)), 0, 1, 0);
		GlStateManager.translate(0, 0, 0.4);

		stack.setStackDisplayName(entity.getName());
		renderItemInWorld(stack);

		GlStateManager.popMatrix();
	}

	@SideOnly(Side.CLIENT)
	public static void renderItemInWorld(ItemStack stack) {
		if (isValid(stack)) {
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			GlStateManager.pushAttrib();
			RenderHelper.enableStandardItemLighting();
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.popAttrib();
			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}

	public static boolean isValid(ItemStack stack) {
		return !stack.isEmpty();
	}
}
