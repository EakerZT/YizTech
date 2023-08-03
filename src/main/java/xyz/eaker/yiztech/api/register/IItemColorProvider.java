package xyz.eaker.yiztech.api.register;

import net.minecraft.world.item.ItemStack;

public interface IItemColorProvider {
    int getColor(ItemStack pStack, int pTintIndex);
}
