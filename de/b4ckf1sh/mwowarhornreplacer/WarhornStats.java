package de.b4ckf1sh.mwowarhornreplacer;

public class WarhornStats {

    private String model;
    private String sound;
    private String icon;
    private String material;

    public WarhornStats(String model, String sound, String icon, String material) {
        this.model = model;
        this.sound = sound;
        this.icon = icon;
        this.material = material;
    }

    public String getModel() {
        return model;
    }

    public String getSound() {
        return sound;
    }

    public String getIcon() {
        return icon;
    }

    public String getMaterial() {
        return material;
    }

    public static String replaceWarhornStats(String xml, WarhornStats toReplace, WarhornStats newStats) {
        return xml.replaceAll(toReplace.getIcon(), newStats.getIcon())
                .replaceAll(toReplace.getModel(), newStats.getModel())
                .replaceAll(toReplace.getSound(), newStats.getSound())
                .replaceAll(toReplace.getMaterial(), newStats.getMaterial());
    }
}
