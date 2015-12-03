package me.mgerdes.raytracer.Tracers;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Utilities.HitInfo;
import me.mgerdes.raytracer.World.World;

public class PrimaryTracer implements Tracer {

    private World world;

    public PrimaryTracer(World w) {
        this.world = w;
    }

    public RGBColor traceRay(Ray r, int depth) {
        HitInfo h = world.hitObjects(r);

        if (h.isHit()) {
            return h.getMaterial().shade(h);
        } else {
            return world.getBackgroundColor();
        }
    }
}
