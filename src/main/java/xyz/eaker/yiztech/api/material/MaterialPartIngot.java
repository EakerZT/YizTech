package xyz.eaker.yiztech.api.material;

import xyz.eaker.yiztech.common.item.material.MaterialIngotItem;
import xyz.eaker.yiztech.common.registry.YTRegistry;

public class MaterialPartIngot extends MaterialPart {
    public MaterialPartIngot() {
        super("ingot");
    }

    @Override
    public void onRegisterPar(Material material) {
        var name = material.getName() + "_" + this.name;
        YTRegistry.registerItem(name, () -> new MaterialIngotItem(material, this));
    }
}
