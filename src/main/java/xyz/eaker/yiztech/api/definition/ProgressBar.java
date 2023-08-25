package xyz.eaker.yiztech.api.definition;

import net.minecraft.resources.ResourceLocation;
import xyz.eaker.yiztech.YizTech;

public class ProgressBar {
    public final static ProgressBar ARROW = new ProgressBar(0, 0, 20, 20, "arrow", ProgressDirection.HORIZONTAL);
    public final static ProgressBar ARROW_MULTIPLE = new ProgressBar(20, 0, 20, 20, "arrow_multiple", ProgressDirection.HORIZONTAL);
    public final static ProgressBar HAMMER = new ProgressBar(40, 0, 20, 20, "hammer", ProgressDirection.VERTICAL);
    public final static ProgressBar WIREMILL = new ProgressBar(60, 0, 20, 20, "wiremill", ProgressDirection.HORIZONTAL);
    public final static ProgressBar CIRCUIT = new ProgressBar(80, 0, 20, 20, "circuit", ProgressDirection.HORIZONTAL);
    public final static ProgressBar EXTRACT = new ProgressBar(100, 0, 20, 20, "extract", ProgressDirection.HORIZONTAL);
    public final static ProgressBar LATHE = new ProgressBar(120, 0, 20, 20, "lathe", ProgressDirection.HORIZONTAL);
    public final static ProgressBar MAGNET = new ProgressBar(140, 0, 20, 20, "magnet", ProgressDirection.HORIZONTAL);
    public final static ProgressBar RECYCLER = new ProgressBar(160, 0, 20, 20, "recycler", ProgressDirection.HORIZONTAL);
    public final static ProgressBar SLICE = new ProgressBar(180, 0, 20, 20, "slice", ProgressDirection.HORIZONTAL);

    public final static ProgressBar BURN = new ProgressBar(0, 40, 20, 20, 3, 3, "burn", ProgressDirection.VERTICAL_REVERSE);
    public final static ProgressBar ARROW_DOWN = new ProgressBar(20, 40, 20, 20, "arrow_down", ProgressDirection.VERTICAL);
    public final static ProgressBar BATH = new ProgressBar(40, 40, 20, 20, "bath", ProgressDirection.HORIZONTAL);
    public final static ProgressBar CANNER = new ProgressBar(60, 40, 20, 20, "canner", ProgressDirection.HORIZONTAL);
    public final static ProgressBar COMPRESS = new ProgressBar(80, 40, 20, 20, "compress", ProgressDirection.HORIZONTAL);
    public final static ProgressBar EXTRUDER = new ProgressBar(100, 40, 20, 20, "extruder", ProgressDirection.HORIZONTAL);
    public final static ProgressBar MACERATE = new ProgressBar(120, 40, 20, 20, "macerate", ProgressDirection.HORIZONTAL);
    public final static ProgressBar MIXER = new ProgressBar(140, 40, 20, 20, "mixer", ProgressDirection.HORIZONTAL);
    public final static ProgressBar SIFT = new ProgressBar(160, 40, 20, 20, "sift", ProgressDirection.HORIZONTAL);
    public final static ProgressBar BENDING = new ProgressBar(180, 40, 20, 20, "bending", ProgressDirection.HORIZONTAL);
    public final ResourceLocation resourceLocation;
    public final int x;
    public final int y;
    public final int width;
    public final int height;
    public final ProgressDirection direction;
    public final int paddingStart;
    public final int paddingEnd;

    private ProgressBar(int x, int y, int width, int height, String name, ProgressDirection direction) {
        this(x, y, width, height, 0, 0, name, direction);
    }

    private ProgressBar(int x, int y, int width, int height, int paddingStart, int paddingEnd, String name, ProgressDirection direction) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.resourceLocation = YizTech.loc("textures/gui/progress_bar/" + name + ".png");
        this.paddingStart = paddingStart;
        this.paddingEnd = paddingEnd;
    }

    public enum ProgressDirection {
        HORIZONTAL, VERTICAL, VERTICAL_REVERSE
    }
}
