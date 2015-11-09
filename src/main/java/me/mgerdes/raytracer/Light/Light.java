package me.mgerdes.raytracer.Light;

import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Utilities.HitInfo;

public interface Light {
    Point getLocation();
    boolean isInShadow(HitInfo h);
}
