package me.mgerdes.raytracer.Maths;

public class Vector3 {

    public double x, y, z; 

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void scaleThis(double s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
    }

    public static Vector3 add(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x + v1.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vector3 scale(Vector3 v, double s) {
        return new Vector3(s * v.x, s * v.y, s * v.z);
    }

    public static Vector3 normalOf(Vector3 v) {
        double l = v.length();
        if (l == 0) {
            return new Vector3(0, 0, 0);
        }
        return new Vector3(v.x / l, v.y / l, v.z / l);
    }

}
