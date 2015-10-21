package me.mgerdes.raytracer;

import me.mgerdes.raytracer.World.World;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class RayTracer {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        World world = new World();
        world.buildScene();
        world.renderScene();
    }

}
