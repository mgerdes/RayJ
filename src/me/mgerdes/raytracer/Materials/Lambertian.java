package me.mgerdes.raytracer.Materials;

import me.mgerdes.raytracer.Hitables.HitRecord;
import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Maths.Ray;

public class Lambertian implements Material {

    private Vector3 albedo;

    public Lambertian(Vector3 albedo) {
        this.albedo = albedo;
    }

    private Vector3 randomVectorInUnitSphere() {
        double theta = Math.random() * Math.PI;
        double phi = Math.random() * 2 * Math.PI;

        double sinTheta = Math.sin(theta);
        double cosTheta = Math.cos(theta);
        double sinPhi = Math.sin(phi);
        double cosPhi = Math.cos(phi);

        return new Vector3(sinTheta * cosPhi, sinTheta * sinPhi, cosTheta);
    }

    public boolean scatter(Ray ray, HitRecord hitRecord, Vector3 attenuation, Ray scattered) {
        Vector3 target = Vector3.add(
                Vector3.add(hitRecord.point, hitRecord.normal),
                randomVectorInUnitSphere());

        scattered.setThis(hitRecord.point, Vector3.sub(target, hitRecord.point));
        attenuation.setThis(albedo.x, albedo.y, albedo.z);

        return true;
    }

}
