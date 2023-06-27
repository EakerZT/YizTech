package xyz.eaker.yiztech.api.material;

import xyz.eaker.yiztech.common.item.material.SimpleMaterialItem;
import xyz.eaker.yiztech.common.registry.YTRegistry;

public class MaterialPartPlate extends MaterialPart {
    public MaterialPartPlate() {
        super("plate");
    }

    @Override
    public void onRegister(Material material) {
        var name = material.getName() + "_" + this.name;
        YTRegistry.item(name, () -> new SimpleMaterialItem(material, this));
    }
}
