package xyz.eaker.yiztech.datagen.base;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import xyz.eaker.yiztech.YizTech;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class BaseDataProvider implements DataProvider {
    protected final DataGenerator dataGenerator;
    private final Map<ResourceLocation, JsonObject> dataMap = new HashMap<>();

    public BaseDataProvider(DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        this.dataMap.clear();
        onRegisterData();
        CompletableFuture<?>[] futures = new CompletableFuture<?>[this.dataMap.size()];
        int i = 0;
        for (var entry : this.dataMap.entrySet()) {
            var s = this.dataGenerator.getPackOutput().getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(YizTech.MOD_ID).resolve(this.getTypePath()).resolve(entry.getKey().getPath() + ".json");
            futures[i++] = DataProvider.saveStable(pOutput, entry.getValue(), s);
        }
        return CompletableFuture.allOf(futures);
    }

    public abstract void onRegisterData();

    protected final void saveData(ResourceLocation id, JsonObject jsonObject) {
        this.dataMap.put(id, jsonObject);
    }

    public abstract String getTypePath();

    private static void saveJSON(CachedOutput directoryCache, JsonObject recipeJson, Path recipePath) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha1(), bytearrayoutputstream);
        Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
        JsonWriter jsonwriter = new JsonWriter(writer);
        jsonwriter.setSerializeNulls(false);
        jsonwriter.setIndent("  ");
        GsonHelper.writeValue(jsonwriter, recipeJson, KEY_COMPARATOR);
        jsonwriter.close();
        directoryCache.writeIfNeeded(recipePath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
    }


}
