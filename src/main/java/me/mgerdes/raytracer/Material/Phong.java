package me.mgerdes.raytracer.Material;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.GeometricObjects.GeometricObject;
import me.mgerdes.raytracer.Light.Light;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Samplers.HemisphereSampler;
import me.mgerdes.raytracer.Samplers.Sampler;
import me.mgerdes.raytracer.Utilities.HitInfo;

import java.util.List;

public class Phong implements Material {

    private final static int NUMBER_OF_SAMPLES = 256;

    private final Sampler sampler;
    private final RGBColor color;
    private final double ka, kd, ks, e;

    public Phong(RGBColor color, double ka, double kd, double ks, double e) {
        this.color = color;
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.e = e;
        this.sampler = new HemisphereSampler(NUMBER_OF_SAMPLES);
    }

    public RGBColor shade(HitInfo h) {
        RGBColor c = ambientTerm(h).plus(diffuseTerm(h)).plus(specularTerm(h));
        if (c.r > 255 || c.g > 255 || c.b > 255) {
            System.out.println("Gamut Overflow");
        }
        return c;
    }

    private RGBColor ambientTerm(HitInfo h) {
        Vector w = new Vector(h.getNormal()).hat();
        Vector v = w.cross(new Vector(0.0072,1,0.0034)).hat();
        Vector u = v.cross(w).hat();

        int hits = 0;
        for (Point p : sampler.getSamplePoints()) {
            Vector direction = u.times(p.x).plus(v.times(p.y)).plus(w.times(p.z));
            Ray ray = new Ray(direction, h.getHitPoint());

            for (GeometricObject object : h.getWorld().getObjects()) {
                HitInfo hit = object.hit(ray);
                if (hit.isHit()) {
                    hits++;
                    break;
                }
            }
        }
        return color.scale(ka * (1 - (((double) hits) / NUMBER_OF_SAMPLES)));
    }

    private RGBColor diffuseTerm(HitInfo h) {
        RGBColor shade = new RGBColor(0,0,0);
        List<Light> lights = h.getWorld().getLights();
        for (Light light : lights) {
            if (!light.isInShadow(h)) {
                Vector l = light.getLocation().minus(h.getHitPoint()).hat();
                Vector n = new Vector(h.getNormal()).hat();
                double l_dot_n = Math.max(l.dot(n), 0);

                RGBColor c = color.scale(l_dot_n * kd);
                shade = shade.plus(c);
            }
        }
        return shade;
    }

    private RGBColor specularTerm(HitInfo h) {
        RGBColor shade = new RGBColor(0,0,0);
        List<Light> lights = h.getWorld().getLights();
        for (Light light : lights) {
            if (!light.isInShadow(h)) {
                Vector l = light.getLocation().minus(h.getHitPoint()).hat();
                Vector n = new Vector(h.getNormal()).hat();
                double l_dot_n = Math.max(l.dot(n), 0);

                Vector r = l.times(-1).plus(n.times(2 * l_dot_n)).hat();
                Vector w = h.getRay().direction.times(-1).hat();
                double r_dot_w = Math.max(r.dot(w), 0);

                RGBColor c = color.scale(Math.pow(r_dot_w, e) * l_dot_n * ks);
                shade = shade.plus(c);
            }
        }
        return shade;
    }
}
