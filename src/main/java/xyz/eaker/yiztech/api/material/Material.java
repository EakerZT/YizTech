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
    public void onCommonRegister() {
        for (MaterialPart part : this.parts) {
            part.onRegister(this);
        }
    }

    @Override
    public void onClientRegister() {
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

        public MaterialBuilder plate() {
            this.parts.add(new MaterialPartPlate());
            return this;
        }

        public MaterialBuilder metallic() {
            this.parts.add(new MaterialPartIngot());
            this.parts.add(new MaterialPartNugget());
            this.parts.add(new MaterialPartPlate());
            this.parts.add(new MaterialPartGear());
            this.parts.add(new MaterialPartRod());
            this.parts.add(new MaterialPartDust());
            return this;
        }

        public Material build() {
            return new Material(this.name, this.color, parts);
        }
    }
}
