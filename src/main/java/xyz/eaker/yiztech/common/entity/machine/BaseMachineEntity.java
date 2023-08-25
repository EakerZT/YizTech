package xyz.eaker.yiztech.common.entity.machine;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;
import xyz.eaker.yiztech.api.capacity.IMachineStatus;
import xyz.eaker.yiztech.api.capacity.SimpleIoItemHandlerContainer;
import xyz.eaker.yiztech.api.machine.Machine;
import xyz.eaker.yiztech.common.menu.BaseMachineMenu;

public class BaseMachineEntity extends BlockEntity implements MenuProvider, IMachineStatus {
    protected final Machine machine;
    protected final NonNullList<ItemStack> itemList;
    protected final NonNullList<FluidStack> fluidList;
    public final int itemInputSize;
    public final int itemOutputSize;
    public final int fluidInputSize;
    public final int fluidOutputSize;
    private final LazyOptional<IItemHandler> menuItemHandlerLazyOptional;
    protected int processProgress = 0;
    protected int processDuration = 0;

    public BaseMachineEntity(Machine machine, BlockPos pPos, BlockState pBlockState) {
        super(machine.getBlockEntitySupplier().get(), pPos, pBlockState);
        this.machine = machine;
        this.itemInputSize = machine.itemInputSize;
        this.itemOutputSize = machine.itemOutputSize;
        this.fluidInputSize = machine.fluidInputSize;
        this.fluidOutputSize = machine.fluidOutputSize;
        this.itemList = NonNullList.withSize(this.itemInputSize + this.itemOutputSize, ItemStack.EMPTY);
        this.fluidList = NonNullList.withSize(this.fluidInputSize + this.fluidOutputSize, FluidStack.EMPTY);
        this.menuItemHandlerLazyOptional = LazyOptional.of(() -> new SimpleIoItemHandlerContainer(this.itemList, this.itemInputSize, this.itemOutputSize, this::setChanged));
    }

    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new BaseMachineMenu(pContainerId, pPlayerInventory, this.machine, this.worldPosition);
    }

    @Override
    public Component getDisplayName() {
        return Component.empty();
    }

    public IItemHandler getMenuItemHandler() {
        return this.menuItemHandlerLazyOptional.resolve().get();
    }

    @Override
    public int machineState() {
        if (this.processProgress == 0) {
            return 0;
        } else if (this.processProgress > 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int processDuration() {
        return this.processDuration;
    }

    @Override
    public int processProgress() {
        return this.processProgress;
    }
}
