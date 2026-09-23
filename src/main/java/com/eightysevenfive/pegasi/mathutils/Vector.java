package com.eightysevenfive.pegasi.mathutils;

public class Vector {

    private float x;
    private float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector scalarMul(Vector v1, float scalar) {
        return new Vector(v1.x * scalar, v1.y * scalar);
    }

    public static Vector normalize(Vector v) {
        float value = (float) Math.sqrt(v.x * v.x + v.y * v.y);
        if(value == 0){
            return new Vector(0,0);
        }
        return new Vector(v.x / value, v.y / value);
    }

    public static float dot(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
