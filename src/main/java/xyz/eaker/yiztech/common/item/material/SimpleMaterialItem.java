package xyz.eaker.yiztech.common.item.material;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import org.jetbrains.annotations.NotNull;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.api.material.Material;
import xyz.eaker.yiztech.api.material.MaterialPart;
import xyz.eaker.yiztech.api.register.IItemColorProvider;
import xyz.eaker.yiztech.api.register.IRegisterItemModel;
import xyz.eaker.yiztech.common.util.ColorHelper;

public class SimpleMaterialItem extends BaseMaterialItem implements IItemColorProvider, IRegisterItemModel {
    private final int[] color = new int[5];

    public SimpleMaterialItem(Material material, MaterialPart materialPart) {
        super(material, materialPart);
        color[0] = ColorHelper.HEXtoDEC(ColorHelper.INTHueShift(material.getColor(), 3, true));
        color[1] = ColorHelper.HEXtoDEC(ColorHelper.INTHueShift(material.getColor(), 1, true));
        color[2] = material.getColor();
        color[3] = ColorHelper.HEXtoDEC(ColorHelper.INTHueShift(material.getColor(), 1, false));
        color[4] = ColorHelper.HEXtoDEC(ColorHelper.INTHueShift(material.getColor(), 2, false));
    }

    @Override
    public int getColor(@NotNull ItemStack pStack, int pTintIndex) {
        if (pTintIndex > -1 && pTintIndex < 5) {
            return color[pTintIndex];
        }
        return -1;
    }

    @Override
    public void onRegisterItemModel(ItemModelProvider provider) {
        var name = this.material.getName() + "_" + this.materialPart.getName();
        provider.getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft:item/generated")))
                .texture("layer0", new ResourceLocation(YizTech.MOD_ID, "item/material/" + materialPart.getName() + "/00"))
                .texture("layer1", new ResourceLocation(YizTech.MOD_ID, "item/material/" + materialPart.getName() + "/01"))
                .texture("layer2", new ResourceLocation(YizTech.MOD_ID, "item/material/" + materialPart.getName() + "/02"))
                .texture("layer3", new ResourceLocation(YizTech.MOD_ID, "item/material/" + materialPart.getName() + "/03"))
                .texture("layer4", new ResourceLocation(YizTech.MOD_ID, "item/material/" + materialPart.getName() + "/04"));
    }
}
