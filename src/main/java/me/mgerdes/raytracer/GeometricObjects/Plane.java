package me.mgerdes.raytracer.GeometricObjects;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Normal;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class Plane implements GeometricObject {
    private Point point;
    private Normal normal;
    private RGBColor color;

    public Plane(Point point, Normal normal, RGBColor color) {
        this.point = point;
        this.normal = normal;
        this.color = color;
    }

    public HitInfo hit(Ray ray) {
        Vector normalVector = new Vector(normal);
        double t = point.minus(ray.origin).dot(normalVector) / ray.direction.dot(normalVector);

        if (t > EPSILON) {
            HitInfo hitInfo = new HitInfo(true);
            hitInfo.setTime(t);
            hitInfo.setNormal(normal);
            hitInfo.setColor(color);
            return hitInfo;
        }

        return new HitInfo(false);
    }
}
