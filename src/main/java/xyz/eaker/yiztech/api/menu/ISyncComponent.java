package xyz.eaker.yiztech.api.menu;

import net.minecraft.network.FriendlyByteBuf;

public interface ISyncComponent<T> {
    T getRealValue();

    void setCacheValue(T t);

    void read(FriendlyByteBuf packet);

    void write(FriendlyByteBuf packet);

    boolean isEqualCache(T t1);

    default BaseComponent self() {
        return (BaseComponent) this;
    }

    default boolean isChanged() {
        var v = getRealValue();
        if (!isEqualCache(v)) {
            setCacheValue(v);
            return true;
        }
        return false;
    }
}
