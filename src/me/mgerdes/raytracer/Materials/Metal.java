package me.mgerdes.raytracer.Materials;

import me.mgerdes.raytracer.Hitables.HitRecord;
import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Maths.Ray;

public class Metal implements Material {

    private Vector3 albedo;

    public Metal(Vector3 albedo) {
        this.albedo = albedo;
    }

    private Vector3 reflect(Vector3 v, Vector3 n) {
        return Vector3.sub(v, Vector3.scale(n, 2.0 * Vector3.dot(v, n)));
    }

    public boolean scatter(Ray ray, HitRecord hitRecord, Vector3 attenuation, Ray scattered) {
        Vector3 reflected = reflect(ray.direction, hitRecord.normal); 
        scattered.setThis(hitRecord.point, reflected);
        attenuation.setThis(albedo);
        return Vector3.dot(scattered.direction, hitRecord.normal) > 0.0;
    }

}
