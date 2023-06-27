package xyz.eaker.yiztech.api.machine;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import xyz.eaker.yiztech.api.register.IRegisterObject;
import xyz.eaker.yiztech.common.block.machine.BaseMachineBlock;
import xyz.eaker.yiztech.common.entity.machine.BaseMachineEntity;
import xyz.eaker.yiztech.common.registry.YTRegistry;

public class Machine implements IRegisterObject {
    private RegistryObject<BlockEntityType<?>> blockEntitySupplier;
    private final String name;

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
        blockEntitySupplier = YTRegistry.blockEntity(name, BaseMachineEntity::new, block);
    }

    @Override
    public void onClientRegister() {

    }
}
