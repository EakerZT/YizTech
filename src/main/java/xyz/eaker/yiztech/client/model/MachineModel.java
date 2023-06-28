package xyz.eaker.yiztech.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.eaker.yiztech.common.util.ModelHelper;

import java.util.*;
import java.util.function.Function;

public class MachineModel implements IDynamicBakedModel {
    public final static String LOADER_NAME = "machine";
    public final static Loader LOADER = new Loader();
    public final static HashMap<ResourceLocation, TextureAtlasSprite> TEXTURE_MAP = new HashMap<>();
    private final ResourceLocation hull;
    private static final EnumMap<Direction, BakedQuad> hullCache = new EnumMap<>(Direction.class);

    public MachineModel(ResourceLocation hull) {
        this.hull = hull;
    }

    public static TextureAtlasSprite getTexture(ResourceLocation id) {
        if (TEXTURE_MAP.containsKey(id)) {
            return TEXTURE_MAP.get(id);
        }
        var tex = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(id);
        TEXTURE_MAP.put(id, tex);
        return tex;
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData, @Nullable RenderType renderType) {
        if (side == null) {
            return Collections.emptyList();
        }
        if (!hullCache.containsKey(side)) {
            hullCache.put(side, ModelHelper.createQuad(ModelHelper.FACE_QUADS.get(side), getTexture(hull)));
        }
        List<BakedQuad> quads = new ArrayList<>();
        quads.add(hullCache.get(side));
        return quads;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public boolean usesBlockLight() {
        return true;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return getTexture(hull);
    }

    @Override
    public ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }

    public static class Geometry implements IUnbakedGeometry<Geometry> {
        private final ResourceLocation hull;

        public Geometry(ResourceLocation hull) {
            this.hull = hull;
        }

        @Override
        public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {
            return new MachineModel(hull);
        }
    }

    public static class Loader implements IGeometryLoader<Geometry> {

        @Override
        public Geometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
            var hull = new ResourceLocation(jsonObject.get("hull").getAsString());
            return new Geometry(hull);
        }
    }
}
