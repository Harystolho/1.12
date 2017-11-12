package harystolho.uberminer.utils;

import harystolho.uberminer.gui.UberCrafterGUI;
import harystolho.uberminer.gui.UberTableGUI;
import harystolho.uberminer.inventory.ContainerUberCrafter;
import harystolho.uberminer.inventory.ContainerUberTable;
import harystolho.uberminer.tile.TileEntityUberCrafter;
import harystolho.uberminer.tile.TileEntityUberTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

	public static final int TABLE = 0;
	public static final int CRAFTER = 1;

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case TABLE:
			return new ContainerUberTable(player.inventory,
					(TileEntityUberTable) world.getTileEntity(new BlockPos(x, y, z)));
		case CRAFTER:
			return new ContainerUberCrafter(player.inventory,
					(TileEntityUberCrafter) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case TABLE:
			TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
			if (te instanceof TileEntityUberTable) {
				TileEntityUberTable containerTileEntity = (TileEntityUberTable) te;
				return new UberTableGUI(new ContainerUberTable(player.inventory, containerTileEntity),
						player.inventory);
			}
		case CRAFTER:
			TileEntity te1 = world.getTileEntity(new BlockPos(x, y, z));
			if (te1 instanceof TileEntityUberCrafter) {
				TileEntityUberCrafter containerTileEntity = (TileEntityUberCrafter) te1;
				return new UberCrafterGUI(new ContainerUberCrafter(player.inventory, containerTileEntity),
						player.inventory);
			}
		default:
			return null;
		}
	}
}