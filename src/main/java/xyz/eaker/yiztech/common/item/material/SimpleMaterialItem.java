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

public class SimpleMaterialItem extends BaseMaterialItem implements IItemColorProvider, IRegisterItemModel {
    private final int color;

    public SimpleMaterialItem(Material material, MaterialPart materialPart) {
        super(material, materialPart);
        this.color = material.getColor();
    }

    @Override
    public int getColor(@NotNull ItemStack pStack, int pTintIndex) {
        if (pTintIndex == 0) {
            return this.color;
        }
        return -1;
    }

    @Override
    public void onRegisterItemModel(ItemModelProvider provider) {
        var name = this.material.getName() + "_" + this.materialPart.getName();
        provider.getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation("minecraft:item/generated")))
                .texture("layer0", new ResourceLocation(YizTech.MOD_ID, "item/material/" + this.materialPart.getName()))
                .texture("layer1", new ResourceLocation(YizTech.MOD_ID, "item/material/" + this.materialPart.getName() + "_overlay"));
    }
}
