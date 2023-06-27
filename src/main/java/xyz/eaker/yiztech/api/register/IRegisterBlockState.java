package xyz.eaker.yiztech.api.register;

import net.minecraftforge.client.model.generators.BlockStateProvider;

public interface IRegisterBlockState {
    void onRegisterBlockState(BlockStateProvider provider);
}
