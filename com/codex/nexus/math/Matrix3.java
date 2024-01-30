package com.codex.nexus.math;

/**
 * A {@code Matrix3} represents a 3X3 matrix in column major ordering.
 *
 * @author Christopher Ruley
 */
public class Matrix3 extends Matrix {

    /**
     * The first element in the first column.
     */
    public float element00;

    /**
     * The second element in the first column.
     */
    public float element01;

    /**
     * The third element in the first column.
     */
    public float element02;

    /**
     * The first element in the second column.
     */
    public float element10;

    /**
     * The second element in the second column.
     */
    public float element11;

    /**
     * The third element in the second column.
     */
    public float element12;

    /**
     * The first element in the third column.
     */
    public float element20;

    /**
     * The second element in the third column.
     */
    public float element21;

    /**
     * The third element in the third column.
     */
    public float element22;

    /**
     * Constructs a {@code Matrix3}.
     */
    public Matrix3() {
        setIdentity();
    }

    /**
     * Constructs a {@code Matrix3}.
     *
     * @param other the {@code Matrix3} to copy from.
     */
    public Matrix3(Matrix3 other) {
        element00 = other.element00;
        element01 = other.element01;
        element02 = other.element02;
        element10 = other.element10;
        element11 = other.element11;
        element12 = other.element12;
        element20 = other.element20;
        element21 = other.element21;
        element22 = other.element22;
    }

    /**
     * Constructs a {@code Matrix3}.
     *
     * @param element00 the first element in the first column.
     * @param element01 the second element in the first column.
     * @param element02 the third element in the first column.
     * @param element10 the first element in the second column.
     * @param element11 the second element in the second column.
     * @param element12 the third element in the second column.
     * @param element20 the first element in the third column.
     * @param element21 the second element in the third column.
     * @param element22 the third element in the third column.
     */
    public Matrix3(float element00, float element01, float element02, float element10, float element11, float element12,
                   float element20, float element21, float element22) {
        this.element00 = element00;
        this.element01 = element01;
        this.element02 = element02;
        this.element10 = element10;
        this.element11 = element11;
        this.element12 = element12;
        this.element20 = element20;
        this.element21 = element21;
        this.element22 = element22;
    }

