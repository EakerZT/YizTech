package xyz.eaker.yiztech.api.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

public class FluidSlot extends BaseSyncComponent {
    private final Supplier<FluidStack> fluidStackSupplier;
    private FluidStack fluidStack;

    public FluidSlot(int x, int y, Supplier<FluidStack> supplier) {
        super(x, y, 16, 16);
        this.fluidStack = FluidStack.EMPTY;
        this.fluidStackSupplier = supplier;
    }

    @Override
    public void read(FriendlyByteBuf packet) {
        this.fluidStack = FluidStack.readFromPacket(packet);
    }

    @Override
    public void write(FriendlyByteBuf packet) {
        packet.writeFluidStack(this.fluidStack);
    }

    @Override
    public boolean isChanged() {
        var v = this.fluidStackSupplier.get();
        if (v.isFluidStackIdentical(this.fluidStack)) {
            return false;
        }
        this.fluidStack = v;
        return true;
    }
}
