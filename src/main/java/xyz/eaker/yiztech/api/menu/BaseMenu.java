package xyz.eaker.yiztech.api.menu;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;
import xyz.eaker.yiztech.common.network.ContainerSyncPacket;
import xyz.eaker.yiztech.common.registry.YTNetwork;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMenu extends AbstractContainerMenu {
    private final Inventory inventory;
    private final List<BaseComponent> components;

    protected BaseMenu(@Nullable MenuType<?> pMenuType, Inventory inventory, int pContainerId) {
        super(pMenuType, pContainerId);
        this.inventory = inventory;
        this.components = new ArrayList<>();
    }

    protected void addComponent(BaseComponent component) {
        component.index = this.components.size();
        this.components.add(component);
    }

    public List<BaseComponent> getComponents() {
        return components;
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

}
