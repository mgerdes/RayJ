package me.mgerdes.raytracer.World;

public class ViewPlane {
    private double horizontalResolution;
    private double verticalResolution;
    private double sizeOfPixel;

    public ViewPlane(double horizontalResolution, double verticalResolution, double sizeOfPixel) {
        this.horizontalResolution = horizontalResolution;
        this.verticalResolution = verticalResolution;
        this.sizeOfPixel = sizeOfPixel;
    }
}
