package me.mgerdes.raytracer.Tracers;

import me.mgerdes.raytracer.Color.RGBColor;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.Utilities.HitInfo;
import me.mgerdes.raytracer.World.World;

public class WhittedTracer implements Tracer {

    private World world;
    private int maxDepth;

    public WhittedTracer(World world, int maxDepth) {
        this.world = world;
        this.maxDepth = maxDepth;
    }

    public RGBColor traceRay(Ray ray, int depth) {
        if (depth > maxDepth) {
            return new RGBColor(0,0,0);
        } else {
            HitInfo hitInfo = world.hitObjects(ray);
            if (hitInfo.isHit()) {
                hitInfo.setDepth(depth);
                hitInfo.setRay(ray);
                return hitInfo.getMaterial().shade(hitInfo);
            } else {
                return world.getBackgroundColor();
            }
        }
    }

}
