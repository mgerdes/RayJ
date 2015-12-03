package me.mgerdes.raytracer.Material;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class Reflective extends Phong {

    private int maxDepth;

    public Reflective(RGBColor color, double ka, double kd, double ks, double e, int maxDepth) {
        super(color, ka, kd, ks, e);
        this.maxDepth = maxDepth;
    }

    public RGBColor shade(HitInfo hitInfo) {
        Point reflectedRayOrigin = hitInfo.getHitPoint();

        Vector normal = new Vector(hitInfo.getNormal()).hat();

        Vector wo = hitInfo.getRay().direction.times(-1).hat();
        double dot = normal.dot(wo);
        Vector wi = wo.times(-1).plus(normal.times(2 * dot)).hat();

        Ray reflectedRay = new Ray(wi, reflectedRayOrigin);

        RGBColor L = super.shade(hitInfo);
        L.plus(hitInfo.getWorld().getTracer().traceRay(reflectedRay, hitInfo.getDepth() + 1));
        return L;
    }

}
