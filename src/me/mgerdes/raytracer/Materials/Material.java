package me.mgerdes.raytracer.Materials;

import me.mgerdes.raytracer.Hitables.HitRecord;
import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Maths.Ray;

public interface Material {

    public boolean scatter(Ray ray, HitRecord hitRecord, Vector3 attenuation, Ray scattered);

}
