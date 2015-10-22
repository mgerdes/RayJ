package me.mgerdes.raytracer.Light;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class Ambient implements Light {
    private RGBColor color;
    private double ls;

    public Ambient(RGBColor color, double ls) {
        this.color = color;
        this.ls = ls;
    }

    public RGBColor L(HitInfo h) {
        return color.scale(ls);
    }
}
