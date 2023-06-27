package xyz.eaker.yiztech.api.material;

import xyz.eaker.yiztech.common.item.material.SimpleMaterialItem;
import xyz.eaker.yiztech.common.registry.YTRegistry;

public class MaterialPartGear extends MaterialPart {
    public MaterialPartGear() {
        super("gear");
    }

    @Override
    public void onRegister(Material material) {
        var name = material.getName() + "_" + this.name;
        YTRegistry.registerItem(name, () -> new SimpleMaterialItem(material, this));
    }
}
