package harystolho.uberminer.tile;

import harystolho.uberminer.inventory.ContainerUberCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.NonNullList;

public class TileEntityUberCrafter extends TileEntityLockableLoot {

	public static final int SIZE = 4;
	public NonNullList<ItemStack> items;

	public TileEntityUberCrafter() {
		super();
		this.items = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.items = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound) && compound.hasKey("Items", 4)) {
			ItemStackHelper.loadAllItems(compound, this.items);
		}

		if (compound.hasKey("CustomName", 8)) {
			this.customName = compound.getString("CustomName");
		}

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items, false);
		}
		return compound;
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		// If we are too far away from this tile entity you cannot use it
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}

	@Override
	public int getSizeInventory() {
		return SIZE;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public String getName() {
		return "ubercrafter";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		this.fillWithLoot(playerIn);
		return new ContainerUberCrafter(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "";
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
    public ItemStack getStackInSlot(int index)
    {
        this.fillWithLoot((EntityPlayer) null);

        return this.getItems().get(index);
    }
	
	public void setContents(NonNullList<ItemStack> contents) {
		this.items = NonNullList.<ItemStack>withSize(SIZE, ItemStack.EMPTY);

		for (int i = 0; i < contents.size(); i++) {
			if (i < this.items.size()) {
				this.getItems().set(i, contents.get(i));
			}
		}
	}

}
