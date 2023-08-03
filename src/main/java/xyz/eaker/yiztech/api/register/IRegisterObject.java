package xyz.eaker.yiztech.api.register;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IRegisterObject {
    void onCommonRegister();
    @OnlyIn(Dist.CLIENT)
    void onClientRegister();
}
