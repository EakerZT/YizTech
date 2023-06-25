package xyz.eaker.yiztech.common.item.material;

import net.minecraft.world.item.Item;
import xyz.eaker.yiztech.api.material.Material;
import xyz.eaker.yiztech.api.material.MaterialPart;

public class BaseMaterialItem extends Item {
    protected final Material material;
    protected final MaterialPart materialPart;

    public BaseMaterialItem(Material material, MaterialPart materialPart) {
        super(new Properties());
        this.material = material;
        this.materialPart = materialPart;
    }
}
