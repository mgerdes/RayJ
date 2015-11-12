package me.mgerdes.raytracer.MeshReaders;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.GeometricObjects.Triangle;
import me.mgerdes.raytracer.GeometricObjects.TriangleMesh;
import me.mgerdes.raytracer.Material.Matte;
import me.mgerdes.raytracer.Maths.Normal;
import me.mgerdes.raytracer.Maths.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WavefrontOBJReader {

    private ArrayList<Point> vertices;
    private ArrayList<Normal> normals;
    private TriangleMesh triangleMesh;

    public WavefrontOBJReader(String filename) {
        try {
            readOBJFileFromScanner(new Scanner(new File(filename)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TriangleMesh getTriangleMesh() {
        return triangleMesh;
    }

    private void readOBJFileFromScanner(Scanner scanner) {
        RGBColor color = new RGBColor(200, 30, 50);
        Matte matte = new Matte(color, 0.2, 0.8);

        vertices = new ArrayList<>();
        normals = new ArrayList<>();
        triangleMesh = new TriangleMesh();

        while (scanner.hasNext()) {
            String label = scanner.next();
            if (label.equals("v")) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                double z = scanner.nextDouble();

                double scale = 100;

                vertices.add(new Point(x * scale, y * scale, z * scale));
            } else if (label.equals("f")) {
                int v1 = scanner.nextInt() - 1;
                int v2 = scanner.nextInt() - 1;
                int v3 = scanner.nextInt() - 1;

                triangleMesh.addTriangle(new Triangle(vertices.get(v1), vertices.get(v2), vertices.get(v3), matte));
            }
        }
    }

}
