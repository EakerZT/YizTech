package xyz.eaker.yiztech.api.recipe;

import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class YTRecipeType {
    private final String name;

    private final List<Supplier<ItemStack>> supportList = new ArrayList<>();

    public YTRecipeType(String name) {
        this.name = name;
    }

    public void addSupport(Supplier<ItemStack> supplier) {
        supportList.add(supplier);
    }

    public String getName() {
        return name;
    }
}
