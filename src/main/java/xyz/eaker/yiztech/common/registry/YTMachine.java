package xyz.eaker.yiztech.common.registry;

import xyz.eaker.yiztech.api.machine.Machine;

public class YTMachine {
    public static void register() {
        YTRegistry.registerObject(new Machine("alloy_smelter"));
    }
}
