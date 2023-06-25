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
        jsonobject.addProperty("parent", "minecraft:item/generated");
        var tex = new JsonObject();
        tex.addProperty("layer0","yiztech:material/ingot/00");
        tex.addProperty("layer1","yiztech:material/ingot/01");
        tex.addProperty("layer2","yiztech:material/ingot/02");
        tex.addProperty("layer3","yiztech:material/ingot/03");
        tex.addProperty("layer4","yiztech:material/ingot/04");
        jsonobject.add("textures", tex);
        this.saveData(new ResourceLocation(YizTech.MOD_ID, "tin_ingot"), jsonobject);
    }

    @Override
    public String getTypePath() {
        return "models/item";
    }

    @Override
    public @NotNull String getName() {
        return "item_provider";
    }
}
