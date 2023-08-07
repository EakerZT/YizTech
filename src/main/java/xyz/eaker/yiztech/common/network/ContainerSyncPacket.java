package xyz.eaker.yiztech.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import xyz.eaker.yiztech.api.menu.BaseMenu;
import xyz.eaker.yiztech.api.menu.ISyncComponent;
import xyz.eaker.yiztech.api.network.BaseNetworkPacket;

import java.util.List;
import java.util.function.Supplier;

// Server -> Client
public class ContainerSyncPacket implements BaseNetworkPacket<ContainerSyncPacket> {
    private List<ISyncComponent<?>> syncData;
    private ByteBuf buffer;

    public ContainerSyncPacket() {

    }

    public ContainerSyncPacket(ByteBuf buffer) {
        this.buffer = buffer;
    }

    public ContainerSyncPacket(List<ISyncComponent<?>> syncData) {
        this.syncData = syncData;
    }

    public FriendlyByteBuf getBuffer() {
        return new FriendlyByteBuf(this.buffer);
    }

    @Override
    public void handler(ContainerSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                var c = Minecraft.getInstance().player.containerMenu;
                if (c instanceof BaseMenu containerMenu) {
                    containerMenu.onReceiveSyncPacket(msg);
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @Override
    public ContainerSyncPacket decode(FriendlyByteBuf buf) {
        return new ContainerSyncPacket(buf.copy());
    }

    @Override
    public void encode(ContainerSyncPacket pack, FriendlyByteBuf buf) {
        buf.writeVarInt(pack.syncData.size());
        for (var data : pack.syncData) {
            buf.writeVarInt(data.self().getIndex());
            data.write(buf);
        }
    }
}
