package me.mgerdes.raytracer.Maths;

public class Ray {
    public final Vector direction;
    public final Point origin;

    public Ray(Vector direction, Point origin) {
        this.direction = direction;
        this.origin = origin;
    }
}
