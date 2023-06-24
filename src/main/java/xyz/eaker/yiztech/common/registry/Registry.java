package xyz.eaker.yiztech.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.eaker.yiztech.YizTech;

import java.util.ArrayList;
import java.util.List;

public class Registry {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, YizTech.MOD_ID);
    private static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, YizTech.MOD_ID);
    private static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, YizTech.MOD_ID);

    private static final List<RegistryObject<Item>> ITEM_LIST = new ArrayList<>();
    private static final List<RegistryObject<Block>> BLOCK_LIST = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> RESOURCE_TAB = CREATIVE_MODE_TABS.register("resource_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(Items.IRON_INGOT::getDefaultInstance)
            .displayItems((parameters, output) -> {
                output.accept(Items.IRON_INGOT);
            }).build());

    public static void registry(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
        BLOCK_REGISTER.register(modEventBus);
        ITEM_REGISTER.register(modEventBus);
    }

}
