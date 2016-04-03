package me.mgerdes.raytracer.Hitables;

import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Materials.Material;

public class HitRecord {

    public double t; 
    public Vector3 point;
    public Vector3 normal;
    public Material material;

    public void setThis(double t, Vector3 point, Vector3 normal, Material material) {
        this.t = t;
        this.point = point;
        this.normal = normal;
        this.material = material;
    }

    public void setThis(HitRecord h) {
        this.setThis(h.t, h.point, h.normal, h.material);
    }

}
