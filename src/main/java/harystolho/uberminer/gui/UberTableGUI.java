package harystolho.uberminer.gui;

import harystolho.uberminer.inventory.ContainerUberTable;
import harystolho.uberminer.tile.TileEntityUberTable;
import harystolho.uberminer.utils.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class UberTableGUI extends GuiContainer{

	public static final int WIDTH = 176;
    public static final int HEIGHT = 124;

    private static final ResourceLocation background = new ResourceLocation(Reference.MODID, "textures/gui/uber_table.png");

    public UberTableGUI(TileEntityUberTable tileEntity, ContainerUberTable container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
