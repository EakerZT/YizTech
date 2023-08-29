package xyz.eaker.yiztech.api.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.resources.ResourceLocation;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.api.menu.ProgressBarComponent;

public class ProgressBarWidget extends BaseWidget {
    private final ProgressBarComponent component;
    private final static ResourceLocation texture = YizTech.loc("textures/gui/progress_bar.png");

    public ProgressBarWidget(ProgressBarComponent component) {
        super(component.x, component.y, component.w, component.h);
        this.component = component;
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        var bar = this.component.getProgressBar();
        pGuiGraphics.blit(texture, this.getX() + this.guiLeft, this.getY() + this.guiTop, bar.x, bar.y, bar.width, bar.height);
    }
}
