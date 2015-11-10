package me.mgerdes.raytracer.World;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.GeometricObjects.GeometricObject;
import me.mgerdes.raytracer.GeometricObjects.Plane;
import me.mgerdes.raytracer.GeometricObjects.Sphere;
import me.mgerdes.raytracer.Light.Light;
import me.mgerdes.raytracer.Light.PointLight;
import me.mgerdes.raytracer.Material.Material;
import me.mgerdes.raytracer.Material.Phong;
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
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i * i; j++) {
                int x, y, z;

                if (i == 1 && j == 1) {
                    x = 0;
                    y = 0;
                    z = -160;
                } else {
                    x = (int) (80 * i * Math.cos(2 * Math.PI * (j / ((double) i * i)))) + (int) (10*Math.random());
                    y = (int) (80 * i * Math.sin(2 * Math.PI * (j / ((double) i * i)))) + (int) (10*Math.random());
                    z = - i * 160 - (int) (80*Math.random());
                }

                int r = (int)(256*Math.random());
                int g = (int)(256*Math.random());
                int b = (int)(256*Math.random());

                Material m = new Phong(new RGBColor(r,g,b), 0.35, 0.50, 0.15, 5);

                objects.add(new Sphere(new Point(x, y, z), (160 / Math.sqrt(i)) + (int) (10*Math.random()), m));
            }
        }

        Plane p = new Plane(new Point(0,0,-1000), new Normal(0,0,1), new Phong(new RGBColor(30,30,30), 0.12, 0.6, 0.28, 5));
        objects.add(p);

        PointLight pointLight = new PointLight(new Point(800, 400, 700));
        lights.add(pointLight);
    }

    public void renderScene() throws FileNotFoundException, UnsupportedEncodingException {
        double z = 100.0;
        Vector rayDirection = new Vector(0, 0, -1);

        int width = 1500;
        int height = 1500;

        PrintWriter writer = new PrintWriter("image.ppm", "UTF-8");
        writer.printf("P3\n");
        writer.printf("%d %d\n", width, height);
        writer.printf("255\n");

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double x = (col - 0.5 * (width - 1.0));
                double y = (row - 0.5 * (height - 1.0));

                Ray r = new Ray(rayDirection, new Point(x, y, z));
                RGBColor color = getRaysHitColor(r);

                writer.printf("%d %d %d ", (int) color.r, (int) color.g, (int) color.b);
            }
            writer.printf("\n");
        }
    }

    private RGBColor getRaysHitColor(Ray r) {
        HitInfo h = traceRay(r);

        if (h.isHit()) {
            return h.getMaterial().shade(h);
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
                h.setWorld(this);
                minHit = h;
            }
        }

        return minHit;
    }
}
