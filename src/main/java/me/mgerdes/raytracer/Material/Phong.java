package me.mgerdes.raytracer.Material;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Light.Light;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

import java.util.List;

public class Phong implements Material {

    private final RGBColor color;
    private final double ka, kd, ks;

    public Phong(RGBColor color, double ka, double kd, double ks) {
        this.color = color;
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
    }

    public RGBColor shade(HitInfo h) {
        RGBColor c = ambientTerm().plus(diffuseTerm(h)).plus(specularTerm(h));
        if (c.r > 255 || c.g > 255 || c.b > 255) {
            System.out.println("Gamut Overflow");
        }
        return c;
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
            double l_dot_n = Math.max(l.dot(n), 0);

            RGBColor c = color.scale(l_dot_n * kd);
            shade = shade.plus(c);
        }
        return shade;
    }

    private RGBColor specularTerm(HitInfo h) {
        RGBColor shade = new RGBColor(0,0,0);
        List<Light> lights = h.getWorld().getLights();
        for (Light light : lights) {
            Vector l = light.getLocation().minus(h.getHitPoint()).hat();
            Vector n = new Vector(h.getNormal()).hat();
            double l_dot_n = Math.max(l.dot(n), 0);

            Vector r = l.times(-1).plus(n.times(2 * l_dot_n)).hat();
            Vector w = h.getRay().direction.times(-1).hat();
            double r_dot_w = Math.max(r.dot(w), 0);

            RGBColor c = color.scale(Math.pow(r_dot_w, 5) * l_dot_n * ks);
            shade = shade.plus(c);
        }
        return shade;
    }
}
