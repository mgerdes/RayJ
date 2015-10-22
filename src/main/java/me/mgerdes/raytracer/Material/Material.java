package me.mgerdes.raytracer.Material;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Utilities.HitInfo;

public interface Material {
    RGBColor shade(HitInfo h);
}
