package xyz.eaker.yiztech.client.registry;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.client.model.MachineModel;
import xyz.eaker.yiztech.common.registry.YTRegistry;
import xyz.eaker.yiztech.common.registry.YTWidget;

@Mod.EventBusSubscriber(modid = YizTech.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Registry {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(YTRegistry::onRegisterClient);
        event.enqueueWork(YTWidget::onRegisterWidget);
    }

    @SubscribeEvent
    public static void onRegisterItemColor(RegisterColorHandlersEvent.Item event) {
        YTRegistry.onRegisterItemColor(event);
    }

    @SubscribeEvent
    public static void onRegisterBlockColor(RegisterColorHandlersEvent.Block event) {
        YTRegistry.onRegisterBlockColor(event);
    }

    @SubscribeEvent
    public static void onRegisterRegisterGeometryLoader(ModelEvent.RegisterGeometryLoaders event) {
        event.register(MachineModel.LOADER_NAME, MachineModel.LOADER);
    }
}
