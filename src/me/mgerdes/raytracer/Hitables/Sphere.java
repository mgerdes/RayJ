package me.mgerdes.raytracer.Hitables;

import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Materials.Material;

public class Sphere implements Hitable {

    public Vector3 center;
    public double radius;
    public Material material;

    public Sphere(Vector3 center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public boolean hit(Ray ray, HitRecord hitRecord) {
        Vector3 originMinusCenter = Vector3.sub(ray.origin, center);
        double a = Vector3.dot(ray.direction, ray.direction);
        double b = Vector3.dot(ray.direction, originMinusCenter);
        double c = Vector3.dot(originMinusCenter, originMinusCenter) - radius * radius;
        double discriminant = b * b - a * c; 

        if (discriminant < 0.0) {
            return false;
        }

        double t =  (-b - Math.sqrt(b * b - a * c)) / a;
        if (t < 0.0) {
            return false;
        }

        Vector3 hitPoint = ray.pointAtTime(t);
        Vector3 hitNormal = Vector3.sub(hitPoint, center);
        hitNormal.normalizeThis();
        hitRecord.setThis(t, hitPoint, hitNormal, material);

        return true;
    }

}
