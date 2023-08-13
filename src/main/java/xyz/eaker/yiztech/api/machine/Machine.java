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
    public final int itemInputSize;
    public final int itemOutputSize;
    public final int fluidInputSize;
    public final int fluidOutputSize;
    private RegistryObject<MenuType<?>> menuType;

    public String getName() {
        return this.name;
    }

    public Machine(String name, int itemInputSize, int itemOutputSize, int fluidInputSize, int fluidOutputSize) {
        this.name = name;
        this.itemInputSize = itemInputSize;
        this.itemOutputSize = itemOutputSize;
        this.fluidInputSize = fluidInputSize;
        this.fluidOutputSize = fluidOutputSize;
    }

    public RegistryObject<BlockEntityType<?>> getBlockEntitySupplier() {
        return this.blockEntitySupplier;
    }

    @Override
    public void onCommonRegister() {
        var block = YTRegistry.block(this.name, () -> new BaseMachineBlock(this));
        YTRegistry.item(this.name, () -> new BlockItem(block.get(), new Item.Properties()));
        this.blockEntitySupplier = YTRegistry.blockEntity(this.name, (type, pos, state) -> new BaseMachineEntity(this, pos, state), block);
        this.menuType = YTRegistry.menu(this.name, (windowId, inv, data) -> new BaseMachineMenu(windowId, inv, data, this));
    }

    public RegistryObject<MenuType<?>> getMenuType() {
        return this.menuType;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onClientRegister() {
        YTRegistry.screen(this.menuType, BaseScreen::new);
    }
}
