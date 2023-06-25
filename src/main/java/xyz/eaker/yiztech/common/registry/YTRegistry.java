package xyz.eaker.yiztech.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.api.register.IItemColorProvider;
import xyz.eaker.yiztech.api.register.IRegisterObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class YTRegistry {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, YizTech.MOD_ID);
    private static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, YizTech.MOD_ID);
    private static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, YizTech.MOD_ID);

    private static final List<IRegisterObject> REGISTER_OBJECTS = new ArrayList<>();

    private static final Map<String, RegistryObject<Item>> ITEM_MAP = new HashMap<>();
    private static final List<RegistryObject<Block>> BLOCK_LIST = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> RESOURCE_TAB = CREATIVE_MODE_TABS.register("resource_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(Items.IRON_INGOT::getDefaultInstance)
            .displayItems((parameters, output) -> {
                for (RegistryObject<Item> o : ITEM_MAP.values()) {
                    output.accept(o.get());
                }
//                output.accept(Items.IRON_INGOT);
            }).build());

    public static void registry(IEventBus modEventBus) {
        ITEM_REGISTER.register(modEventBus);
        BLOCK_REGISTER.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        MaterialLoader.register();
        for (IRegisterObject o : REGISTER_OBJECTS) {
            o.onServerRegister();
        }
    }

    public static RegistryObject<Item> registerItem(String name, final Supplier<Item> sup) {
        RegistryObject<Item> item = ITEM_REGISTER.register(name, sup);
        ITEM_MAP.put(name, item);
        return item;
    }

    public static void registerObject(IRegisterObject o) {
        REGISTER_OBJECTS.add(o);
    }

    public static void onRegisterItemColor(RegisterColorHandlersEvent.Item event) {
        for (RegistryObject<Item> value : ITEM_MAP.values()) {
            var item = value.get();
            if (item instanceof IItemColorProvider iItemColorProvider) {
                event.register(iItemColorProvider, item);
            }
        }
    }

    public static void onRegisterBlockColor(RegisterColorHandlersEvent.Block event) {

    }

}
