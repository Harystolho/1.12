package harystolho.uberminer.booklet;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import harystolho.uberminer.utils.Reference;
import io.netty.util.internal.StringUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiPageButtonList.GuiEntry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class BookletGUI extends GuiScreen {

	public static int BUTTONS = 3;
	public static ResourceLocation RES_GUI = new ResourceLocation(Reference.MODID, "booklet/booklet_gui");
	public static ResourceLocation RES_BUTTONS = new ResourceLocation(Reference.MODID, "booklet/booklet_buttons");

	public static GuiScreen pScreen;
	public static int xSize;
	public static int ySize;

	public BookletGUI(GuiScreen previousScreen, GuiScreen parentPage) {
		this.pScreen = previousScreen;

		this.xSize = 281;
		this.ySize = 180;
	}

	public void init() {
		this.buttonList.add(new BookletButton(47009, 100, 100, 50, 30, "Button"));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawScreenInit(mouseX, mouseY, partialTicks);
		drawScreenPost(mouseX, mouseY, partialTicks);
	}

	public void drawScreenInit(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(RES_GUI);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

	public void drawScreenPost(int mouseX, int mouseY, float partialTicks) {
		for (int i = 0; i < this.buttonList.size(); ++i) {
			((GuiButton) this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY, partialTicks);
		}

		for (int j = 0; j < this.labelList.size(); ++j) {
			((GuiLabel) this.labelList.get(j)).drawLabel(this.mc, mouseX, mouseY);
		}
	}
}