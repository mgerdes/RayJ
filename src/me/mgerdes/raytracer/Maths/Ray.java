package me.mgerdes.raytracer.Maths;

import me.mgerdes.raytracer.Maths.Vector3;

public class Ray {

    public Vector3 origin, direction;

    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vector3 pointAtTime(double t) {
        return Vector3.add(origin, Vector3.scale(direction, t));
    }

}
