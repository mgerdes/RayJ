package me.mgerdes.raytracer.Utilities;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Normal;

public class HitInfo {
    private RGBColor color;
    private Normal normal;
    private double time;
    private boolean isHit;

    public HitInfo(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setColor(RGBColor color) {
        this.color = color;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    public RGBColor getColor() {
        return color;
    }

    public Normal getNormal() {
        return normal;
    }
}
