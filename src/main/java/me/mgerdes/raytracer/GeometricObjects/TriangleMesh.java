package me.mgerdes.raytracer.GeometricObjects;

import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Utilities.HitInfo;

import java.util.ArrayList;
import java.util.List;

public class TriangleMesh implements GeometricObject {

    private List<Triangle> triangles;

    public TriangleMesh() {
        this.triangles = new ArrayList<>();
    }

    public HitInfo hit(Ray ray) {
        for (Triangle t : triangles) {
            HitInfo h = t.hit(ray);
            if (h.isHit()) {
                return h;
            }
        }
        return new HitInfo(false);
    }

    public void addTriangle(Triangle t) {
        triangles.add(t);
    }

}
