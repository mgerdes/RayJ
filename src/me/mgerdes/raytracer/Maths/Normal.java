package me.mgerdes.raytracer.Maths;

public class Normal {
    public final double x, y, z;

    public Normal(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Normal(Vector v) {
        this(v.x, v.y, v.z);
    }
}
