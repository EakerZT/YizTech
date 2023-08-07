package xyz.eaker.yiztech.common.block.machine;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.network.NetworkHooks;
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
        provider.getBuilder(this.machine.getName())
                .parent(new ModelFile.UncheckedModelFile(provider.modLoc("block/base_machine")))
                .texture("base", provider.modLoc("block/machine/hull/bronze"))
                .texture("sign", provider.modLoc("block/machine/sign/" + "alloy_smelter" + ("_active")));
    }

    @Override
    public void onRegisterBlockState(BlockStateProvider provider) {
        provider.simpleBlock(this, provider.models().getBuilder(this.machine.getName())
                .customLoader((b, f) -> new CustomLoaderBuilder<BlockModelBuilder>(YizTech.loc(MachineModel.LOADER_NAME), b, f) {
                    @Override
                    public JsonObject toJson(JsonObject json) {
                        json.addProperty("hull", YizTech.loc("block/machine/hull/bronze").toString());
                        json.addProperty("sign", YizTech.loc("block/machine/sign/alloy_smelter").toString());
                        json.addProperty("signActive", YizTech.loc("block/machine/sign/alloy_smelter_active").toString());
                        return super.toJson(json);
                    }
                }).end());
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide() || pHand != InteractionHand.MAIN_HAND) {
            return InteractionResult.SUCCESS;
        }
        BlockEntity entity = pLevel.getBlockEntity(pPos);
//        if (!(entity instanceof BaseMachineEntity)) {
//            return InteractionResult.PASS;
//        }
        if (entity instanceof MenuProvider menuProvider) {
            NetworkHooks.openScreen((ServerPlayer) pPlayer, menuProvider, pPos);
            return InteractionResult.CONSUME;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return this.machine.getBlockEntitySupplier().get().create(pPos, pState);
    }
}
