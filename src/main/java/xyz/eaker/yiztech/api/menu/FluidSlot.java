package xyz.eaker.yiztech.api.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;

public class FluidSlot extends BaseComponent implements ISyncComponent<FluidStack> {
    private FluidStack fluidStack;

    public FluidSlot(int x, int y) {
        super(x, y, 16, 16);
    }

    @Override
    public FluidStack getRealValue() {
        return null;
    }

    @Override
    public void setCacheValue(FluidStack fluidStack) {

    }

    @Override
    public void read(FriendlyByteBuf packet) {

    }

    @Override
    public void write(FriendlyByteBuf packet) {

    }

    @Override
    public boolean isEqualCache(FluidStack t1) {
        return false;
    }
}
