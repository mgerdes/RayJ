package me.mgerdes.raytracer.Maths;

public class Vector3 {

    public double x, y, z; 

    public Vector3() {
        this(0, 0, 0);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void scaleThis(Vector3 v) {
        this.scaleThis(v.x, v.y, v.z);
    }

    public void scaleThis(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
    }

    public void scaleThis(double s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
    }

    public void normalizeThis() {
        double l = this.length();
        if (l == 0) {
            return;
        }
        this.x /= l;
        this.y /= l;
        this.z /= l;
    }

    public void setThis(Vector3 v) {
        this.setThis(v.x, v.y, v.z);
    }

    public void setThis(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void addToThis(Vector3 v) {
        this.addToThis(v.x, v.y, v.z);
    }

    public void addToThis(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public static double dot(Vector3 v1, Vector3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vector3 add(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vector3 sub(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
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
