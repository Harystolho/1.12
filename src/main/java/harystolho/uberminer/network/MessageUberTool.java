package harystolho.uberminer.network;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class MessageUberTool extends MessageHandler<MessageUberTool>{

	private List<ItemStack> list = new ArrayList<ItemStack>();
	
	public MessageUberTool() {}
	
	public MessageUberTool(List<ItemStack> list) {
		this.list = list;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	@Override
	public void handleClientSide(MessageUberTool message, EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(MessageUberTool message, EntityPlayer player) {
		
	}

}
