package xyz.eaker.yiztech.api.screen;

import net.minecraft.client.gui.GuiGraphics;
import xyz.eaker.yiztech.api.menu.FluidSlot;

public class FluidSlotWidget extends BaseWidget {
    public FluidSlotWidget(FluidSlot fluidSlot) {
        super(fluidSlot.x, fluidSlot.y, fluidSlot.w, fluidSlot.h);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

    }
}
