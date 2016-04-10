package me.mgerdes.raytracer.Materials;

import me.mgerdes.raytracer.Hitables.HitRecord;
import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Maths.Ray;

public class Metal implements Material {

    private Vector3 albedo;
    private double fuzziness;

    public Metal(Vector3 albedo, double fuzziness) {
        this.albedo = albedo;
        this.fuzziness = Math.max(fuzziness, 0);
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

    private Vector3 reflect(Vector3 v, Vector3 n) {
        return Vector3.sub(v, Vector3.scale(n, 2.0 * Vector3.dot(v, n)));
    }

    public boolean scatter(Ray ray, HitRecord hitRecord, Vector3 attenuation, Ray scattered) {
        Vector3 reflected = reflect(ray.direction, hitRecord.normal); 

        Vector3 rand = randomVectorInUnitSphere();
        rand.scaleThis(fuzziness);

        scattered.setThis(hitRecord.point, Vector3.add(reflected, rand));
        attenuation.setThis(albedo);
        return Vector3.dot(scattered.direction, hitRecord.normal) > 0.0;
    }

}
