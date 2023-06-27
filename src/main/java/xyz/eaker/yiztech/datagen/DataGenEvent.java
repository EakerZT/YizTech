package xyz.eaker.yiztech.datagen;

import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.common.registry.YTRegistry;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvent {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        var gen = event.getGenerator();
        var extHelper = event.getExistingFileHelper();
        gen.addProvider(true, new ItemModelProvider(gen.getPackOutput(), YizTech.MOD_ID, extHelper) {
            @Override
            protected void registerModels() {
                YTRegistry.onRegisterItemModel(this);
            }
        });
    }
}
