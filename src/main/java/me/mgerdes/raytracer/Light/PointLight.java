package me.mgerdes.raytracer.Light;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

public class PointLight implements Light {
    private RGBColor color;
    private Point location;
    private double ls;

    public PointLight(RGBColor color, Point location, double ls) {
        this.color = color;
        this.location = location;
        this.ls = ls;
    }

    public RGBColor L(HitInfo h) {
        Vector normal = new Vector(h.getNormal());
        double dot = location.minus(h.getHitPoint()).dot(normal);
        return color.scale(ls * dot);
    }
}
