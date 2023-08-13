package xyz.eaker.yiztech.api.capacity;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class SimpleIoItemHandlerContainer implements IItemHandlerModifiable {
    private final NonNullList<ItemStack> itemList;
    private final int itemInputSize, itemOutputSize;
    private final Runnable setChanged;

    public SimpleIoItemHandlerContainer(NonNullList<ItemStack> itemList, int itemInputSize, int itemOutputSize, Runnable onChange) {
        this.itemList = itemList;
        this.itemInputSize = itemInputSize;
        this.itemOutputSize = itemOutputSize;
        this.setChanged = onChange;
    }

    @Override
    public int getSlots() {
        return this.itemList.size();
    }

    @NotNull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.itemList.get(slot);
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (slot >= this.itemInputSize) {
            return stack;
        }
        if (stack.isEmpty()) {
            return stack;
        }
        if (!isItemValid(slot, stack)) {
            return stack;
        }
        ItemStack existing = this.itemList.get(slot);
        int limit = Math.min(getSlotLimit(slot), stack.getMaxStackSize());
        if (!existing.isEmpty()) {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing)) {
                return stack;
            }
            limit -= existing.getCount();
        }
        if (limit <= 0) {
            return stack;
        }
        boolean reachedLimit = stack.getCount() > limit;
        if (!simulate) {
            if (existing.isEmpty()) {
                this.itemList.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            } else {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            this.setChanged.run();
        }
        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
    }

    @NotNull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0) {
            return ItemStack.EMPTY;
        }
        ItemStack existing = this.itemList.get(slot);
        if (existing.isEmpty()) {
            return ItemStack.EMPTY;
        }
        int toExtract = Math.min(amount, existing.getMaxStackSize());
        if (existing.getCount() <= toExtract) {
            if (!simulate) {
                this.itemList.set(slot, ItemStack.EMPTY);
                this.setChanged.run();
                return existing;
            } else {
                return existing.copy();
            }
        } else {
            if (!simulate) {
                this.itemList.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                this.setChanged.run();
            }
            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    @Override
    public int getSlotLimit(int slot) {
        return this.itemList.get(slot).getMaxStackSize();
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return true;
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        this.itemList.set(slot, stack);
        this.setChanged.run();
    }
}
