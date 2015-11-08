package me.mgerdes.raytracer.Light;

import me.mgerdes.raytracer.Maths.Point;

public class PointLight implements Light {
    private Point location;

    public PointLight(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
