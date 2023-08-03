package xyz.eaker.yiztech.api.machine;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;
import xyz.eaker.yiztech.api.register.IRegisterObject;
import xyz.eaker.yiztech.client.screen.BaseScreen;
import xyz.eaker.yiztech.common.block.machine.BaseMachineBlock;
import xyz.eaker.yiztech.common.entity.machine.BaseMachineEntity;
import xyz.eaker.yiztech.common.menu.BaseMachineMenu;
import xyz.eaker.yiztech.common.registry.YTRegistry;

public class Machine implements IRegisterObject {
    private RegistryObject<BlockEntityType<?>> blockEntitySupplier;
    private final String name;
    private RegistryObject<MenuType<?>> menuType;

    public String getName() {
        return name;
    }

    public Machine(String name) {
        this.name = name;
    }

    public RegistryObject<BlockEntityType<?>> getBlockEntitySupplier() {
        return blockEntitySupplier;
    }

    @Override
    public void onCommonRegister() {
        var block = YTRegistry.block(name, () -> new BaseMachineBlock(this));
        YTRegistry.item(name, () -> new BlockItem(block.get(), new Item.Properties()));
        this.blockEntitySupplier = YTRegistry.blockEntity(name, (type, pos, state) -> new BaseMachineEntity(this, pos, state), block);
        this.menuType = YTRegistry.menu(name, (windowId, inv, data) -> new BaseMachineMenu(windowId, inv, data, this));
    }

    public RegistryObject<MenuType<?>> getMenuType() {
        return menuType;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onClientRegister() {
        YTRegistry.screen(this.menuType, BaseScreen::new);
    }
}
