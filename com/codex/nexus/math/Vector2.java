package com.codex.nexus.math;

/**
 * A {@code Vector2} represents a 2-component vector.
 *
 * @author Christopher Ruley
 */
public class Vector2 extends Vector {

    /**
     * The x component.
     */
    public float x;

    /**
     * The y component.
     */
    public float y;

    /**
     * Constructs a {@code Vector}.
     */
    public Vector2() {
        setZero();
    }

    /**
     * Constructs a {@code Vector}.
     *
     * @param other the {@code Vector2} to copy from.
     */
    public Vector2(Vector2 other) {
        x = other.x;
        y = other.y;
    }

    /**
     * Constructs a {@code Vector}.
     *
     * @param x the x component.
     * @param y the y component.
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getMagnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Gets the dot product of this {@code Vector2} and another.
     *
     * @param other the other {@code Vector2} to compare from.
     * @return the dot product of this {@code Vector2} and another.
     */
    public float getDot(Vector2 other) {
        return x * other.x + y * other.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 setZero() {
        x = 0.0F;
        y = 0.0F;

        return this;
    }

    /**
     * Calculates the sum of this {@code Vector2} and another.
     *
     * @param other the {@code Vector2} to add.
     * @return this {@code Vector2}.
     */
    public Vector2 add(Vector2 other) {
        return add(this, other, this);
    }

    /**
     * Calculates the sum of a {@code Vector2} and another.
     *
     * @param left        the left {@code Vector2} to add.
     * @param right       the right {@code Vector2} to add.
     * @param destination the {@code Vector2} to store the result in.
     * @return a {@code Vector2} containing the result.
     */
    public static Vector2 add(Vector2 left, Vector2 right, Vector2 destination) {
        if (destination == null) {
            destination = new Vector2();
        }

        destination.x = left.x + right.x;
        destination.y = left.y + right.y;

        return destination;
    }

    /**
     * Calculates the difference of this {@code Vector2} and another.
     *
     * @param other the {@code Vector2} to subtract.
     * @return this {@code Vector2}.
     */
    public Vector2 subtract(Vector2 other) {
        return subtract(this, other, this);
    }

    /**
     * Calculates the difference of a {@code Vector2} and another.
     *
     * @param left        the left {@code Vector2} to subtract.
     * @param right       the right {@code Vector2} to subtract.
     * @param destination the {@code Vector2} to store the result in.
     * @return a {@code Vector2} containing the result.
     */
    public static Vector2 subtract(Vector2 left, Vector2 right, Vector2 destination) {
        if (destination == null) {
            destination = new Vector2();
        }

        destination.x = left.x - right.x;
        destination.y = left.y - right.y;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 negate() {
        x = -x;
        y = -y;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 normalize() {
        float magnitude = getMagnitude();

        x /= magnitude;
        y /= magnitude;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float[] toArray() {
        return new float[] {x, y};
    }

    /**
     * Determines whether this {@code Vector2} is equal to another {@code Object}.
     *
     * @param other the {@code Object} to compare from.
     * @return whether this {@code Vector2} is equal to another {@code Object}.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector2 vector2) {
            return x == vector2.x && y == vector2.y;
        }

        return false;
    }

    /**
     * Gets a {@code String} representation of this {@code Vector2}.
     *
     * @return a {@code String} representation of this {@code Vector2}.
     */
    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y;
    }

}
