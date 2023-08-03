package xyz.eaker.yiztech.common.registry;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.function.TriFunction;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.api.recipe.YTRecipeType;
import xyz.eaker.yiztech.api.register.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class YTRegistry {
    private static final List<IRegisterObject> REGISTER_OBJECTS = new ArrayList<>();
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, YizTech.MOD_ID);
    private static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, YizTech.MOD_ID);
    private static final Map<String, RegistryObject<Block>> BLOCK_MAP = new HashMap<>();
    private static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, YizTech.MOD_ID);
    private static final Map<String, RegistryObject<Item>> ITEM_MAP = new HashMap<>();
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, YizTech.MOD_ID);
    private static final Map<String, RegistryObject<BlockEntityType<?>>> BLOCK_ENTITY_MAP = new HashMap<>();
    private static final DeferredRegister<MenuType<?>> MENU_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, YizTech.MOD_ID);
    private static final Map<String, RegistryObject<MenuType<?>>> MENU_TYPE_MAP = new HashMap<>();


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
        BLOCK_ENTITY_REGISTER.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        MENU_TYPE_REGISTER.register(modEventBus);
        YTMaterial.register();
        YTMachine.register();
        for (IRegisterObject o : REGISTER_OBJECTS) {
            o.onCommonRegister();
        }
    }

    public static RegistryObject<Item> item(String name, final Supplier<Item> sup) {
        RegistryObject<Item> item = ITEM_REGISTER.register(name, sup);
        ITEM_MAP.put(name, item);
        return item;
    }

    public static RegistryObject<Block> block(String name, final Supplier<Block> sup) {
        RegistryObject<Block> block = BLOCK_REGISTER.register(name, sup);
        BLOCK_MAP.put(name, block);
        return block;
    }

    public static YTRecipeType recipeType(YTRecipeType recipeType) {
        return recipeType;
    }

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<?>> blockEntity(String name, TriFunction<BlockEntityType<T>, BlockPos, BlockState, T> entitySupplier, Supplier<Block> blockSupplier) {
        RegistryObject<BlockEntityType<?>> blockEntityType = BLOCK_ENTITY_REGISTER.register(name, () -> BlockEntityType.Builder.of((p, s) -> entitySupplier.apply((BlockEntityType<T>) BLOCK_ENTITY_MAP.get(name).get(), p, s), blockSupplier.get()).build(null));
        BLOCK_ENTITY_MAP.put(name, blockEntityType);
        return blockEntityType;
    }

    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<?>> menu(String name, IContainerFactory<T> sup) {
        RegistryObject<MenuType<?>> menuType = MENU_TYPE_REGISTER.register(name, () -> IForgeMenuType.create(sup));
        MENU_TYPE_MAP.put(name, menuType);
        return menuType;
    }

    @OnlyIn(Dist.CLIENT)
    public static <T extends AbstractContainerMenu, U extends Screen & MenuAccess<T>> void screen(RegistryObject<MenuType<?>> menuType, TriFunction<T, Inventory, Component, U> screenProvider) {
        MenuScreens.register(((RegistryObject<MenuType<T>>) (Object) menuType).get(), screenProvider::apply);
    }


    @OnlyIn(Dist.CLIENT)
    public static void onRegisterClient() {
        for (IRegisterObject o : REGISTER_OBJECTS) {
            o.onClientRegister();
        }
    }

    public static void registerObject(IRegisterObject o) {
        REGISTER_OBJECTS.add(o);
    }

    public static void onRegisterItemModel(ItemModelProvider provider) {
        for (RegistryObject<Item> value : ITEM_MAP.values()) {
            var item = value.get();
            if (item instanceof IRegisterItemModel iRegisterItemModel) {
                iRegisterItemModel.onRegisterItemModel(provider);
            }
        }
        for (var value : BLOCK_MAP.values()) {
            var v = value.get();
            if (v instanceof IRegisterBlockItemModel iRegisterBlockItemModel) {
                iRegisterBlockItemModel.onRegisterBlockItemModel(provider);
            }
        }
    }

    public static void onRegisterBlockState(BlockStateProvider provider) {
        for (var value : BLOCK_MAP.values()) {
            var v = value.get();
            if (v instanceof IRegisterBlockState iRegisterBlockState) {
                iRegisterBlockState.onRegisterBlockState(provider);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void onRegisterItemColor(RegisterColorHandlersEvent.Item event) {
        for (var value : ITEM_MAP.values()) {
            var v = value.get();
            if (v instanceof IItemColorProvider iItemColorProvider) {
                event.register(iItemColorProvider::getColor, v);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void onRegisterBlockColor(RegisterColorHandlersEvent.Block event) {
        for (var value : BLOCK_MAP.values()) {
            var v = value.get();
            if (v instanceof IBlockColorProvider iBlockColorProvider) {
                event.register(iBlockColorProvider::getColor, v);
            }
        }
    }

}
