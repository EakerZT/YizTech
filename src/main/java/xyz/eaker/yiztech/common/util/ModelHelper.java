package xyz.eaker.yiztech.common.util;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.util.FastColor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.pipeline.QuadBakingVertexConsumer;

import java.util.EnumMap;

// Copy from EnderIO-Rewriter
public class ModelHelper {


    public static final EnumMap<Direction, Vec3[]> FACE_QUADS = new EnumMap<>(Direction.class);

    static {
        for (Direction dir : Direction.values()) {
            FACE_QUADS.put(dir, createQuadVertex(dir, 0, 1, 1));
        }
    }

    public static Vec3[] createQuadVertex(Direction face, double leftEdge, double rightEdge, double elevation) {
        return switch (face) {
            case DOWN -> new Vec3[]{
                    new Vec3(leftEdge, 1 - elevation, leftEdge),
                    new Vec3(rightEdge, 1 - elevation, leftEdge),
                    new Vec3(rightEdge, 1 - elevation, rightEdge),
                    new Vec3(leftEdge, 1 - elevation, rightEdge)
            };
            case UP -> new Vec3[]{
                    new Vec3(leftEdge, elevation, leftEdge),
                    new Vec3(leftEdge, elevation, rightEdge),
                    new Vec3(rightEdge, elevation, rightEdge),
                    new Vec3(rightEdge, elevation, leftEdge)
            };
            case NORTH -> new Vec3[]{
                    new Vec3(rightEdge, rightEdge, 1 - elevation),
                    new Vec3(rightEdge, leftEdge, 1 - elevation),
                    new Vec3(leftEdge, leftEdge, 1 - elevation),
                    new Vec3(leftEdge, rightEdge, 1 - elevation)
            };
            case SOUTH -> new Vec3[]{
                    new Vec3(leftEdge, rightEdge, elevation),
                    new Vec3(leftEdge, leftEdge, elevation),
                    new Vec3(rightEdge, leftEdge, elevation),
                    new Vec3(rightEdge, rightEdge, elevation)
            };
            case WEST -> new Vec3[]{
                    new Vec3(1 - elevation, rightEdge, leftEdge),
                    new Vec3(1 - elevation, leftEdge, leftEdge),
                    new Vec3(1 - elevation, leftEdge, rightEdge),
                    new Vec3(1 - elevation, rightEdge, rightEdge)
            };
            case EAST -> new Vec3[]{
                    new Vec3(elevation, rightEdge, rightEdge),
                    new Vec3(elevation, leftEdge, rightEdge),
                    new Vec3(elevation, leftEdge, leftEdge),
                    new Vec3(elevation, rightEdge, leftEdge)
            };
        };
    }

    public static BakedQuad createQuad(Vec3[] vertex, TextureAtlasSprite sprite, int tintIndex) {
        return createQuad(vertex[0], vertex[1], vertex[2], vertex[3], sprite, 0xFFFFFF, 1.0f, tintIndex, true, true);
    }

    public static BakedQuad createQuad(Vec3[] vertex, TextureAtlasSprite sprite, int tintIndex, boolean hasAmbientOcclusion, boolean shade) {
        return createQuad(vertex[0], vertex[1], vertex[2], vertex[3], sprite, 0xFFFFFF, 1.0f, tintIndex, hasAmbientOcclusion, shade);
    }

    public static BakedQuad createQuad(Vec3[] vertex, TextureAtlasSprite sprite, int color, float alpha, int tintIndex, boolean hasAmbientOcclusion, boolean shade) {
        return createQuad(vertex[0], vertex[1], vertex[2], vertex[3], sprite, color, alpha, tintIndex, hasAmbientOcclusion, shade);
    }

    public static BakedQuad createQuad(Vec3 v1, Vec3 v2, Vec3 v3, Vec3 v4, TextureAtlasSprite sprite, int color, float alpha, int tintIndex, boolean hasAmbientOcclusion, boolean shade) {
        Vec3 normal = v3.subtract(v2).cross(v1.subtract(v2)).normalize();
        float nx = (float) normal.x;
        float ny = (float) normal.y;
        float nz = (float) normal.z;

        int tw = sprite.contents().width();
        int th = sprite.contents().height();

        float r = FastColor.ARGB32.red(color) / 255.0f;
        float g = FastColor.ARGB32.green(color) / 255.0f;
        float b = FastColor.ARGB32.blue(color) / 255.0f;

        BakedQuad[] quad = new BakedQuad[1];
        QuadBakingVertexConsumer baker = new QuadBakingVertexConsumer(q -> quad[0] = q);
        baker.setSprite(sprite);
        baker.setHasAmbientOcclusion(hasAmbientOcclusion);
        baker.setShade(shade);
        baker.setTintIndex(tintIndex);
        baker.setDirection(Direction.getNearest(normal.x, normal.y, normal.z));
        baker.normal(nx, ny, nz).vertex(v1.x, v1.y, v1.z).uv(sprite.getU(0), sprite.getV(0)).color(r, g, b, alpha).endVertex();
        baker.normal(nx, ny, nz).vertex(v2.x, v2.y, v2.z).uv(sprite.getU(0), sprite.getV(th)).color(r, g, b, alpha).endVertex();
        baker.normal(nx, ny, nz).vertex(v3.x, v3.y, v3.z).uv(sprite.getU(tw), sprite.getV(th)).color(r, g, b, alpha).endVertex();
        baker.normal(nx, ny, nz).vertex(v4.x, v4.y, v4.z).uv(sprite.getU(tw), sprite.getV(0)).color(r, g, b, alpha).endVertex();
        return quad[0];
    }
}
