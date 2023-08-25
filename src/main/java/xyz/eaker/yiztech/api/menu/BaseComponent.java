package xyz.eaker.yiztech.api.menu;

public abstract class BaseComponent {
    protected int index;
    protected int x;
    protected int y;
    protected int w;
    protected int h;

    public BaseComponent(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.index = -1;
    }

    protected void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
