package xyz.eaker.yiztech.api.material;

public abstract class MaterialPart {
    protected final String name;

    public MaterialPart(String name) {
        this.name = name;
    }

    public abstract void onRegisterPar(Material material);
}
