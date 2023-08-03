package xyz.eaker.yiztech.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import xyz.eaker.yiztech.common.menu.BaseMachineMenu;

public class BaseScreen extends AbstractContainerScreen<BaseMachineMenu> {
    public BaseScreen(BaseMachineMenu menu, Inventory inventory, Component pTitle) {
        super(menu, inventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {

    }
}
