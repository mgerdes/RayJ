package me.mgerdes.raytracer.Hitables;

import me.mgerdes.raytracer.Maths.Ray;

public interface Hitable {

    public boolean hit(Ray ray, HitRecord hitRecord);

}
