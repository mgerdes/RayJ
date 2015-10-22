package me.mgerdes.raytracer.GeometricObjects;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Material.Material;
import me.mgerdes.raytracer.Maths.Normal;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class Sphere implements GeometricObject {
    private Point center;
    private double radius;
    private Material material;

    public Sphere(Point center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public HitInfo hit(Ray ray) {
        Vector temp = ray.origin.minus(center);
        double a = ray.direction.dot(ray.direction);
        double b = 2.0 * temp.dot(ray.direction);
        double c = temp.dot(temp) - radius * radius;
        double disc	= b * b - 4.0 * a * c;

        if (disc < 0.0) {
            return new HitInfo(false);
        } else {
            double e = Math.sqrt(disc);
            double denom = 2.0 * a;
            double t = (-b - e) / denom;

            if (t > EPSILON) {
                Vector v = temp.plus(ray.direction.times(t)).divide(radius);
                HitInfo h = new HitInfo(true);
                h.setNormal(new Normal(v));
                h.setMaterial(material);
                h.setTime(t);

                Point hitPoint = ray.origin.plus(ray.direction.times(t));
                h.setHitPoint(hitPoint);
                return h;
            }

            t = (-b + e) / denom;

            if (t > EPSILON) {
                Vector v = temp.plus(ray.direction.times(t)).divide(radius);
                HitInfo h = new HitInfo(true);
                h.setNormal(new Normal(v));
                h.setMaterial(material);
                h.setTime(t);

                Point hitPoint = ray.origin.plus(ray.direction.times(t));
                h.setHitPoint(hitPoint);
                return h;
            }
        }

        return new HitInfo(false);
    }
}
