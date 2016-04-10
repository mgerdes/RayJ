package me.mgerdes.raytracer.Materials;

import me.mgerdes.raytracer.Hitables.HitRecord;
import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Maths.Ray;

public class Dielectric implements Material {

    private double refractionIndex;

    public Dielectric(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    private Vector3 reflect(Vector3 v, Vector3 n) {
        return Vector3.sub(v, Vector3.scale(n, 2.0 * Vector3.dot(v, n)));
    }

    private boolean refract(Vector3 v, Vector3 n, double niOverNt, Vector3 refracted) {
        Vector3 uv = Vector3.normalOf(v);
        double dt = Vector3.dot(uv, n);
        double discriminant = 1.0 - niOverNt * niOverNt * (1.0 - dt * dt);
        if (discriminant > 0.0) {
            Vector3 temp1 = Vector3.scale(Vector3.sub(v, Vector3.scale(n, dt)), niOverNt);
            Vector3 temp2 = Vector3.scale(n, Math.sqrt(discriminant));
            refracted.setThis(Vector3.sub(temp1, temp2));
        }
        return false;
    }

    public boolean scatter(Ray ray, HitRecord hitRecord, Vector3 attenuation, Ray scattered) {
        attenuation.setThis(1.0, 1.0, 1.0);

        double niOverNt;
        Vector3 outwardNormal;
        if (Vector3.dot(ray.direction, hitRecord.normal) > 0.0) {
            outwardNormal = Vector3.scale(hitRecord.normal, -1.0);
            niOverNt = refractionIndex;
        }
        else {
            outwardNormal = hitRecord.normal;
            niOverNt = 1.0 / refractionIndex;
        }

        Vector3 refracted = new Vector3();
        if (refract(ray.direction, outwardNormal, niOverNt, refracted)) {
            scattered.setThis(new Ray(hitRecord.point, refracted));
        } 
        else {
            Vector3 reflected = reflect(ray.direction, hitRecord.normal);
            scattered.setThis(new Ray(hitRecord.point, reflected));
        }

        return true;
    }

}
