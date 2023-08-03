package xyz.eaker.yiztech.common.entity.machine;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import xyz.eaker.yiztech.api.machine.Machine;
import xyz.eaker.yiztech.common.menu.BaseMachineMenu;

public class BaseMachineEntity extends BlockEntity implements MenuProvider {
    private Machine machine;

    public BaseMachineEntity(Machine machine, BlockPos pPos, BlockState pBlockState) {
        super(machine.getBlockEntitySupplier().get(), pPos, pBlockState);
        this.machine = machine;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new BaseMachineMenu(pContainerId, pPlayerInventory, this.machine, this.worldPosition);
    }

    @Override
    public Component getDisplayName() {
        return Component.empty();
    }
}
