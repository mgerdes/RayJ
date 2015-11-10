package me.mgerdes.raytracer.Samplers;

import me.mgerdes.raytracer.Maths.Point;

public class HemisphereSampler implements Sampler {

    private int sampleSize;
    private Point[] samplePoints;

    public HemisphereSampler(int sampleSize) {
        this.sampleSize = sampleSize;
        createSamplePoints();
    }

    private void createSamplePoints() {
        int sqrt = (int) Math.sqrt(sampleSize);

        Point[] points = new Point[sampleSize];

        int num = 0;
        for (int i = 0; i < sqrt; i++) {
            double theta = ((double) i / sqrt) * 2 * Math.PI;
            for (int j = 0; j < sqrt; j++) {
                double phi = ((double) j / sqrt) * Math.PI / 2;

                double sin_theta = Math.sin(theta);
                double cos_theta = Math.cos(theta);
                double sin_phi = Math.sin(phi);
                double cos_phi = Math.cos(phi);

                double x = sin_theta * cos_phi;
                double y = sin_theta * sin_phi;
                double z = cos_theta;

                points[num] = new Point(x,y,z);
                num++;
            }
        }

        this.samplePoints = points;
    }

    public Point[] getSamplePoints() {
        return samplePoints;
    }

}
