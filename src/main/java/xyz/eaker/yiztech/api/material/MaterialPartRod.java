package xyz.eaker.yiztech.api.material;

import xyz.eaker.yiztech.common.item.material.SimpleMaterialItem;
import xyz.eaker.yiztech.common.registry.YTRegistry;

public class MaterialPartRod extends MaterialPart {
    public MaterialPartRod() {
        super("rod");
    }

    @Override
    public void onRegister(Material material) {
        var name = material.getName() + "_" + this.name;
        YTRegistry.item(name, () -> new SimpleMaterialItem(material, this));
    }
}
