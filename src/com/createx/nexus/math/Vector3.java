package com.createx.nexus.math;

/**
 * A {@code Vector3} represents a 3-component vector.
 *
 * @author Christopher Ruley
 */
public class Vector3 extends Vector {

    /**
     * The x component.
     */
    public float x;

    /**
     * The y component.
     */
    public float y;

    /**
     * The z component.
     */
    public float z;

    /**
     * Constructs a {@code Vector3}.
     */
    public Vector3() {
        setZero();
    }

    /**
     * Constructs a {@code Vector3}.
     *
     * @param other the {@code Vector3} to copy from.
     */
    public Vector3(Vector3 other) {
        x = other.x;
        y = other.y;
        z = other.z;
    }

    /**
     * Constructs a {@code Vector3}.
     *
     * @param x the x component.
     * @param y the y component.
     * @param z the z component.
     */
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getMagnitude() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Gets the dot product of this {@code Vector3} and another.
     *
     * @param other the {@code Vector3} to compare from.
     * @return the dot product of this {@code Vector3} and another.
     */
    public float getDot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3 setZero() {
        x = 0.0F;
        y = 0.0F;
        z = 0.0F;

        return this;
    }

    /**
     * Calculates the sum of this {@code Vector3} and another.
     *
     * @param other the {@code Vector3} to add.
     * @return this {@code Vector3}.
     */
    public Vector3 add(Vector3 other) {
        return add(this, other, this);
    }

    /**
     * Calculates the sum of a {@code Vector3} and another.
     *
     * @param left        the left {@code Vector3} to add.
     * @param right       the right {@code Vector3} to add.
     * @param destination the {@code Vector3} to store the result in.
     * @return a {@code Vector3} containing the result.
     */
    public static Vector3 add(Vector3 left, Vector3 right, Vector3 destination) {
        if (destination == null) {
            destination = new Vector3();
        }

        destination.x = left.x + right.x;
        destination.y = left.y + right.y;
        destination.z = left.z + right.z;

        return destination;
    }

    /**
     * Calculates the difference of this {@code Vector3} and another.
     *
     * @param other the {@code Vector3} to subtract.
     * @return this {@code Vector3}.
     */
    public Vector3 subtract(Vector3 other) {
        return subtract(this, other, this);
    }

    /**
     * Calculates the difference of a {@code Vector3} and another.
     *
     * @param left        the left {@code Vector3} to subtract.
     * @param right       the right {@code Vector3} to subtract.
     * @param destination the {@code Vector3} to store the result in.
     * @return a {@code Vector3} containing the result.
     */
    public static Vector3 subtract(Vector3 left, Vector3 right, Vector3 destination) {
        if (destination == null) {
            destination = new Vector3();
        }

        destination.x = left.x - right.x;
        destination.y = left.y - right.y;
        destination.z = left.z - right.z;

        return destination;
    }

    /**
     * Calculates the cross product of this {@code Vector3} and another.
     *
     * @param other the {@code Vector3} to cross.
     * @return this {@code Vector3}.
     */
    public Vector3 cross(Vector3 other) {
        return cross(this, other, this);
    }

    /**
     * Calculates the cross product of a {@code Vector3} and another.
     *
     * @param left        the left {@code Vector3} to cross.
     * @param right       the right {@code Vector3} to cross.
     * @param destination the {@code Vector3} to store the result in.
     * @return a {@code Vector3} containing the result.
     */
    public static Vector3 cross(Vector3 left, Vector3 right, Vector3 destination) {
        if (destination == null) {
            destination = new Vector3();
        }

        destination.x = left.y * right.z - left.z * right.y;
        destination.y = right.x * left.z - right.z * left.x;
        destination.z = left.x * right.y - left.y * right.x;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3 negate() {
        return negate(this, this);
    }

    /**
     * Negates a {@code Vector3}.
     *
     * @param source      the {@code Vector3} to negate.
     * @param destination the {@code Vector3} to store the result in.
     * @return a {@code Vector3} containing the result.
     */
    public static Vector3 negate(Vector3 source, Vector3 destination) {
        if (destination == null) {
            destination = new Vector3();
        }

        destination.x = -source.x;
        destination.y = -source.y;
        destination.z = -source.z;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector3 normalize() {
        return normalize(this, this);
    }

    /**
     * Normalizes a {@code Vector3}.
     *
     * @param source      the {@code Vector3} to normalize.
     * @param destination the {@code Vector3} to store the result in.
     * @return a {@code Vector3} containing the result.
     */
    public static Vector3 normalize(Vector3 source, Vector3 destination) {
        if (destination == null) {
            destination = new Vector3();
        }

        float magnitude = destination.getMagnitude();

        destination.x = source.x / magnitude;
        destination.y = source.y / magnitude;
        destination.z = source.z / magnitude;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float[] toArray() {
        return new float[] {x, y, z};
    }

    /**
     * Determines whether this {@code Vector3} is equal to another {@code Object}.
     *
     * @param other the {@code Object} to compare from.
     * @return whether this {@code Vector3} is equal to another {@code Object}.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector3 vector3) {
            return x == vector3.x && y == vector3.y && z == vector3.z;
        }

        return false;
    }

    /**
     * Gets a {@code String} representation of this {@code Vector3}.
     *
     * @return a {@code String} representation of this {@code Vector3}.
     */
    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y + ", Z = " + z;
    }

}
