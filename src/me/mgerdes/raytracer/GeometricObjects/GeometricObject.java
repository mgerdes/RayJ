package me.mgerdes.raytracer.GeometricObjects;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Utilities.HitInfo;

public interface GeometricObject {
    double EPSILON = 0.0001;
    HitInfo hit(Ray ray);
    RGBColor getColor();
}
