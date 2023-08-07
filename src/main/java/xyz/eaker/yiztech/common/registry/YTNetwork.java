package xyz.eaker.yiztech.common.registry;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.api.network.BaseNetworkPacket;
import xyz.eaker.yiztech.common.network.ContainerSyncPacket;

public class YTNetwork {
    private static final String VERSION = "1.0";
    private static SimpleChannel CHANNEL;
    private static int currMessageId;

    public static void register() {
        CHANNEL = NetworkRegistry.newSimpleChannel(
                YizTech.loc("et_networking"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION));
        registerMessage(new ContainerSyncPacket(), ContainerSyncPacket.class);
    }

    private static  <T extends BaseNetworkPacket<T>> void registerMessage(T packet, Class<T> clazz) {
        CHANNEL.registerMessage(currMessageId++, clazz, packet::encode, packet::decode, packet::handler);
    }

    public static  <T extends BaseNetworkPacket<T>> void sendToServer(T t) {
        CHANNEL.sendToServer(t);
    }

    public static  <T extends BaseNetworkPacket<T>> void sendToClient(T t, ServerPlayer player) {
        if (!(player instanceof FakePlayer))
            CHANNEL.sendTo(t, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}
