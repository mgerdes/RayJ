package me.mgerdes.raytracer.GeometricObjects;

import me.mgerdes.raytracer.Material.Material;
import me.mgerdes.raytracer.Maths.Normal;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class Triangle implements GeometricObject{

    private Point p0, p1, p2;
    private Material material;
    private Normal normal;

    public Triangle(Point p0, Point p1, Point p2, Material material) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.material = material;
        this.normal = new Normal(p0.minus(p1).cross(p0.minus(p2)).hat());
    }

    public HitInfo hit(Ray ray) {
        double a = p0.x - p1.x, b = p0.x - p2.x, c = ray.direction.x, d = p0.x - ray.origin.x;
        double e = p0.y - p1.y, f = p0.y - p2.y, g = ray.direction.y, h = p0.y - ray.origin.y;
        double i = p0.z - p1.z, j = p0.z - p2.z, k = ray.direction.z, l = p0.z - ray.origin.z;

        double m = f * k - g * j, n = h * k - g * l, p = f * l - h * j;
        double q = g * i - e * k, s = e * j - f * i;

        double inv_denom = 1.0 / (a * m + b * q + c * s);

        double e1 = d * m - b * n - c * p;
        double beta = e1 * inv_denom;

        if (beta < 0.0) {
            return new HitInfo(false);
        }

        double r = e * l - h * i;
        double e2 = a * n + d * q + c * r;
        double gamma = e2 * inv_denom;

        if (gamma < 0.0) {
            return new HitInfo(false);
        }

        if (beta + gamma > 1.0) {
            return new HitInfo(false);
        }

        double e3 = a * p - b * r + d * s;
        double t = e3 * inv_denom;

        if (t < EPSILON) {
            return new HitInfo(false);
        }

        HitInfo hitInfo = new HitInfo(true);
        hitInfo.setTime(t);
        hitInfo.setNormal(normal);
        hitInfo.setMaterial(material);
        hitInfo.setRay(ray);
        hitInfo.setHitPoint(ray.origin.plus(ray.direction.times(t)));

        return hitInfo;
    }

}
