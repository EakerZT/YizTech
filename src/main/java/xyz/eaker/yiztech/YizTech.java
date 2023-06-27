package xyz.eaker.yiztech;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.eaker.yiztech.common.registry.YTRegistry;

@Mod(YizTech.MOD_ID)
public class YizTech {
    public static final String MOD_ID = "yiztech";

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public YizTech() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        YTRegistry.registry(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
