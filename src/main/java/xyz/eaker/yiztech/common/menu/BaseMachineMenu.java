package xyz.eaker.yiztech.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import xyz.eaker.yiztech.api.machine.Machine;
import xyz.eaker.yiztech.common.entity.machine.BaseMachineEntity;

public class BaseMachineMenu extends AbstractContainerMenu {
    private final BaseMachineEntity entity;

    public BaseMachineMenu(int pContainerId, Inventory inventory, FriendlyByteBuf data, Machine machine) {
        this(pContainerId, inventory, machine, data.readBlockPos());
    }

    public BaseMachineMenu(int pContainerId, Inventory inventory, Machine machine, BlockPos pos) {
        super(machine.getMenuType().get(), pContainerId);
        this.entity = null;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    public BaseMachineEntity getEntity() {
        return entity;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
