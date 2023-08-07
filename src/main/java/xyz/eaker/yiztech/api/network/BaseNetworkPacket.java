package xyz.eaker.yiztech.api.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface BaseNetworkPacket<T> {
    void handler(T msg, Supplier<NetworkEvent.Context> ctx);

    T decode(FriendlyByteBuf buf);

    public void encode(T pack, FriendlyByteBuf buf);
}
