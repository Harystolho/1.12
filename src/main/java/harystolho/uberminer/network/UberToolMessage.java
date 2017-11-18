package harystolho.uberminer.network;

import harystolho.uberminer.init.ItemInit;
import harystolho.uberminer.items.BowUber;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UberToolMessage implements IMessage {
	private double stage;
	private EntityPlayer player;

	public UberToolMessage(double stage, EntityPlayer player) {
		this.stage = stage;
		this.player = player;
	}

	public UberToolMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.stage = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(stage);
	}

	public static class Handler implements IMessageHandler<UberToolMessage, IMessage> {
		@Override
		public IMessage onMessage(final UberToolMessage message, final MessageContext ctx) {

			return null;
		}
	}
}
