package xyz.eaker.yiztech.api.register;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import xyz.eaker.yiztech.YizTech;

public interface IRegisterBlockItemModel {
    void onRegisterBlockItemModel(ItemModelProvider provider);
}
