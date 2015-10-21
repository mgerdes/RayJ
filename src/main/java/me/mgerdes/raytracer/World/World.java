package me.mgerdes.raytracer.World;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.GeometricObjects.GeometricObject;
import me.mgerdes.raytracer.GeometricObjects.Plane;
import me.mgerdes.raytracer.GeometricObjects.Sphere;
import me.mgerdes.raytracer.Maths.Normal;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class World {
    private ArrayList<GeometricObject> objects;
    private RGBColor backgroundColor;

    public World() {
        objects = new ArrayList<>();
        backgroundColor = new RGBColor(0, 0, 0);
    }

    public void buildScene() {
        GeometricObject s = new Sphere(new Point(0,-25,0), 80, new RGBColor(100,100,100));
        GeometricObject s2 = new Sphere(new Point(0,30,0), 60, new RGBColor(100,0,100));
        GeometricObject p = new Plane(new Point(0,0,0), new Normal(0,1,1), new RGBColor(10,0,100));
        objects.add(s);
        objects.add(s2);
        objects.add(p);
    }

    public void renderScene() throws FileNotFoundException, UnsupportedEncodingException {
        double z = 100.0;
        Vector rayDirection = new Vector(0,0,-1);

        PrintWriter writer = new PrintWriter("image.ppm", "UTF-8");
        writer.printf("P3\n");
        writer.printf("400 400\n");
        writer.printf("255\n");

        for (int row = 0; row < 400; row++) {
            for (int col = 0; col < 400; col++) {
                double x = (col - 0.5 * (400 - 1.0));
                double y = (row - 0.5 * (400 - 1.0));

                Ray r = new Ray(rayDirection, new Point(x, y, z));
                RGBColor color = getRaysHitColor(r);

                writer.printf("%d %d %d ", (int)color.r, (int)color.g, (int)color.b);
            }
            writer.printf("\n");
        }
    }

    private RGBColor getRaysHitColor(Ray r) {
        HitInfo h = traceRay(r);

        if (h.isHit()) {
            return h.getColor();
        } else {
            return backgroundColor;
        }
    }

    private HitInfo traceRay(Ray r) {
        HitInfo minHit = new HitInfo(false);
        minHit.setTime(Double.MAX_VALUE);

        for (GeometricObject g : objects) {
            HitInfo h = g.hit(r);
            if (h.isHit() && h.getTime() < minHit.getTime()) {
                minHit =  h;
            }
        }

        return minHit;
    }
}
