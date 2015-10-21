package me.mgerdes.raytracer.Maths;

public class Vector {
    public final double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector plus(Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    public double dot(Vector v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Vector times(double s) {
        return new Vector(x * s , y * s, z * s);
    }

    public Vector divide(double s) {
        return new Vector(x / s , y / s, z / s);
    }
}
