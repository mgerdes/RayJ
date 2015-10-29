package me.mgerdes.raytracer.Material;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Light.Light;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class Matte implements Material {
    private RGBColor cd;
    private double kd;

    public Matte(RGBColor cd, double kd) {
        this.cd = cd;
        this.kd = kd;
    }

    public RGBColor shade(HitInfo h) {
        RGBColor color = new RGBColor(0,0,0);

        for (Light light : h.getWorld().getLights()) {
            color = color.plus(light.L(h).times(cd));
        }

        if (color.r > 255 || color.g > 255 || color.b > 255) {
            color = new RGBColor(0,0,0);
        }

        return color;
    }
}
