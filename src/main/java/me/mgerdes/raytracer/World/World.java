package me.mgerdes.raytracer.World;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.GeometricObjects.GeometricObject;
import me.mgerdes.raytracer.GeometricObjects.Plane;
import me.mgerdes.raytracer.GeometricObjects.Sphere;
import me.mgerdes.raytracer.Light.Light;
import me.mgerdes.raytracer.Light.PointLight;
import me.mgerdes.raytracer.Material.Material;
import me.mgerdes.raytracer.Material.Matte;
import me.mgerdes.raytracer.Maths.Normal;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Maths.Vector;
import me.mgerdes.raytracer.Utilities.HitInfo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class World {
    private List<GeometricObject> objects;
    private List<Light> lights;
    private RGBColor backgroundColor;

    public World() {
        objects = new ArrayList<>();
        lights = new ArrayList<>();
        backgroundColor = new RGBColor(0, 0, 0);
    }

    public List<GeometricObject> getObjects() {
        return objects;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void buildScene() {
        Material m1 = new Matte(new RGBColor(220, 80, 120), 0.20, 0.80);
        Sphere s1 = new Sphere(new Point(0,50,-105), 100, m1);
        objects.add(s1);

        Material m3 = new Matte(new RGBColor(220, 120, 80), 0.2, 0.8);
        Plane p3 = new Plane(new Point(0,-25,0), new Normal(0,1,0), m3);
        objects.add(p3);

        PointLight pointLight = new PointLight(new Point(200, 200, 200));
        lights.add(pointLight);
    }

    public void renderScene() throws FileNotFoundException, UnsupportedEncodingException {
        double z = 120.0;
        double e = 50.0;

        int width = 1000;
        int height = 1000;

        PrintWriter writer = new PrintWriter("image.ppm", "UTF-8");
        writer.printf("P3\n");
        writer.printf("%d %d\n", width, height);
        writer.printf("255\n");

        double lastPercent = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double percentDone = (row * col) / ((double) (width * height));
                if (percentDone > lastPercent) {
                    System.out.println(percentDone);
                    lastPercent = percentDone + 0.001;
                }

                double x = (col - 0.5 * (width - 1.0));
                double y = -(row - 0.5 * (height - 1.0));

                Vector rayDirection = new Vector(x, y, -z).hat();

                Ray r = new Ray(rayDirection, new Point(0, 0, e));
                RGBColor color = getRaysHitColor(r);

                writer.printf("%d %d %d ", (int) color.r, (int) color.g, (int) color.b);
            }
            writer.printf("\n");
        }
    }

    public RGBColor getRaysHitColor(Ray r) {
        HitInfo h = traceRay(r);
        if (h.isHit()) {
            return h.getMaterial().shade(h);
        } else {
            return backgroundColor;
        }
    }

    public HitInfo traceRay(Ray r) {
        HitInfo minHit = new HitInfo(false);
        minHit.setTime(Double.MAX_VALUE);

        for (GeometricObject g : objects) {
            HitInfo h = g.hit(r);
            if (h.isHit() && h.getTime() < minHit.getTime()) {
                h.setWorld(this);
                minHit = h;
            }
        }

        return minHit;
    }
}
