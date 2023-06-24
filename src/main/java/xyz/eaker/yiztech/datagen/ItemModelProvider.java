package xyz.eaker.yiztech.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.datagen.base.BaseDataProvider;

public class ItemModelProvider extends BaseDataProvider {
    public ItemModelProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void onRegisterData() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("aaa", "bbb");
        this.saveData(new ResourceLocation(YizTech.MOD_ID, "server_ingot"), jsonobject);
    }

    @Override
    public String getTypePath() {
        return "model/item";
    }

    @Override
    public @NotNull String getName() {
        return "item_provider";
    }
}
