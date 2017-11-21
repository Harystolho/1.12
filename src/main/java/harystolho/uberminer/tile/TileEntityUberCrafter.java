package harystolho.uberminer.tile;

import harystolho.uberminer.inventory.ContainerUberCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public class TileEntityUberCrafter extends TileEntityLockable implements ITickable {

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
		ItemStackHelper.loadAllItems(compound, this.items);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.items);
		return compound;
	}

	public static void registerFixesCrafter(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityUberCrafter.class, new String[] { "Items" }));
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
		return new ContainerUberCrafter(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "";
	}

	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	public void update() {
		if (!this.world.isRemote) {
			ItemStack tool = getStackInSlot(3);
			if (!tool.isEmpty()) {
				NBTTagCompound nbt = tool.getTagCompound();
				if (nbt == null) {
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setInteger("ToolDurability", 100);
					nbttagcompound1.setInteger("ToolRadius", 1);
					nbttagcompound1.setFloat("ToolSpeed", 1);
					nbttagcompound1.setString("ToolModifiers", "---");

					tool.setTagCompound(nbttagcompound1);
				}

				nbt = tool.getTagCompound();

				int maxSpeed = 2;
				if (nbt.getString("ToolModifiers") != null) {
					if (nbt.getString("ToolModifiers").contains("ExtraSpeed"))
						if (maxSpeed == 2)
							maxSpeed = 4;
				}

				if (!getStackInSlot(0).isEmpty()) {
					if (nbt.getFloat("ToolSpeed") < maxSpeed) {
						ItemStack item1 = getStackInSlot(0);
						item1.setCount(item1.getCount() - 1);
						nbt.setFloat("ToolSpeed", nbt.getFloat("ToolSpeed") + 0.0055F);
					}
				}
				if (!getStackInSlot(2).isEmpty()) {
					ItemStack item3 = getStackInSlot(2);
					if (nbt.getString("ToolModifiers").equals("---")) {
						if (item3.getUnlocalizedName().equals("item.speed_modifier")) {
							item3.setCount(item3.getCount() - 1);
							nbt.setString("ToolModifiers", "ExtraSpeed");
						} else if (item3.getUnlocalizedName().equals("item.range_modifier")) {
							item3.setCount(item3.getCount() - 1);
							nbt.setString("ToolModifiers", "ExtraRange");
							nbt.setInteger("ToolRadius", nbt.getInteger("ToolRadius") + 1);
						}

					} else {
						if (item3.getUnlocalizedName().equals("item.speed_modifier")) {
							if (!nbt.getString("ToolModifiers").contains("ExtraSpeed")) {
								item3.setCount(item3.getCount() - 1);
								nbt.setString("ToolModifiers", nbt.getString("ToolModifiers") + " | ExtraSpeed");
							}

						} else if (item3.getUnlocalizedName().equals("item.range_modifier")) {
							if (nbt.getInteger("ToolRadius") < 5) {
								item3.setCount(item3.getCount() - 1);
								nbt.setInteger("ToolRadius", nbt.getInteger("ToolRadius") + 1);
								nbt.setString("ToolModifiers", nbt.getString("ToolModifiers") + " | ExtraRange");
							}
						}
					}
				}
			}
		}
	}

	@Override
	public ItemStack getStackInSlot(int index) {

		return this.getItems().get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.items, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.items, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack)
				&& ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.items.set(index, stack);

		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 3) {
			this.markDirty();
		}
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 3) {
			if (stack.getUnlocalizedName().equals("item.bow_uber")) {
				return true;
			} else {
				return false;
			}
		}
		if (index == 0) {
			if (stack.getUnlocalizedName().equals("item.redstone")) {
				return true;
			} else {
				return false;
			}
		}
		if (index == 2) {
			if (stack.getUnlocalizedName().equals("item.range_modifier")
					|| stack.getUnlocalizedName().equals("item.speed_modifier")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

}
