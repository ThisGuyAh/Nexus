package com.codex.nexus.math;

public class Vector4 {

    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4() {
        setZero();
    }

    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4(Vector4 other) {
        x = other.x;
        y = other.y;
        z = other.z;
        w = other.w;
    }

    public float getMagnitude() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public float getDot(Vector4 other) {
        return x * other.x + y * other.y + z * other.z + w * other.w;
    }

    public Vector4 setZero() {
        x = 0.0F;
        y = 0.0F;
        z = 0.0F;
        w = 0.0F;

        return this;
    }

    public Vector4 add(Vector4 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        w += other.w;

        return this;
    }

    public Vector4 subtract(Vector4 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        w -= other.w;

        return this;
    }

    public Vector4 cross(Vector4 other) {
        Vector3 truncatedVector = new Vector3(x, y, z).cross(new Vector3(other.x, other.y, other.z));

        x = truncatedVector.x;
        y = truncatedVector.y;
        z = truncatedVector.z;

        return this;
    }

    public Vector4 negate() {
        x = -x;
        y = -y;
        z = -z;
        w = -w;

        return this;
    }

    public Vector4 normalize() {
        float magnitude = getMagnitude();

        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
        w /= magnitude;

        return this;
    }

    public float[] toArray() {
        return new float[] { x, y, z, w };
    }

    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y + ", Z = " + z;
    }

}
