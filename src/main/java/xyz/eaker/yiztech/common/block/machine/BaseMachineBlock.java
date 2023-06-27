package xyz.eaker.yiztech.common.block.machine;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import org.jetbrains.annotations.Nullable;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.api.machine.Machine;
import xyz.eaker.yiztech.api.register.IRegisterBlockItemModel;
import xyz.eaker.yiztech.api.register.IRegisterBlockState;

public class BaseMachineBlock extends Block implements IRegisterBlockState, IRegisterBlockItemModel, EntityBlock {
    private final Machine machine;

    public BaseMachineBlock(Machine machine) {
        super(BlockBehaviour.Properties.of());
        this.machine = machine;
    }

    @Override
    public void onRegisterBlockItemModel(ItemModelProvider provider) {
        provider.getBuilder(machine.getName())
                .parent(new ModelFile.UncheckedModelFile(YizTech.loc("block/" + machine.getName())));
    }

    @Override
    public void onRegisterBlockState(BlockStateProvider provider) {
        provider.simpleBlock(this, provider.models().cubeAll(machine.getName(), YizTech.loc("block/machine/hull/bronze")));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return machine.getBlockEntitySupplier().get().create(pPos, pState);
    }
}
