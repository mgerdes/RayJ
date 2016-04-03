package me.mgerdes.raytracer.Hitables;

import me.mgerdes.raytracer.Hitables.HitRecord;
import me.mgerdes.raytracer.Maths.Ray;

import java.util.ArrayList;

public class Scene implements Hitable {

    private ArrayList<Hitable> hitables;

    public Scene() {
        this.hitables = new ArrayList<>();
    }

    public void addHitable(Hitable hitable) {
        this.hitables.add(hitable);
    }

    public boolean hit(Ray ray, HitRecord hitRecord) {
        double earliestHitTime = Double.MAX_VALUE;
        HitRecord earliestHitRecord = null;

        for (Hitable hitable : hitables) {
            HitRecord nextHitRecord = new HitRecord();
            if (hitable.hit(ray, nextHitRecord)) {
                if (nextHitRecord.t < earliestHitTime) {
                    earliestHitRecord = nextHitRecord;
                    earliestHitTime = earliestHitRecord.t;
                }
            }
        }

        if (earliestHitRecord == null) {
            return false;
        }

        hitRecord.setThis(earliestHitRecord);
        return true;
    }

}
