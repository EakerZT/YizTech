package xyz.eaker.yiztech.common.registry;

import xyz.eaker.yiztech.api.material.Material;

public class YTMaterial {
    public static void register() {
        YTRegistry.registerObject(Material.builder("tin")
                .color(0xffb400)
                .metallic()
                .build());
        YTRegistry.registerObject(Material.builder("steel")
                .color(0x808080)
                .metallic()
                .build());
    }
}
