package me.mgerdes.raytracer.Maths;

public class Vector {
    public final double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Normal n) {
        this.x = n.x;
        this.y = n.y;
        this.z = n.z;
    }

    public Vector(Point p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
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

    public Vector hat() {
        return this.divide(Math.sqrt(x*x + y*y + z*z));
    }

    public Vector cross(Vector v) {
        return new Vector(this.y * v.z - this.z * v.y,
                this.z * v.x - this.x * v.z,
                this.x * v.y - this.y * v.x);
    }
}
