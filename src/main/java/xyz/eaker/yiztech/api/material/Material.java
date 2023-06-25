package xyz.eaker.yiztech.api.material;

import xyz.eaker.yiztech.api.register.IRegisterObject;

import java.util.ArrayList;
import java.util.List;

public class Material implements IRegisterObject {
    private final String name;
    private final int color;
    private final List<MaterialPart> parts;

    public Material(String name, int color, List<MaterialPart> parts) {
        this.name = name;
        this.color = color;
        this.parts = parts;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public static MaterialBuilder builder(String name) {
        return new MaterialBuilder(name);
    }

    @Override
    public void onServerRegister() {
        for (MaterialPart part : this.parts) {
            part.onRegisterPar(this);
        }
    }

    @Override
    public void onCommonRegister() {

    }

    public static class MaterialBuilder {
        private final String name;
        private int color = -1;
        private final List<MaterialPart> parts = new ArrayList<>();

        public MaterialBuilder(String name) {
            this.name = name;
        }

        public MaterialBuilder color(int color) {
            this.color = color;
            return this;
        }

        public MaterialBuilder ingot() {
            this.parts.add(new MaterialPartIngot());
            return this;
        }


        public Material build() {
            return new Material(this.name, this.color, parts);
        }
    }
}
