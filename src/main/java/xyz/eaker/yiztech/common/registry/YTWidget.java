package xyz.eaker.yiztech.common.registry;

import com.google.common.collect.Maps;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.eaker.yiztech.api.menu.BaseComponent;
import xyz.eaker.yiztech.api.menu.FluidSlot;
import xyz.eaker.yiztech.api.menu.ProgressBarComponent;
import xyz.eaker.yiztech.api.screen.BaseWidget;
import xyz.eaker.yiztech.api.screen.FluidSlotWidget;
import xyz.eaker.yiztech.api.screen.ProgressBarWidget;

import java.util.Map;
import java.util.function.Function;

public class YTWidget {
    private static final Map<Class<BaseComponent>, Function<BaseComponent, BaseWidget>> widgetMap = Maps.newHashMap();


    private static <T extends BaseComponent, V extends BaseWidget> void register(Class<T> componentClass, Function<T, V> construct) {
        widgetMap.put((Class<BaseComponent>) componentClass, (Function<BaseComponent, BaseWidget>) construct);
    }

    public static <T extends BaseComponent> BaseWidget get(T component, Class<T> componentClass) {
        return widgetMap.get(componentClass).apply(component);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onRegisterWidget() {
        register(FluidSlot.class, FluidSlotWidget::new);
        register(ProgressBarComponent.class, ProgressBarWidget::new);
    }
}
