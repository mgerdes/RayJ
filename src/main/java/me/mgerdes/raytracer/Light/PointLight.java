package me.mgerdes.raytracer.Light;

import me.mgerdes.raytracer.GeometricObjects.GeometricObject;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class PointLight implements Light {
    private Point location;

    public PointLight(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public boolean isInShadow(HitInfo h) {
        Ray r = new Ray(location.minus(h.getHitPoint()), h.getHitPoint());

        double distanceToLight = location.distanceTo(r.origin);

        for (GeometricObject g : h.getWorld().getObjects()) {
            HitInfo hit = g.hit(r);
            if (hit.isHit() && hit.getTime() < distanceToLight) {
                return true;
            }
        }
        return false;
    }
}
