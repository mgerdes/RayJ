package me.mgerdes.raytracer.Light;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Utilities.HitInfo;

public interface Light {
    RGBColor L(HitInfo h);
}
