package me.mgerdes.raytracer.Samplers;

import me.mgerdes.raytracer.Maths.Point;

public interface Sampler {
    Point[] getSamplePoints();
}
