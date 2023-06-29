package xyz.eaker.yiztech.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class ResourceHelper {
    public static boolean isTextureExit(ResourceLocation resourceLocation){
        var id = new ResourceLocation(resourceLocation.getNamespace(), "textures/%s.png".formatted(resourceLocation.getPath()));
        return Minecraft.getInstance().getResourceManager().getResource(id).isPresent();
    }
}
