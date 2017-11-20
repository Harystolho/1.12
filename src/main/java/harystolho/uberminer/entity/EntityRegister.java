package harystolho.uberminer.entity;

import harystolho.uberminer.Main;
import harystolho.uberminer.utils.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRegister {

	 public static void init(){
	        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "uber_powder_1"), EntityUberPowder.class, Reference.MODID+"uber_poweder_1", 0, Main.instance, 64, 1, false);
	    }

	    @SideOnly(Side.CLIENT)
	    public static void initClient(){
	        RenderingRegistry.registerEntityRenderingHandler(EntityUberPowder.class, UberPowderRender.FACTORY);
	    }
	
}
