package harystolho.uberminer.network;

import harystolho.uberminer.utils.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

	static SimpleNetworkWrapper INSTANCE;
	
	public void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		
	//	INSTANCE.registerMessage(MessageUberTool.Handler.class, MessageUberTool.class, 0, Side.SERVER);
	}
	

	
}
