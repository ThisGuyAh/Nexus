package com.codex.nexus.math;

/**
 * A {@code Vector4} represents a 4-component vector.
 *
 * @author Christopher Ruley
 */
public class Vector4 extends Vector {

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
     * The w component.
     */
    public float w;

    /**
     * Constructs a {@code Vector4}.
     */
    public Vector4() {
        setZero();
    }

    /**
     * Constructs a {@code Vector4}.
     *
     * @param other the {@code Vector4} to copy from.
     */
    public Vector4(Vector4 other) {
        x = other.x;
        y = other.y;
        z = other.z;
        w = other.w;
    }

    /**
     * Constructs a {@code Vector4}.
     *
     * @param x the x component.
     * @param y the y component.
     * @param z the z component.
     * @param w the w component.
     */
    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getMagnitude() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * Gets the dot product of this {@code Vector3} and another.
     *
     * @param other the {@code Vector3} to compare from.
     * @return the dot product of this {@code Vector3} and another.
     */
    public float getDot(Vector4 other) {
        return x * other.x + y * other.y + z * other.z + w * other.w;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector4 setZero() {
        x = 0.0F;
        y = 0.0F;
        z = 0.0F;
        w = 0.0F;

        return this;
    }

    /**
     * Calculates the sum of this {@code Vector4} and another.
     *
     * @param other the {@code Vector4} to add.
     * @return this {@code Vector4}.
     */
    public Vector4 add(Vector4 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        w += other.w;

        return this;
    }

    /**
     * Calculates the sum of a {@code Vector4} and another.
     *
     * @param left        the left {@code Vector4} to add.
     * @param right       the right {@code Vector4} to add.
     * @param destination the {@code Vector4} to store the result in.
     * @return a {@code Vector3} containing the result.
     */
    public static Vector4 add(Vector4 left, Vector4 right, Vector4 destination) {
        if (destination == null) {
            destination = new Vector4();
        }

        destination.x = left.x + right.x;
        destination.y = left.y + right.y;
        destination.z = left.z + right.z;
        destination.w = left.w + right.w;

        return destination;
    }

    /**
     * Calculates the difference of this {@code Vector4} and another.
     *
     * @param other the {@code Vector4} to subtract.
     * @return this {@code Vector4}.
     */
    public Vector4 subtract(Vector4 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        w -= other.w;

        return this;
    }

    /**
     * Calculates the difference of a {@code Vector4} and another.
     *
     * @param left        the left {@code Vector4} to subtract.
     * @param right       the right {@code Vector4} to subtract.
     * @param destination the {@code Vector4} to store the result in.
     * @return a {@code Vector3} containing the result.
     */
    public static Vector4 subtract(Vector4 left, Vector4 right, Vector4 destination) {
        if (destination == null) {
            destination = new Vector4();
        }

        destination.x = left.x - right.x;
        destination.y = left.y - right.y;
        destination.z = left.z - right.z;
        destination.w = left.w - right.w;

        return destination;
    }

    /**
     * Calculates the cross product of this {@code Vector4} and another.
     *
     * @param other the {@code Vector4} to cross.
     * @return this {@code Vector4}.
     */
    public Vector4 cross(Vector4 other) {
        return cross(this, other, this);
    }

    /**
     * Calculates the cross product of a {@code Vector4} and another.
     *
     * @param left        the left {@code Vector4} to cross.
     * @param right       the right {@code Vector4} to cross.
     * @param destination the {@code Vector4} to store the result in.
     * @return a {@code Vector4} containing the result.
     */
    public static Vector4 cross(Vector4 left, Vector4 right, Vector4 destination) {
        if (destination == null) {
            destination = new Vector4();
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
    public Vector4 negate() {
        return negate(this, this);
    }

    /**
     * Negates a {@code Vector4}.
     *
     * @param source      the {@code Vector4} to negate.
     * @param destination the {@code Vector4} to store the result in.
     * @return a {@code Vector4} containing the result.
     */
    public static Vector4 negate(Vector4 source, Vector4 destination) {
        if (destination == null) {
            destination = new Vector4();
        }

        destination.x = -source.x;
        destination.y = -source.y;
        destination.z = -source.z;
        destination.w = -source.w;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector4 normalize() {
        return normalize(this, this);
    }

    /**
     * Normalizes a {@code Vector4}.
     *
     * @param source      the {@code Vector4} to normalize.
     * @param destination the {@code Vector4} to store the result in.
     * @return a {@code Vector4} containing the result.
     */
    public static Vector4 normalize(Vector4 source, Vector4 destination) {
        if (destination == null) {
            destination = new Vector4();
        }

        float magnitude = destination.getMagnitude();

        destination.x = source.x / magnitude;
        destination.y = source.y / magnitude;
        destination.z = source.z / magnitude;
        destination.w = source.w / magnitude;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float[] toArray() {
        return new float[] {x, y, z, w};
    }

    /**
     * Determines whether this {@code Vector4} is equal to another {@code Object}.
     *
     * @param other the {@code Object} to compare from.
     * @return whether this {@code Vector4} is equal to another {@code Object}.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector4 vector4) {
            return x == vector4.x && y == vector4.y && z == vector4.z && w == vector4.w;
        }

        return false;
    }

    /**
     * Gets a {@code String} representation of this {@code Vector4}.
     *
     * @return a {@code String} representation of this {@code Vector4}.
     */
    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y + ", Z = " + z + ", W = " + w;
    }

}
