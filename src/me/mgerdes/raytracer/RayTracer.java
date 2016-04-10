package me.mgerdes.raytracer;

import me.mgerdes.raytracer.Hitables.Hitable;
import me.mgerdes.raytracer.Hitables.HitRecord;
import me.mgerdes.raytracer.Hitables.Sphere;
import me.mgerdes.raytracer.Hitables.Scene;
import me.mgerdes.raytracer.Maths.Vector3;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Cameras.Camera;
import me.mgerdes.raytracer.Materials.Lambertian;
import me.mgerdes.raytracer.Materials.Metal;
import me.mgerdes.raytracer.Materials.Dielectric;

import java.util.ArrayList;

public class RayTracer {

    public static Scene scene = new Scene();

    public static Vector3 color(Ray ray, Scene scene, int depth) {
        HitRecord hitRecord = new HitRecord();

        if (scene.hit(ray, hitRecord)) {
            Vector3 attenuation = new Vector3();
            Ray scattered = new Ray(); 

            if (depth < 50 & hitRecord.material.scatter(ray, hitRecord, attenuation, scattered)) {
                Vector3 color = color(scattered, scene, depth + 1);
                color.scaleThis(attenuation);
                return color;
            }
            else {
                return new Vector3(0.0, 0.0, 0.0);
            }
        } 
        else {
            double t = 0.5 * (ray.direction.y + 1.0);

            Vector3 v1 = new Vector3(1.0, 1.0, 1.0);
            v1.scaleThis(1.0 - t);

            Vector3 v2 = new Vector3(0.5, 0.7, 1.0);
            v2.scaleThis(t);

            return Vector3.add(v1, v2);
        }
    }

    public static void buildScene() {
        scene.addHitable(new Sphere(new Vector3(0.0, 0.0, -1.0), 0.5, new Lambertian(new Vector3(0.1, 0.2, 0.5))));
        scene.addHitable(new Sphere(new Vector3(0.0, -100.5, -1.0), 100, new Lambertian(new Vector3(0.8, 0.8, 0.0))));
        scene.addHitable(new Sphere(new Vector3(1.0, 0.0, -1.0), 0.5, new Metal(new Vector3(0.8, 0.6, 0.2), 0.0)));
        scene.addHitable(new Sphere(new Vector3(-1.0, 0.0, -1.0), 0.5, new Dielectric(1.5)));
    }

    public static void main(String[] args) {
        buildScene();
        int width = 200;
        int height = 100;
        int numSamples = 100;

        System.out.printf("P3\n%d %d\n255\n", width, height); 

        Camera camera = new Camera();

        for (int j = height-1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                Vector3 color = new Vector3(0.0, 0.0, 0.0);
                for (int k = 0; k < numSamples; k++) {
                    double u = (i + Math.random()) / width; 
                    double v = (j + Math.random()) / height; 

                    Ray ray = camera.getRay(u, v);

                    color.addToThis(color(ray, scene, 0));
                }
                color.scaleThis(1.0/numSamples);
                color.setThis(Math.sqrt(color.x), Math.sqrt(color.y), Math.sqrt(color.z));

                int ir = (int) (255 * color.x);
                int ig = (int) (255 * color.y);
                int ib = (int) (255 * color.z);

                System.out.printf("%d %d %d\n", ir, ig, ib);
            }
        }
    }

}
