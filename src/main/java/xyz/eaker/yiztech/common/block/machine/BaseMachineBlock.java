package xyz.eaker.yiztech.common.block.machine;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.*;
import org.jetbrains.annotations.Nullable;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.api.machine.Machine;
import xyz.eaker.yiztech.api.register.IRegisterBlockItemModel;
import xyz.eaker.yiztech.api.register.IRegisterBlockState;
import xyz.eaker.yiztech.client.model.MachineModel;

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
        provider.simpleBlock(this, provider.models().getBuilder(machine.getName())
                .customLoader((b, f) -> new CustomLoaderBuilder<BlockModelBuilder>(YizTech.loc(MachineModel.LOADER_NAME), b, f) {
                    @Override
                    public JsonObject toJson(JsonObject json) {
                        json.addProperty("hull", YizTech.loc("block/machine/hull/bronze").toString());
                        return super.toJson(json);
                    }
                }).end());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return machine.getBlockEntitySupplier().get().create(pPos, pState);
    }
}
