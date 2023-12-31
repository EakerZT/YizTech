package xyz.eaker.yiztech.api.material;

public abstract class MaterialPart {
    protected final String name;

    public MaterialPart(String name) {
        this.name = name;
    }

    public abstract void onRegister(Material material);

    public String getName() {
        return this.name;
    }
}
