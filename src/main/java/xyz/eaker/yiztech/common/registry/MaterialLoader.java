package xyz.eaker.yiztech.common.registry;

import xyz.eaker.yiztech.api.material.Material;

public class MaterialLoader {
    public static void register() {
        YTRegistry.registerObject(Material.builder("tin")
                .color(0x808080)
                .ingot()
                .build());
    }
}
