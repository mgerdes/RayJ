package me.mgerdes.raytracer.Tracers;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Ray;

public interface Tracer {
    RGBColor traceRay(Ray r, int depth);
}
