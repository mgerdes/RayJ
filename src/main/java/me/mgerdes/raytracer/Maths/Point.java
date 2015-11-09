package me.mgerdes.raytracer.Maths;

public class Point {
    public final double x, y, z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point plus(Vector v) {
        return new Point(x + v.x, y + v.y, z + v.z);
    }

    public Vector minus(Point p) {
        return new Vector(x - p.x, y - p.y, z - p.z);
    }

    public double distanceTo(Point p) {
        return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y) + (this.z - p.z) * (this.z - p.z));
    }
}
