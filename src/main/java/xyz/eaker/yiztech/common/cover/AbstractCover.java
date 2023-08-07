package xyz.eaker.yiztech.common.cover;

public abstract class AbstractCover {
    private String name;

    public boolean tick() {
        return false;
    }

    public String getName() {
        return this.name;
    }
}
