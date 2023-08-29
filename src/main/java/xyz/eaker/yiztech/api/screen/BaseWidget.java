package xyz.eaker.yiztech.api.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import xyz.eaker.yiztech.client.screen.BaseScreen;

import java.util.Collections;
import java.util.List;

public abstract class BaseWidget extends AbstractWidget {
    protected int guiLeft, guiTop;

    public BaseWidget(int pX, int pY, int pWidth, int pHeight) {
        super(pX, pY, pWidth, pHeight, Component.empty());
    }

    public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

    }

    public List<Component> renderTooltip(GuiGraphics pGuiGraphics, int mouseX, int mouseY) {
        return Collections.emptyList();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public void init(BaseScreen screen) {
        this.guiLeft = screen.getGuiLeft();
        this.guiTop = screen.getGuiTop();
    }
}
