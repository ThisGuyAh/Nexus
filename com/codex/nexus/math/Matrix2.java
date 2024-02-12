package com.codex.nexus.math;

/**
 * A {@code Matrix2} represents a 2X2 element matrix in column major ordering.
 *
 * @author Christopher Ruley
 */
public class Matrix2 extends Matrix {

    /**
     * The first element in the first column.
     */
    public float element00;

    /**
     * The second element in the first column.
     */
    public float element01;

    /**
     * The first element in the second column.
     */
    public float element10;

    /**
     * The second element in the second column.
     */
    public float element11;

    /**
     * Constructs a {@code Matrix2}.
     */
    public Matrix2() {
        setIdentity();
    }

    /**
     * Constructs a {@code Matrix2}.
     *
     * @param other the {@code Matrix2} to copy from.
     */
    public Matrix2(Matrix2 other) {
        element00 = other.element00;
        element01 = other.element01;
        element10 = other.element10;
        element11 = other.element11;
    }

    /**
     * Constructs a {@code Matrix2}.
     *
     * @param element00 the first element in the first column.
     * @param element01 the second element in the first column.
     * @param element10 the first element in the second column.
     * @param element11 the second element in the second column.
     */
    public Matrix2(float element00, float element01, float element10, float element11) {
        this.element00 = element00;
        this.element01 = element01;
        this.element10 = element10;
        this.element11 = element11;
    }

    /**
     * Gets the determinant.
     *
     * @return the determinant.
     */
    public float getDeterminant() {
        return element00 * element11 - element01 * element10;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix2 setZero() {
        element00 = 0.0F;
        element01 = 0.0F;
        element10 = 0.0F;
        element11 = 0.0F;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix2 setIdentity() {
        element00 = 1.0F;
        element01 = 0.0F;
        element10 = 0.0F;
        element11 = 1.0F;

        return this;
    }

    /**
     * Calculates the sum of this {@code Matrix2} and another.
     *
     * @param other the {@code Matrix2} to add.
     * @return this {@code Matrix2}.
     */
    public Matrix2 add(Matrix2 other) {
        return add(this, other, this);
    }

    /**
     * Calculates the sum of a {@code Matrix2} and another.
     *
     * @param left        the left {@code Matrix2} to add.
     * @param right       the right {@code Matrix2} to add.
     * @param destination the {@code Matrix2} to store the result in.
     * @return a {@code Matrix2} containing the result.
     */
    public static Matrix2 add(Matrix2 left, Matrix2 right, Matrix2 destination) {
        if (destination == null) {
            destination = new Matrix2();
        }

        destination.element00 = left.element00 + right.element00;
        destination.element01 = left.element01 + right.element01;
        destination.element10 = left.element10 + right.element10;
        destination.element11 = left.element11 + right.element11;

        return destination;
    }

    /**
     * Calculates the difference of this {@code Matrix2} and another.
     *
     * @param other the {@code Matrix2} to subtract.
     * @return this {@code Matrix2}.
     */
    public Matrix2 subtract(Matrix2 other) {
        return subtract(this, other, this);
    }

    /**
     * Calculates the difference of a {@code Matrix2} and another.
     *
     * @param left        the left {@code Matrix2} to subtract.
     * @param right       the right {@code Matrix2} to subtract.
     * @param destination the {@code Matrix2} to store the result in.
     * @return a {@code Matrix2} containing the result.
     */
    public static Matrix2 subtract(Matrix2 left, Matrix2 right, Matrix2 destination) {
        if (destination == null) {
            destination = new Matrix2();
        }

        destination.element00 = left.element00 - right.element00;
        destination.element01 = left.element01 - right.element01;
        destination.element10 = left.element10 - right.element10;
        destination.element11 = left.element11 - right.element11;

        return destination;
    }

    /**
     * Calculates the product of this {@code Matrix2} and another.
     *
     * @param other the {@code Matrix2} to multiply by.
     * @return this {@code Matrix2}.
     */
    public Matrix2 multiply(Matrix2 other) {
        return multiply(this, other, this);
    }

    /**
     * Calculates the product of a {@code Matrix2} and another.
     *
     * @param left        the left {@code Matrix2} to multiply by.
     * @param right       the right {@code Matrix2} to multiply by.
     * @param destination the {@code Matrix2} to store the result in.
     * @return a {@code Matrix2} containing the result.
     */
    public static Matrix2 multiply(Matrix2 left, Matrix2 right, Matrix2 destination) {
        if (destination == null) {
            destination = new Matrix2();
        }

        destination.element00 = left.element00 * right.element00 + left.element10 * right.element01;
        destination.element01 = left.element01 * right.element00 + left.element11 * right.element01;
        destination.element10 = left.element00 * right.element10 + left.element10 * right.element11;
        destination.element11 = left.element01 * right.element10 + left.element11 * right.element11;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix2 negate() {
        element00 = -element00;
        element01 = -element01;
        element10 = -element10;
        element11 = -element11;

        return this;
    }

    /**
     * Negates a {@code Matrix2}.
     *
     * @param source      the {@code Matrix2} to negate.
     * @param destination the {@code Matrix2} to store the result in.
     * @return a {@code Matrix2} containing the result.
     */
    public static Matrix2 negate(Matrix2 source, Matrix2 destination) {
        if (destination == null) {
            destination = new Matrix2();
        }

        destination.element00 = -source.element00;
        destination.element01 = -source.element01;
        destination.element10 = -source.element10;
        destination.element11 = -source.element11;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix2 invert() {
        return invert(this, this);
    }

    /**
     * Inverts a {@code Matrix2}.
     *
     * @param source      the {@code Matrix2} to invert.
     * @param destination the {@code Matrix2} to store the result in.
     * @return a {@code Matrix2} containing the result.
     */
    public static Matrix2 invert(Matrix2 source, Matrix2 destination) {
        if (destination == null) {
            destination = new Matrix2();
        }

        float determinant = source.getDeterminant();

        if (determinant != 0) {
            float inverseDeterminant = 1.0F / determinant;
            float value00 = source.element11 * inverseDeterminant;
            float value01 = -source.element01 * inverseDeterminant;
            float value10 = -source.element10 * inverseDeterminant;
            float value11 = source.element00 * inverseDeterminant;

            destination.element00 = value00;
            destination.element01 = value01;
            destination.element10 = value10;
            destination.element11 = value11;
        }

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float[] toArray() {
        return new float[] {
            element00,
            element01,
            element10,
            element11
        };
    }

    /**
     * Gets a {@code String} representation of this {@code Matrix2}.
     *
     * @return a {@code String} representation of this {@code Matrix2}.
     */
    @Override
    public String toString() {
        String row1 = "Row 1: [" + element00 + ", " + element10 + "]";
        String row2 = "Row 2: [" + element01 + ", " + element11 + "]";

        return row1 + "\n" + row2;
    }

}
