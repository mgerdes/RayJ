package me.mgerdes.raytracer.Samplers;

import me.mgerdes.raytracer.Maths.Point;

public class Hemisphere implements Sampler {

    private int sampleSize;

    public Hemisphere(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public Point[] getSamplePoints() {
        int sqrt = (int) Math.sqrt(sampleSize);

        Point[] points = new Point[sampleSize];

        int num = 0;
        for (int i = 0; i < sqrt; i++) {
            double angle = (i / sqrt) * 2 * Math.PI;
            for (int j = 0; j < sqrt; j++) {
                double z = j / sqrt;
                double x = Math.cos(angle) * z;
                double y = Math.sin(angle) * z;

                points[num] = new Point(x,y,z);
                num++;
            }
        }

        return points;
    }

}
