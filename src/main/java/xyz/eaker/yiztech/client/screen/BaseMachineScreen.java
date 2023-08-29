package xyz.eaker.yiztech.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import xyz.eaker.yiztech.common.menu.BaseMachineMenu;

public class BaseMachineScreen extends BaseScreen<BaseMachineMenu> {

    public BaseMachineScreen(BaseMachineMenu menu, Inventory inventory, Component pTitle) {
        super(menu, inventory, pTitle);
    }
}
