package xyz.eaker.yiztech.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.SlotItemHandler;
import xyz.eaker.yiztech.api.capacity.IMachineStatus;
import xyz.eaker.yiztech.api.definition.ProgressBar;
import xyz.eaker.yiztech.api.machine.Machine;
import xyz.eaker.yiztech.api.menu.BaseMenu;
import xyz.eaker.yiztech.api.menu.ProgressBarComponent;
import xyz.eaker.yiztech.common.entity.machine.BaseMachineEntity;

public class BaseMachineMenu extends BaseMenu {
    private final BaseMachineEntity entity;

    public BaseMachineMenu(int pContainerId, Inventory inventory, FriendlyByteBuf data, Machine machine) {
        this(pContainerId, inventory, machine, data.readBlockPos());

    }

    public BaseMachineMenu(int pContainerId, Inventory inventory, Machine machine, BlockPos pos) {
        super(machine.getMenuType().get(), inventory, pContainerId);
        var blockEntity = inventory.player.level().getBlockEntity(pos);
        if (blockEntity instanceof BaseMachineEntity baseMachineEntity) {
            this.entity = baseMachineEntity;
        } else {
            throw new RuntimeException("Unsupported BlockEntity:Not AbstractMachineBlockEntity");
        }
        var itemContainer = this.entity.getMenuItemHandler();
        for (int i = 0; i < this.entity.itemInputSize; i++) {
            addSlot(new SlotItemHandler(itemContainer, i, i * 16, i * 16));
        }
        addComponent(new ProgressBarComponent(77, 23, ProgressBar.ARROW, new IMachineStatus() {
            @Override
            public int machineState() {
                return 0;
            }

            @Override
            public int processProgress() {
                return 0;
            }

            @Override
            public int processDuration() {
                return 0;
            }
        }));
    }

    public BaseMachineEntity getEntity() {
        return this.entity;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
