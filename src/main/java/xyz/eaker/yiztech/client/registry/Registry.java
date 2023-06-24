//package xyz.eaker.yiztech.client.registry;
//
//import com.mojang.logging.LogUtils;
//import net.minecraft.client.Minecraft;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import org.slf4j.Logger;
//import xyz.eaker.yiztech.YizTech;
//
//@Mod.EventBusSubscriber(modid = YizTech.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//public class Registry {
//    private static final Logger LOGGER = LogUtils.getLogger();
//    @SubscribeEvent
//    public static void onClientSetup(FMLClientSetupEvent event) {
//        LOGGER.info("HELLO FROM CLIENT SETUP");
//        LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
//    }
//}
