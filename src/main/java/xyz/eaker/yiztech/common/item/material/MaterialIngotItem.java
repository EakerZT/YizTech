package xyz.eaker.yiztech.common.item.material;

import net.minecraft.world.item.ItemStack;
import xyz.eaker.yiztech.api.material.Material;
import xyz.eaker.yiztech.api.material.MaterialPart;
import xyz.eaker.yiztech.api.register.IItemColorProvider;
import xyz.eaker.yiztech.common.util.ColorHelper;

public class MaterialIngotItem extends BaseMaterialItem implements IItemColorProvider {
    private final int[] color = new int[5];

    public MaterialIngotItem(Material material, MaterialPart materialPart) {
        super(material, materialPart);
        color[0] = ColorHelper.HEXtoDEC(ColorHelper.INTHueShift(material.getColor(), 3, true));
        color[1] = ColorHelper.HEXtoDEC(ColorHelper.INTHueShift(material.getColor(), 1, true));
        color[2] = material.getColor();
        color[3] = ColorHelper.HEXtoDEC(ColorHelper.INTHueShift(material.getColor(), 1, false));
        color[4] = ColorHelper.HEXtoDEC(ColorHelper.INTHueShift(material.getColor(), 2, false));
    }

    @Override
    public int getColor(ItemStack pStack, int pTintIndex) {
        if (pTintIndex > -1 && pTintIndex < 5) {
            return color[pTintIndex];
        }
        return -1;
    }
}
