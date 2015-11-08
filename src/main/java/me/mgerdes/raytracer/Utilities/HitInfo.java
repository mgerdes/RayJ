package me.mgerdes.raytracer.Utilities;

import me.mgerdes.raytracer.Material.Material;
import me.mgerdes.raytracer.Maths.Normal;
import me.mgerdes.raytracer.Maths.Point;
import me.mgerdes.raytracer.Maths.Ray;
import me.mgerdes.raytracer.World.World;

public class HitInfo {
    private Ray ray;
    private Point hitPoint;
    private World world;
    private Material material;
    private Normal normal;
    private double time;
    private boolean isHit;

    public HitInfo(boolean isHit) {
        this.isHit = isHit;
    }

    public Ray getRay() {
        return ray;
    }

    public void setRay(Ray ray) {
        this.ray = ray;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHitPoint(Point h) {
        this.hitPoint = h;
    }

    public Point getHitPoint() {
        return hitPoint;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    public Material getMaterial() {
        return material;
    }

    public Normal getNormal() {
        return normal;
    }
}
