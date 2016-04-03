package me.mgerdes.raytracer.Cameras;

import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Maths.Ray;

public class Camera {

    Vector3 lowerLeftCorner;
    Vector3 horizontal;
    Vector3 vertical;
    Vector3 origin;

    public Camera() {
        this.lowerLeftCorner = new Vector3(-2.0, -1.0, -1.0);
        this.horizontal = new Vector3(4.0, 0.0, 0.0);
        this.vertical = new Vector3(0.0, 2.0, 0.0);
        this.origin = new Vector3(0.0, 0.0, 0.0);
    }

    public Ray getRay(double u, double v) {
        Vector3 direction = 
            Vector3.add(
                    lowerLeftCorner,
                    Vector3.add(
                        Vector3.scale(horizontal, u),
                        Vector3.scale(vertical, v)));
        direction.normalizeThis();

        return new Ray(origin, direction);
    }

}
