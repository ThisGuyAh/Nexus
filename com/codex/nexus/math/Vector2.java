package com.codex.nexus.math;

public class Vector2 {

    public float x;
    public float y;

    public Vector2() {
        setZero();
    }

    public Vector2(Vector2 other) {
        x = other.x;
        y = other.y;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getMagnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float getDot(Vector2 other) {
        return x * other.x + y * other.y;
    }

    public Vector2 setZero() {
        x = 0.0F;
        y = 0.0F;

        return this;
    }

    public Vector2 add(Vector3 other) {
        x += other.x;
        y += other.y;

        return this;
    }

    public Vector2 subtract(Vector3 other) {
        x -= other.x;
        y -= other.y;

        return this;
    }

    public Vector2 negate() {
        x = -x;
        y = -y;

        return this;
    }

    public Vector2 normalize() {
        float magnitude = getMagnitude();

        x /= magnitude;
        y /= magnitude;

        return this;
    }

    public float[] toArray() {
        return new float[] { x, y };
    }

}
