package me.mgerdes.raytracer;

import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Maths.Ray;

public class RayTracer {

    public static Vector3 color(Ray r) {
        Vector3 unitDirection = Vector3.normalOf(r.direction);
        double t = 0.5 * (unitDirection.y + 1.0);

        Vector3 v1 = new Vector3(1.0, 1.0, 1.0);
        v1.scaleThis(1.0 - t);

        Vector3 v2 = new Vector3(0.5, 0.7, 1.0);
        v2.scaleThis(t);

        return Vector3.add(v1, v2);
    }

    public static void main(String[] args) {
        int width = 200;
        int height = 100;

        System.out.printf("P3\n%d %d\n255\n", width, height); 

        Vector3 lowerLeft = new Vector3(-2.0, -1.0, -1.0);
        Vector3 horizontal = new Vector3(4.0, 0.0, 0.0);
        Vector3 vertical = new Vector3(0.0, 2.0, 0.0);
        Vector3 origin = new Vector3(0.0, 0.0, 0.0);

        for (int j = height-1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                double u = (double) i / width; 
                double v = (double) j / height; 

                Vector3 direction = 
                    Vector3.add(
                            lowerLeft,
                            Vector3.add(
                                Vector3.scale(horizontal, u),
                                Vector3.scale(vertical, v)));
                                
                Ray r = new Ray(origin, direction);

                Vector3 color = color(r);

                int ir = (int) (255 * color.x);
                int ig = (int) (255 * color.y);
                int ib = (int) (255 * color.z);

                System.out.printf("%d %d %d\n", ir, ig, ib);
            }
        }
    }

}
