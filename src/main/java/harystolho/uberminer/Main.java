package harystolho.uberminer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import harystolho.uberminer.entity.EntityRegister;
import harystolho.uberminer.network.NetworkHandler;
import harystolho.uberminer.proxy.CommonProxy;
import harystolho.uberminer.utils.GuiProxy;
import harystolho.uberminer.utils.Reference;
import harystolho.uberminer.utils.handlers.RegistryHandlers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MODID, version = Reference.VERSION, name = Reference.NAME)
public class Main {

	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	public static final CreativeTabs UBERMINER = new UberMinerTab("uberminer");
	
	public static final Logger LOGGER = LogManager.getLogger(Reference.NAME);
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new RegistryHandlers());
		NetworkHandler.init();
		EntityRegister.init();
	}
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
	}
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		EntityRegister.initClient();
	}
		
	
}