    /**
     * Gets the determinant.
     *
     * @return the determinant.
     */
    public float getDeterminant() {
        return element00 * (element11 * element22 - element12 * element21)
            + element01 * (element12 * element20 - element10 * element22)
            + element02 * (element10 * element21 - element11 * element20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix3 setZero() {
        element00 = 0.0F;
        element01 = 0.0F;
        element02 = 0.0F;
        element10 = 0.0F;
        element11 = 0.0F;
        element12 = 0.0F;
        element20 = 0.0F;
        element21 = 0.0F;
        element22 = 0.0F;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix3 setIdentity() {
        element00 = 1.0F;
        element01 = 0.0F;
        element02 = 0.0F;
        element10 = 0.0F;
        element11 = 1.0F;
        element12 = 0.0F;
        element20 = 0.0F;
        element21 = 0.0F;
        element22 = 1.0F;

        return this;
    }

    /**
     * Calculates the sum of this {@code Matrix3} and another.
     *
     * @param other the {@code Matrix3} to add.
     * @return this {@code Matrix3}.
     */
    public Matrix3 add(Matrix3 other) {
        return add(this, other, this);
    }

    /**
     * Calculates the sum of a {@code Matrix3} and another.
     *
     * @param left        the left {@code Matrix3} to add.
     * @param right       the right {@code Matrix3} to add.
     * @param destination the {@code Matrix3} to store the result in.
     * @return a {@code Matrix3} containing the result.
     */
    public static Matrix3 add(Matrix3 left, Matrix3 right, Matrix3 destination) {
        if (destination == null) {
            destination = new Matrix3();
        }

        destination.element00 = left.element00 + right.element00;
        destination.element01 = left.element01 + right.element01;
        destination.element02 = left.element02 + right.element02;
        destination.element10 = left.element10 + right.element10;
        destination.element11 = left.element11 + right.element11;
        destination.element12 = left.element12 + right.element12;
        destination.element20 = left.element20 + right.element20;
        destination.element21 = left.element21 + right.element21;
        destination.element22 = left.element22 + right.element22;

        return destination;
    }

    /**
     * Calculates the difference of this {@code Matrix3} and another.
     *
     * @param other the {@code Matrix3} to subtract.
     * @return this {@code Matrix3}.
     */
    public Matrix3 subtract(Matrix3 other) {
        return subtract(this, other, this);
    }

    /**
     * Calculates the difference of a {@code Matrix3} and another.
     *
     * @param left        the left {@code Matrix3} to subtract.
     * @param right       the right {@code Matrix3} to subtract.
     * @param destination the {@code Matrix3} to store the result in.
     * @return a {@code Matrix3} containing the result.
     */
    public static Matrix3 subtract(Matrix3 left, Matrix3 right, Matrix3 destination) {
        if (destination == null) {
            destination = new Matrix3();
        }

        destination.element00 = left.element00 - right.element00;
        destination.element01 = left.element01 - right.element01;
        destination.element02 = left.element02 - right.element02;
        destination.element10 = left.element10 - right.element10;
        destination.element11 = left.element11 - right.element11;
        destination.element12 = left.element12 - right.element12;
        destination.element20 = left.element20 - right.element20;
        destination.element21 = left.element21 - right.element21;
        destination.element22 = left.element22 - right.element22;

        return destination;
    }

    /**
     * Calculates the product of this {@code Matrix3} and another.
     *
     * @param other the {@code Matrix3} to multiply by.
     * @return this {@code Matrix3}.
     */
    public Matrix3 multiply(Matrix3 other) {
        return multiply(this, other, this);
    }

    /**
     * Calculates the product of a {@code Matrix3} and another.
     *
     * @param left        the left {@code Matrix3} to multiply by.
     * @param right       the right {@code Matrix3} to multiply by.
     * @param destination the {@code Matrix3} to store the result in.
     * @return a {@code Matrix3} containing the result.
     */
    public static Matrix3 multiply(Matrix3 left, Matrix3 right, Matrix3 destination) {
        if (destination == null) {
            destination = new Matrix3();
        }

        destination.element00 = left.element00 * right.element00 + left.element10 * right.element01 + left.element20
            * right.element02;
        destination.element01 = left.element01 * right.element00 + left.element11 * right.element01 + left.element21
            * right.element02;
        destination.element02 = left.element02 * right.element00 + left.element12 * right.element01 + left.element22
            * right.element02;
        destination.element10 = left.element00 * right.element10 + left.element10 * right.element11 + left.element20
            * right.element12;
        destination.element11 = left.element01 * right.element10 + left.element11 * right.element11 + left.element21
            * right.element12;
        destination.element12 = left.element02 * right.element10 + left.element12 * right.element11 + left.element22
            * right.element12;
        destination.element20 = left.element00 * right.element20 + left.element10 * right.element21 + left.element20
            * right.element22;
        destination.element21 = left.element01 * right.element20 + left.element11 * right.element21 + left.element21
            * right.element22;
        destination.element22 = left.element02 * right.element20 + left.element12 * right.element21 + left.element22
            * right.element22;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix3 negate() {
        return negate(this, this);
    }

    /**
     * Negates a {@code Matrix3}.
     *
     * @param source      the {@code Matrix3} to negate.
     * @param destination the {@code Matrix3} to store the result in.
     * @return a {@code Matrix3} containing the result.
     */
    public static Matrix3 negate(Matrix3 source, Matrix3 destination) {
        if (destination == null) {
            destination = new Matrix3();
        }
        destination.element00 = -source.element00;
        destination.element01 = -source.element01;
        destination.element02 = -source.element02;
        destination.element10 = -source.element10;
        destination.element11 = -source.element11;
        destination.element12 = -source.element12;
        destination.element20 = -source.element20;
        destination.element21 = -source.element21;
        destination.element22 = -source.element22;

        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix3 invert() {
        return invert(this, this);
    }

    /**
     * Inverts a {@code Matrix3}.
     *
     * @param source      the {@code Matrix3} to invert.
     * @param destination the {@code Matrix3} to store the result in.
     * @return a {@code Matrix3} containing the result.
     */
    public static Matrix3 invert(Matrix3 source, Matrix3 destination) {
        if (destination == null) {
            destination = new Matrix3();
        }

        float determinant = source.getDeterminant();

        if (determinant != 0) {
            float inverseDeterminant = 1.0F / determinant;

            float value00 = (source.element11 * source.element22 - source.element12 * source.element21)
                * inverseDeterminant;
            float value01 = (-source.element10 * source.element22 + source.element12 * source.element20)
                * inverseDeterminant;
            float value02 = (source.element10 * source.element21 - source.element11 * source.element20)
                * inverseDeterminant;
            float value10 = (-source.element01 * source.element22 + source.element02 * source.element21)
                * inverseDeterminant;
            float value11 = (source.element00 * source.element22 - source.element02 * source.element20)
                * inverseDeterminant;
            float value12 = (-source.element00 * source.element21 + source.element01 * source.element20)
                * inverseDeterminant;
            float value20 = (source.element01 * source.element12 - source.element02 * source.element11)
                * inverseDeterminant;
            float value21 = (-source.element00 * source.element12 + source.element02 * source.element10)
                * inverseDeterminant;
            float value22 = (source.element00 * source.element11 - source.element01 * source.element10)
                * inverseDeterminant;

            destination.element00 = value00;
            destination.element11 = value01;
            destination.element22 = value02;
            destination.element01 = value10;
            destination.element10 = value11;
            destination.element20 = value12;
            destination.element02 = value20;
            destination.element12 = value21;
            destination.element21 = value22;
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
            element02,
            element10,
            element11,
            element12,
            element20,
            element21,
            element22
        };
    }

    /**
     * Gets a {@code String} representation of this {@code Matrix3}.
     *
     * @return a {@code String} representation of this {@code Matrix3}.
     */
    @Override
    public String toString() {
        String row1 = "Row 1: [" + element00 + ", " + element10 + ", " + element20 + "]";
        String row2 = "Row 2: [" + element01 + ", " + element11 + ", " + element21 + "]";
        String row3 = "Row 3: [" + element02 + ", " + element12 + ", " + element22 + "]";

        return row1 + "\n" + row2 + "\n" + row3;
    }

}
