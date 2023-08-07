package xyz.eaker.yiztech.api.menu;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.eaker.yiztech.common.network.ContainerSyncPacket;
import xyz.eaker.yiztech.common.registry.YTNetwork;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMenu extends AbstractContainerMenu {
    protected final Inventory inventory;
    private final List<BaseComponent> components;

    public BaseMenu(@Nullable MenuType<?> pMenuType, Inventory inventory, int pContainerId) {
        super(pMenuType, pContainerId);
        this.inventory = inventory;
        addPlayerInventorySlots();
        this.components = new ArrayList<>();
    }

    protected void addComponent(BaseComponent component) {
        component.index = this.components.size();
        this.components.add(component);
    }

    public List<BaseComponent> getComponents() {
        return this.components;
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        if (!(this.inventory.player instanceof ServerPlayer)) {
            return;
        }
        List<ISyncComponent<?>> syncList = new ArrayList<>();
        for (BaseComponent component : this.components) {
            if (component instanceof ISyncComponent<?> syncComponent) {
                syncList.add(syncComponent);
            }
        }
        if (syncList.isEmpty()) {
            return;
        }
        YTNetwork.sendToClient(new ContainerSyncPacket(syncList), (ServerPlayer) this.inventory.player);
    }

    public void onReceiveSyncPacket(ContainerSyncPacket packet) {

    }

    protected int addSlotRange(IItemHandler handler, int index, int x, int y, int dx) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    protected void addSlotBox(IItemHandler handler, int index, int x, int y, int dx, int dy) {
        for (int j = 0; j < 3; j++) {
            index = addSlotRange(handler, index, x, y, dx);
            y += dy;
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    private void addPlayerInventorySlots() {
        int leftPos = 8;
        int topPos = 84;
        var inventoryItemHandler = new InvWrapper(this.inventory);
        addSlotBox(inventoryItemHandler, 9, leftPos, topPos, 18, 18);
        topPos += 58;
        addSlotRange(inventoryItemHandler, 0, leftPos, topPos, 18);
    }
}
