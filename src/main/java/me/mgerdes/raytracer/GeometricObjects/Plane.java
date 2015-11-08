package me.mgerdes.raytracer.GeometricObjects;

import me.mgerdes.raytracer.Material.Material;
import me.mgerdes.raytracer.Maths.Normal;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class Plane implements GeometricObject {
    private Point point;
    private Normal normal;
    private Material material;

    public Plane(Point point, Normal normal, Material material) {
        this.point = point;
        this.normal = normal;
        this.material = material;
    }

    public HitInfo hit(Ray ray) {
        Vector normalVector = new Vector(normal);
        double t = point.minus(ray.origin).dot(normalVector) / ray.direction.dot(normalVector);

        if (t > EPSILON) {
            HitInfo hitInfo = new HitInfo(true);
            hitInfo.setTime(t);
            hitInfo.setNormal(normal);
            hitInfo.setMaterial(material);
            hitInfo.setRay(ray);

            Point hitPoint = ray.origin.plus(ray.direction.times(t));
            hitInfo.setHitPoint(hitPoint);
            return hitInfo;
        }

        return new HitInfo(false);
    }
}
