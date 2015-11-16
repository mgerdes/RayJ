package me.mgerdes.raytracer.Material;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class Reflective extends Matte {

    private int maxDepth;

    public Reflective(RGBColor color, double ka, double kd, int maxDepth) {
        super(color, ka, kd);
        this.maxDepth = maxDepth;
    }

    public RGBColor shade(HitInfo hitInfo) {
        if (hitInfo.getRay().depth < maxDepth) {
            Point reflectedRayOrigin = hitInfo.getHitPoint();

            Vector normal = new Vector(hitInfo.getNormal()).hat();

            Vector wo = normal.times(-1);
            double dot = normal.dot(hitInfo.getRay().direction.times(-1).hat());
            Vector reflectedRayDirection = wo.times(-1).plus(normal.times(2 * dot));

            Ray reflectedRay = new Ray(reflectedRayDirection, reflectedRayOrigin, hitInfo.getRay().depth + 1);

            HitInfo h = hitInfo.getWorld().traceRay(reflectedRay);
            if (h.isHit()) {
                return h.getMaterial().shade(h);
            } else {
                return super.shade(hitInfo);
            }
        } else {
            return super.shade(hitInfo);
        }
    }

}