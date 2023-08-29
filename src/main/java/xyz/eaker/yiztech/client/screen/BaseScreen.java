package xyz.eaker.yiztech.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import xyz.eaker.yiztech.YizTech;
import xyz.eaker.yiztech.api.menu.BaseComponent;
import xyz.eaker.yiztech.api.menu.BaseMenu;
import xyz.eaker.yiztech.api.screen.BaseWidget;
import xyz.eaker.yiztech.common.registry.YTWidget;

import java.util.ArrayList;
import java.util.List;

public class BaseScreen<T extends BaseMenu> extends AbstractContainerScreen<T> {
    private static final ResourceLocation base = YizTech.loc("textures/gui/base/base.png");
    public final static int ITEM_SLOT_BG_X = 176;
    public final static int ITEM_SLOT_BG_Y = 0;
    public final static int ITEM_SLOT_BG_W = 18;
    public final static int ITEM_SLOT_BG_H = 18;
    private final List<BaseWidget> widgets;
    private final List<BaseWidget> widgetsAdd;

    public BaseScreen(T menu, Inventory inventory, Component pTitle) {
        super(menu, inventory, pTitle);
        this.widgets = new ArrayList<>();
        this.widgetsAdd = new ArrayList<>();
    }

    /*
Only Call In Constructor
 */
    public <C extends BaseWidget> C addWidget(C widget) {
        this.widgetsAdd.add(widget);
        return widget;
    }

    @Override
    protected void init() {
        super.init();
        this.widgets.clear();
        for (BaseComponent component : this.menu.getComponents()) {
            var widget = YTWidget.get(component, (Class<BaseComponent>) component.getClass());
            if (widget != null) {
                widget.init(this);
                this.addRenderableOnly(widget);
                this.widgets.add(widget);
            }
        }
        for (BaseWidget widget : this.widgetsAdd) {
            widget.init(this);
            this.addRenderableOnly(widget);
            this.widgets.add(widget);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(base, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        for (Slot slot : this.menu.slots) {
            guiGraphics.blit(base, this.leftPos + slot.x - 1, this.topPos + slot.y - 1, ITEM_SLOT_BG_X, ITEM_SLOT_BG_Y, ITEM_SLOT_BG_W, ITEM_SLOT_BG_H);
        }
        for (var widget : this.widgets) {
            widget.renderBackground(guiGraphics, p_97789_ - this.leftPos, p_97790_ - this.topPos, p_97788_);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate((float) this.leftPos, (float) this.topPos, 0.0F);
        for (var widget : this.widgets) {
            if (widget.isMouseOver(pMouseX - this.leftPos, pMouseY - this.topPos)) {
                var str = widget.renderTooltip(pGuiGraphics, pMouseX - this.leftPos, pMouseY - this.topPos);
                if (!str.isEmpty()) {
                    pGuiGraphics.renderComponentTooltip(null, str, pMouseX - this.leftPos, pMouseY - this.topPos);
                }
            }
        }
        pGuiGraphics.pose().popPose();
    }
}
