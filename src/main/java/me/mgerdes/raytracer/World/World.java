package me.mgerdes.raytracer.World;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.GeometricObjects.GeometricObject;
import me.mgerdes.raytracer.GeometricObjects.Plane;
import me.mgerdes.raytracer.GeometricObjects.Sphere;
import me.mgerdes.raytracer.Light.Ambient;
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
        Material sM = new Matte(new RGBColor(100, 55, 200), 0.8);
        Material s2M = new Matte(new RGBColor(0, 80, 100), 0.8);
        Material pM = new Matte(new RGBColor(0, 200, 0), 0.8);

        GeometricObject s = new Sphere(new Point(0,-25,0), 80, sM);
        GeometricObject s2 = new Sphere(new Point(0,30,0), 60, s2M);
        GeometricObject p = new Plane(new Point(0,0,0), new Normal(0,1,1), pM);
        objects.add(s);
        objects.add(s2);
        objects.add(p);

        Ambient l = new Ambient(new RGBColor(0.2, 0.2, 0.2), 0.8);
        PointLight pointLight = new PointLight(new RGBColor(0.8, 0.8, 0.8), new Point(100, 100, 100), 0.8);
        lights.add(l);
        lights.add(pointLight);
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
                minHit =  h;
            }
        }

        return minHit;
    }
}
