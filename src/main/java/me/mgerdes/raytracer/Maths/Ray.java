package me.mgerdes.raytracer.Maths;

public class Ray {
    public final Vector direction;
    public final Point origin;
    public final int depth;

    public Ray(Vector direction, Point origin) {
        this.direction = direction;
        this.origin = origin;
        this.depth = 0;
    }

    public Ray(Vector direction, Point origin, int depth) {
        this.direction = direction;
        this.origin = origin;
        this.depth = depth;
    }
}
