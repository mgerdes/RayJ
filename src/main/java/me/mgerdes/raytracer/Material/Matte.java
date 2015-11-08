package me.mgerdes.raytracer.Material;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Light.Light;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

import java.util.List;

public class Matte implements Material {
    private RGBColor color;
    private double ka, kd;

    public Matte(RGBColor color, double ka, double kd) {
        this.color = color;
        this.ka = ka;
        this.kd = kd;
    }

    public RGBColor shade(HitInfo h) {
        RGBColor color = ambientTerm().plus(diffuseTerm(h));
        if (color.r > 255 || color.g > 255 || color.b > 255) {
            System.out.println("Gamut Overflow");
        }
        return color;
    }

    private RGBColor ambientTerm() {
        return color.scale(ka);
    }

    private RGBColor diffuseTerm(HitInfo h) {
        RGBColor shade = new RGBColor(0,0,0);
        List<Light> lights = h.getWorld().getLights();
        for (Light light : lights) {
            Vector l = light.getLocation().minus(h.getHitPoint()).hat();
            Vector n = new Vector(h.getNormal()).hat();
            double dot = Math.max(l.dot(n), 0);
            RGBColor c = color.scale(dot * kd);
            shade = shade.plus(c);
        }
        return shade;
    }
}
