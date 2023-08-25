package xyz.eaker.yiztech.api.menu;

import net.minecraft.network.FriendlyByteBuf;

public abstract class BaseSyncComponent extends BaseComponent {
    public BaseSyncComponent(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public abstract void read(FriendlyByteBuf packet);

    public abstract void write(FriendlyByteBuf packet);

    public abstract boolean isChanged();
}
