package xyz.eaker.yiztech.api.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class CapabilityUtils {
    public static ItemStack getAndSplit(List<ItemStack> stacks, int index, int amount) {
        return index >= 0 && index < stacks.size() && !stacks.get(index).isEmpty() && amount > 0 ? stacks.get(index).split(amount) : ItemStack.EMPTY;
    }

    public static ItemStack getAndRemove(List<ItemStack> stacks, int index) {
        return index >= 0 && index < stacks.size() ? stacks.set(index, ItemStack.EMPTY) : ItemStack.EMPTY;
    }

    public static boolean transferItems(IItemHandler from, IItemHandler to) {
        return transferItems(from, to, false);
    }

    public static boolean transferItems(IItemHandler from, IItemHandler to, boolean once) {
        boolean successful = false;
        for (int i = 0; i < from.getSlots(); i++) {
            ItemStack toInsert = from.extractItem(i, from.getStackInSlot(i).getCount(), true);
            if (toInsert.isEmpty()) {
                continue;
            }
            ItemStack inserted = ItemHandlerHelper.insertItem(to, toInsert, true);
            if (inserted.getCount() < toInsert.getCount()) {
                int actual = toInsert.getCount() - inserted.getCount();
                toInsert.setCount(toInsert.getCount() - inserted.getCount());
                ItemHandlerHelper.insertItem(to, toInsert, false);
                from.extractItem(i, actual, false);
                if (!successful) {
                    successful = true;
                }
                if (once) {
                    break;
                }
            }
        }
        return successful;
    }
}
