package harystolho.uberminer.inventory;

import harystolho.uberminer.tile.TileEntityUberCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerUberCrafter extends Container {
	private TileEntityUberCrafter te;

	public ContainerUberCrafter(IInventory playerInventory, TileEntityUberCrafter te) {
		this.te = te;

		addOwnSlots();
		addPlayerSlots(playerInventory);
	}

	private void addPlayerSlots(IInventory playerInventory) {
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				int x = 8 + col * 18;
				int y = row * 18 + 84;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
			}
		}

		for (int row = 0; row < 9; ++row) {
			int x = 8 + row * 18;
			int y = 142;
			this.addSlotToContainer(new Slot(playerInventory, row, x, y));
		}
	}

	private void addOwnSlots() {
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

		addSlotToContainer(new SlotItemHandler(itemHandler, 0, 9, 8));
		addSlotToContainer(new SlotItemHandler(itemHandler, 1, 9, 34));
		addSlotToContainer(new SlotItemHandler(itemHandler, 2, 9, 61));
		addSlotToContainer(new SlotItemHandler(itemHandler, 3, 139, 61));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 4) {
				if (!this.mergeItemStack(itemstack1, 4, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 4, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.canInteractWith(playerIn);
	}

	@Override
	public void detectAndSendChanges() {

		for (int i = 0; i < this.inventorySlots.size(); ++i) {
			ItemStack itemstack = ((Slot) this.inventorySlots.get(i)).getStack();
			ItemStack itemstack1 = this.inventoryItemStacks.get(i);
			if (!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
				boolean clientStackChanged = !ItemStack.areItemStacksEqualUsingNBTShareTag(itemstack1, itemstack);
				itemstack1 = itemstack.isEmpty() ? ItemStack.EMPTY : itemstack.copy();
				this.inventoryItemStacks.set(i, itemstack1);

				if (clientStackChanged)
					for (int j = 0; j < this.listeners.size(); ++j) {
						((IContainerListener) this.listeners.get(j)).sendSlotContents(this, i, itemstack1);
					}
			}
		}
	}

}
