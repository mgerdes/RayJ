package me.mgerdes.raytracer.Color;

public class RGBColor {
    public final double r, g, b;

    public RGBColor (double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public RGBColor scale(double s) {
        return new RGBColor(s * r, s * g, s * b);
    }

    public RGBColor plus(RGBColor c) {
        return new RGBColor(r + c.r, g + c.g, b + c.b);
    }

    public RGBColor times(RGBColor c) {
        return new RGBColor(r * c.r, g * c.g, b * c.b);
    }
}
