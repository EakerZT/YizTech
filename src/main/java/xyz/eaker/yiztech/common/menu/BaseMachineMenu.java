package xyz.eaker.yiztech.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import xyz.eaker.yiztech.api.machine.Machine;
import xyz.eaker.yiztech.api.menu.BaseMenu;
import xyz.eaker.yiztech.common.entity.machine.BaseMachineEntity;

public class BaseMachineMenu extends BaseMenu {
    private final BaseMachineEntity entity;

    public BaseMachineMenu(int pContainerId, Inventory inventory, FriendlyByteBuf data, Machine machine) {
        this(pContainerId, inventory, machine, data.readBlockPos());
    }

    public BaseMachineMenu(int pContainerId, Inventory inventory, Machine machine, BlockPos pos) {
        super(machine.getMenuType().get(), inventory, pContainerId);
        this.entity = null;
    }

    public BaseMachineEntity getEntity() {
        return this.entity;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
