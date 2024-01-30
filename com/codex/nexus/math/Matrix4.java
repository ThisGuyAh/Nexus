package com.codex.nexus.math;

/**
 * A {@code Matrix4} represents a 4X4 element matrix in column major ordering.
 *
 * @author Christopher Ruley
 */
public class Matrix4 extends Matrix {

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
     * The fourth element in the first column.
     */
    public float element03;

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
     * The fourth element in the second column.
     */
    public float element13;

    /**
     * The firth element in the third column.
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
     * The fourth element in the third column.
     */
    public float element23;

    /**
     * The first element in the fourth column.
     */
    public float element30;

    /**
     * The second element in the fourth column.
     */
    public float element31;

    /**
     * The third element in the fourth column.
     */
    public float element32;

    /**
     * The fourth element in the fourth column.
     */
    public float element33;

    /**
     * Constructs a {@code Matrix4}.
     */
    public Matrix4() {
        setIdentity();
    }

    /**
     * Constructs a {@code Matrix4}.
     *
     * @param other the {@code Matrix4} to copy from.
     */
    public Matrix4(Matrix4 other) {
        element00 = other.element00;
        element01 = other.element01;
        element02 = other.element02;
        element03 = other.element03;
        element10 = other.element10;
        element11 = other.element11;
        element12 = other.element12;
        element13 = other.element13;
        element20 = other.element20;
        element21 = other.element21;
        element22 = other.element22;
        element23 = other.element23;
        element30 = other.element30;
        element31 = other.element31;
        element32 = other.element32;
        element33 = other.element33;
    }

    /**
     * Constructs a {@code Matrix4}.
     * 
     * @param element00 the first element in the first column.
     * @param element01 the second element in the first column.
     * @param element02 the third element in the first column.
     * @param element03 the fourth element in the first column.
     * @param element10 the first element in the second column.
     * @param element11 the second element in the second column.
     * @param element12 the third element in the second column.
     * @param element13 the fourth element in the second column.
     * @param element20 the first element in the third column.
     * @param element21 the second element in the third column.
     * @param element22 the third element in the third column.
     * @param element23 the fourth element in the third column.
     * @param element30 the first element in the fourth column.
     * @param element31 the second element in the fourth column.
     * @param element32 the third element in the fourth column.
     * @param element33 the fourth element in the fourth column.
     */
    public Matrix4(float element00, float element01, float element02, float element03, float element10, float element11, 
                   float element12, float element13, float element20, float element21, float element22, float element23,
                   float element30, float element31, float element32, float element33) {
        this.element00 = element00;
        this.element01 = element01;
        this.element02 = element02;
        this.element03 = element03;
        this.element10 = element10;
        this.element11 = element11;
        this.element12 = element12;
        this.element13 = element13;
        this.element20 = element20;
        this.element21 = element21;
        this.element22 = element22;
        this.element23 = element23;
        this.element30 = element30;
        this.element31 = element31;
        this.element32 = element32;
        this.element33 = element33;
    }

    /**
     * Gets the determinant.
     *
     * @return this {@code Matrix4}.
     */
    public float getDeterminant() {
        float determinant = element00 * ((element11 * element22 * element33 + element12 * element23 * element31
            + element13 * element21 * element32) - element13 * element22 * element31
            - element11 * element23 * element32 - element12 * element21 * element33);

        determinant -= element01 * ((element10 * element22 * element33 + element12 * element23 * element30
            + element13 * element20 * element32) - element13 * element22 * element30
            - element10 * element23 * element32 - element12 * element20 * element33);
        determinant += element02 * ((element10 * element21 * element33 + element11 * element23 * element30
            + element13 * element20 * element31) - element13 * element21 * element30
            - element10 * element23 * element31 - element11 * element20 * element33);
        determinant -= element03 * ((element10 * element21 * element32 + element11 * element22 * element30
            + element12 * element20 * element31) - element12 * element21 * element30
            - element10 * element22 * element31 - element11 * element20 * element32);

        return determinant;
    }

    /**
     * Sets this {@code Matrix4} to the zero.
     *
     * @return this {@code Matrix4}.
     */
    public Matrix4 setZero() {
        element00 = 0.0F;
        element01 = 0.0F;
        element02 = 0.0F;
        element03 = 0.0F;
        element10 = 0.0F;
        element11 = 0.0F;
        element12 = 0.0F;
        element13 = 0.0F;
        element20 = 0.0F;
        element21 = 0.0F;
        element22 = 0.0F;
        element23 = 0.0F;
        element30 = 0.0F;
        element31 = 0.0F;
        element32 = 0.0F;
        element33 = 0.0F;

        return this;
    }

    /**
     * Sets this {@code Matrix4} to the identity.
     *
     * @return this {@code Matrix4}.
     */
    public Matrix4 setIdentity() {
        element00 = 1.0F;
        element01 = 0.0F;
        element02 = 0.0F;
        element03 = 0.0F;
        element10 = 0.0F;
        element11 = 1.0F;
        element12 = 0.0F;
        element13 = 0.0F;
        element20 = 0.0F;
        element21 = 0.0F;
        element22 = 1.0F;
        element23 = 0.0F;
        element30 = 0.0F;
        element31 = 0.0F;
        element32 = 0.0F;
        element33 = 1.0F;

        return this;
    }

    /**
     * Sets this {@code Matrix4} to a transformation matrix.
     *
     * @param translation the rendered object's translation.
     * @param rotation    the rendered object's rotation (in degrees).
     * @param scale       the rendered object's scale.
     * @return this {@code Matrix4}.
     */
    public Matrix4 setTransformation(Vector3 translation, Vector3 rotation, Vector3 scale) {
        setIdentity();
        translate(translation);
        rotate(rotation.x, new Vector3(1.0F, 0.0F, 0.0F));
        rotate(rotation.y, new Vector3(0.0F, 1.0F, 0.0F));
        rotate(rotation.z, new Vector3(0.0F, 0.0F, 1.0F));
        scale(scale);

        return this;
    }

    /**
     * Sets this {@code Matrix4} to a view matrix.
     *
     * @param translation the translation.
     * @param rotation    the rotation.
     * @return this {@code Matrix4}.
     */
    public Matrix4 setView(Vector3 translation, Vector3 rotation) {
        setIdentity();
        rotate(rotation.x, new Vector3(1.0F, 0.0F, 0.0F));
        rotate(rotation.y, new Vector3(0.0F, 1.0F, 0.0F));
        rotate(rotation.z, new Vector3(0.0F, 0.0F, 1.0F));
        translate(new Vector3(translation).negate());

        return this;
    }

    public Matrix4 setLookAt(Vector3 position, Vector3 target, Vector3 up) {
        Vector3 forward = new Vector3();
        Vector3 right = new Vector3();
        Vector3 newUp = new Vector3();

        Vector3.subtract(target, position, forward);
        Vector3.normalize(forward);
        Vector3.cross(forward, up, right);
        Vector3.normalize(right);
        Vector3.cross(right, forward, newUp);
        Vector3.negate(forward);
        setIdentity();

        element00 = right.x;
        element01 = right.y;
        element02 = right.z;
        element10 = newUp.x;
        element11 = newUp.y;
        element12 = newUp.z;
        element20 = forward.x;
        element21 = forward.y;
        element22 = forward.z;

        translate(new Vector3(position).negate());

        return this;
    }

    /**
     * Sets this {@code Matrix4} to a perspective projection matrix, projecting 3-D vertices in a scene into a 2-D
     * viewport with a perspective divide.
     *
     * @param width       the drawing area's width (in pixels), used to calculate the aspect ratio.
     * @param height      the drawing area's height (in pixels), used to calculate the aspect ratio.
     * @param fieldOfView the field of view (in degrees), or the angular extent of scene.
     * @param nearPlane   the minimum visible depth of the scene.
     * @param farPlane    the maximum visible depth of the scene.
     * @return this {@code Matrix4}.
     */
    public Matrix4 setPerspectiveProjection(int width, int height, float fieldOfView, float nearPlane, float farPlane) {
        float aspectRatio = (float) width / (float) height;
        float yScale = (float) (1.0F / Math.tan(Math.toRadians(fieldOfView / 2.0F)));
        float xScale = yScale / aspectRatio;
        float frustumLength = farPlane - nearPlane;

        setIdentity();

        element00 = xScale;
        element11 = yScale;
        element22 = -(farPlane + nearPlane) / frustumLength;
        element23 = -1.0F;
        element32 = -(2.0F * farPlane * nearPlane) / frustumLength;
        element33 = 0.0F;

        return this;
    }

    /**
     * Calculates the sum of this {@code Matrix4} and another.
     *
     * @param other the other {@code Matrix4} to add.
     * @return this {@code Matrix4}.
     */
    public Matrix4 add(Matrix4 other) {
        return add(this, other, this);
    }

    /**
     * Calculates the sum of a {@code Matrix4} and another.
     *
     * @param left        the left {@code Matrix4} to add.
     * @param right       the right {@code Matrix4} to add.
     * @param destination the destination {@code Matrix4}.
     * @return a {@code Matrix4} containing the result.
     */
    public static Matrix4 add(Matrix4 left, Matrix4 right, Matrix4 destination) {
        if (destination == null) {
            destination = new Matrix4();
        }

        destination.element00 = left.element00 + right.element00;
        destination.element01 = left.element01 + right.element01;
        destination.element02 = left.element02 + right.element02;
        destination.element03 = left.element03 + right.element03;
        destination.element10 = left.element10 + right.element10;
        destination.element11 = left.element11 + right.element11;
        destination.element12 = left.element12 + right.element12;
        destination.element13 = left.element13 + right.element13;
        destination.element20 = left.element20 + right.element20;
        destination.element21 = left.element21 + right.element21;
        destination.element22 = left.element22 + right.element22;
        destination.element23 = left.element23 + right.element23;
        destination.element30 = left.element30 + right.element30;
        destination.element31 = left.element31 + right.element31;
        destination.element32 = left.element32 + right.element32;
        destination.element33 = left.element33 + right.element33;

        return destination;
    }

    /**
     * Calculates the difference of this {@code Matrix4} and another.
     *
     * @param other the other {@code Matrix4} to subtract.
     * @return this {@code Matrix4}.
     */
    public Matrix4 subtract(Matrix4 other) {
        return subtract(this, other, this);
    }

    /**
     * Calculates the difference of a {@code Matrix4} and another.
     *
     * @param left        the left {@code Matrix4} to subtract.
     * @param right       the right {@code Matrix4} to subtract.
     * @param destination the {@code Matrix4} to store the result in.
     * @return a {@code Matrix4} containing the result.
     */
    public static Matrix4 subtract(Matrix4 left, Matrix4 right, Matrix4 destination) {
        if (destination == null) {
            destination = new Matrix4();
        }

        destination.element00 = left.element00 - right.element00;
        destination.element01 = left.element01 - right.element01;
        destination.element02 = left.element02 - right.element02;
        destination.element03 = left.element03 - right.element03;
        destination.element10 = left.element10 - right.element10;
        destination.element11 = left.element11 - right.element11;
        destination.element12 = left.element12 - right.element12;
        destination.element13 = left.element13 - right.element13;
        destination.element20 = left.element20 - right.element20;
        destination.element21 = left.element21 - right.element21;
        destination.element22 = left.element22 - right.element22;
        destination.element23 = left.element23 - right.element23;
        destination.element30 = left.element30 - right.element30;
        destination.element31 = left.element31 - right.element31;
        destination.element32 = left.element32 - right.element32;
        destination.element33 = left.element33 - right.element33;

        return destination;
    }

    /**
     * Calculates the product of this {@code Matrix4} and another.
     *
     * @param other the other {@code Matrix4} to multiply by.
     * @return this {@code Matrix4}.
     */
    public Matrix4 multiply(Matrix4 other) {
        return multiply(this, other, this);
    }

    /**
     * Calculates the product of a {@code Matrix4} and another.
     *
     * @param left        the left {@code Matrix4} to multiply by.
     * @param right       the right {@code Matrix4} to multiply by.
     * @param destination the {@code Matrix4} to store the result in.
     * @return a {@code Matrix4} containing the result.
     */
    public static Matrix4 multiply(Matrix4 left, Matrix4 right, Matrix4 destination) {
        if (destination == null) {
            destination = new Matrix4();
        }

        destination.element00 = left.element00 * right.element00 + left.element10 * right.element01 + left.element20
            * right.element02 + left.element30 * right.element03;
        destination.element01 = left.element01 * right.element00 + left.element11 * right.element01 + left.element21
            * right.element02 + left.element31 * right.element03;
        destination.element02 = left.element02 * right.element00 + left.element12 * right.element01 + left.element22
            * right.element02 + left.element32 * right.element03;
        destination.element03 = left.element03 * right.element00 + left.element13 * right.element01 + left.element23
            * right.element02 + left.element33 * right.element03;
        destination.element10 = left.element00 * right.element10 + left.element10 * right.element11 + left.element20
            * right.element12 + left.element30 * right.element13;
        destination.element11 = left.element01 * right.element10 + left.element11 * right.element11 + left.element21
            * right.element12 + left.element31 * right.element13;
        destination.element12 = left.element02 * right.element10 + left.element12 * right.element11 + left.element22
            * right.element12 + left.element32 * right.element13;
        destination.element13 = left.element03 * right.element10 + left.element13 * right.element11 + left.element23
            * right.element12 + left.element33 * right.element13;
        destination.element20 = left.element00 * right.element20 + left.element10 * right.element21 + left.element20
            * right.element22 + left.element30 * right.element23;
        destination.element21 = left.element01 * right.element20 + left.element11 * right.element21 + left.element21
            * right.element22 + left.element31 * right.element23;
        destination.element22 = left.element02 * right.element20 + left.element12 * right.element21 + left.element22
            * right.element22 + left.element32 * right.element23;
        destination.element23 = left.element03 * right.element20 + left.element13 * right.element21 + left.element23
            * right.element22 + left.element33 * right.element23;
        destination.element30 = left.element00 * right.element30 + left.element10 * right.element31 + left.element20
            * right.element32 + left.element30 * right.element33;
        destination.element31 = left.element01 * right.element30 + left.element11 * right.element31 + left.element21
            * right.element32 + left.element31 * right.element33;
        destination.element32 = left.element02 * right.element30 + left.element12 * right.element31 + left.element22
            * right.element32 + left.element32 * right.element33;
        destination.element33 = left.element03 * right.element30 + left.element13 * right.element31 + left.element23
            * right.element32 + left.element33 * right.element33;

        return destination;
    }

    /**
     * Negates this {@code Matrix4}.
     *
     * @return this {@code Matrix4}.
     */
    public Matrix4 negate() {
        return negate(this, this);
    }

    /**
     * Negates a {@code Matrix4}.
     *
     * @param source      the {@code Matrix4} to negate.
     * @param destination the {@code Matrix4} to store the result in.
     * @return a {@code Matrix4} containing the result.
     */
    public static Matrix4 negate(Matrix4 source, Matrix4 destination) {
        if (destination == null) {
            destination = new Matrix4();
        }

        destination.element00 = -source.element00;
        destination.element01 = -source.element01;
        destination.element02 = -source.element02;
        destination.element03 = -source.element03;
        destination.element10 = -source.element10;
        destination.element11 = -source.element11;
        destination.element12 = -source.element12;
        destination.element13 = -source.element13;
        destination.element20 = -source.element20;
        destination.element21 = -source.element21;
        destination.element22 = -source.element22;
        destination.element23 = -source.element23;
        destination.element30 = -source.element30;
        destination.element31 = -source.element31;
        destination.element32 = -source.element32;
        destination.element33 = -source.element33;

        return destination;
    }

    /**
     * Inverts this {@code Matrix4}.
     *
     * @return this {@code Matrix4}.
     */
    public Matrix4 invert() {
        float determinant = getDeterminant();

        if (determinant != 0) {
            float inverseDeterminant = 1.0F / determinant;
            float value00 = new Matrix3(element11, element12, element13, element21, element22, element23, element31,
                element32, element33).getDeterminant() * inverseDeterminant;
            float value01 = -new Matrix3(element10, element12, element13, element20, element22, element23, element30,
                element32, element33).getDeterminant() * inverseDeterminant;
            float value02 = new Matrix3(element10, element11, element13, element20, element21, element23, element30,
                element31, element33).getDeterminant() * inverseDeterminant;
            float value03 = -new Matrix3(element10, element11, element12, element20, element21, element22, element30,
                element31, element32).getDeterminant() * inverseDeterminant;
            float value10 = -new Matrix3(element01, element02, element03, element21, element22, element23, element31,
                element32, element33).getDeterminant() * inverseDeterminant;
            float value11 = new Matrix3(element00, element02, element03, element20, element22, element23, element30,
                element32, element33).getDeterminant() * inverseDeterminant;
            float value12 = -new Matrix3(element00, element01, element03, element20, element21, element23, element30,
                element31, element33).getDeterminant() * inverseDeterminant;
            float value13 = new Matrix3(element00, element01, element02, element20, element21, element22, element30,
                element31, element32).getDeterminant() * inverseDeterminant;
            float value20 = new Matrix3(element01, element02, element03, element11, element12, element13, element31,
                element32, element33).getDeterminant() * inverseDeterminant;
            float value21 = -new Matrix3(element00, element02, element03, element10, element12, element13, element30,
                element32, element33).getDeterminant() * inverseDeterminant;
            float value22 = new Matrix3(element00, element01, element03, element10, element11, element13, element30,
                element31, element33).getDeterminant() * inverseDeterminant;
            float value23 = -new Matrix3(element00, element01, element02, element10, element11, element12, element30,
                element31, element32).getDeterminant() * inverseDeterminant;
            float value30 = -new Matrix3(element01, element02, element03, element11, element12, element13, element21,
                element22, element23).getDeterminant() * inverseDeterminant;
            float value31 = new Matrix3(element00, element02, element03, element10, element12, element13, element20,
                element22, element23).getDeterminant() * inverseDeterminant;
            float value32 = -new Matrix3(element00, element01, element03, element10, element11, element13, element20,
                element21, element23).getDeterminant() * inverseDeterminant;
            float value33 = new Matrix3(element00, element01, element02, element10, element11, element12, element20,
                element21, element22).getDeterminant() * inverseDeterminant;

            element00 = value00;
            element01 = value01;
            element02 = value02;
            element03 = value03;
            element10 = value10;
            element11 = value11;
            element12 = value12;
            element13 = value13;
            element20 = value20;
            element21 = value21;
            element22 = value22;
            element23 = value23;
            element30 = value30;
            element31 = value31;
            element32 = value32;
            element33 = value33;
        }

        return this;
    }

    /**
     * Translates this {@code Matrix4}.
     *
     * @param translation the translation.
     * @return this {@code Matrix4}.
     */
    public Matrix4 translate(Vector3 translation) {
        element30 += element00 * translation.x + element10 * translation.y + element20 * translation.z;
        element31 += element01 * translation.x + element11 * translation.y + element21 * translation.z;
        element32 += element02 * translation.x + element12 * translation.y + element22 * translation.z;
        element33 += element03 * translation.x + element13 * translation.y + element23 * translation.z;

        return this;
    }

    /**
     * Rotates this {@code Matrix4}.
     *
     * @param angle the angle (in degrees) to rotate by.
     * @param axis  the axis to rotate.
     * @return this {@code Matrix4}.
     */
    public Matrix4 rotate(float angle, Vector3 axis) {
        float cosine = (float) Math.cos(Math.toRadians(angle));
        float sine = (float) Math.sin(Math.toRadians(angle));
        float oneMinusCosine = 1.0f - cosine;
        float xy = axis.x * axis.y;
        float yz = axis.y * axis.z;
        float xz = axis.x * axis.z;
        float xs = axis.x * sine;
        float ys = axis.y * sine;
        float zs = axis.z * sine;
        float factor00 = axis.x * axis.x * oneMinusCosine + cosine;
        float factor01 = xy * oneMinusCosine + zs;
        float factor02 = xz * oneMinusCosine - ys;
        float factor10 = xy * oneMinusCosine - zs;
        float factor11 = axis.y * axis.y * oneMinusCosine + cosine;
        float factor12 = yz * oneMinusCosine + xs;
        float factor20 = xz * oneMinusCosine + ys;
        float factor21 = yz * oneMinusCosine - xs;
        float factor22 = axis.z * axis.z * oneMinusCosine + cosine;
        float value00 = element00 * factor00 + element10 * factor01 + element20 * factor02;
        float value01 = element01 * factor00 + element11 * factor01 + element21 * factor02;
        float value02 = element02 * factor00 + element12 * factor01 + element22 * factor02;
        float value03 = element03 * factor00 + element13 * factor01 + element23 * factor02;
        float value10 = element00 * factor10 + element10 * factor11 + element20 * factor12;
        float value11 = element01 * factor10 + element11 * factor11 + element21 * factor12;
        float value12 = element02 * factor10 + element12 * factor11 + element22 * factor12;
        float value13 = element03 * factor10 + element13 * factor11 + element23 * factor12;
        float value20 = element00 * factor20 + element10 * factor21 + element20 * factor22;
        float value21 = element01 * factor20 + element11 * factor21 + element21 * factor22;
        float value22 = element02 * factor20 + element12 * factor21 + element22 * factor22;
        float value23 = element03 * factor20 + element13 * factor21 + element23 * factor22;

        element00 = value00;
        element01 = value01;
        element02 = value02;
        element03 = value03;
        element10 = value10;
        element11 = value11;
        element12 = value12;
        element13 = value13;
        element20 = value20;
        element21 = value21;
        element22 = value22;
        element23 = value23;

        return this;
    }

    /**
     * Scales this {@code Matrix4}.
     *
     * @param scale the scale.
     * @return this {@code Matrix4}.
     */
    public Matrix4 scale(Vector3 scale) {
        element00 *= scale.x;
        element01 *= scale.x;
        element02 *= scale.x;
        element03 *= scale.x;
        element10 *= scale.y;
        element11 *= scale.y;
        element12 *= scale.y;
        element13 *= scale.y;
        element20 *= scale.z;
        element21 *= scale.z;
        element22 *= scale.z;
        element23 *= scale.z;

        return this;
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
            element03,
            element10,
            element11,
            element12,
            element13,
            element20,
            element21,
            element22,
            element23,
            element30,
            element31,
            element32,
            element33
        };
    }

    /**
     * Gets a {@code String} representation of this {@code Matrix4}.
     *
     * @return a {@code String} representation of this {@code Matrix4}.
     */
    @Override
    public String toString() {
        String row1 = "Row 1: [" + element00 + ", " + element10 + ", " + element20 + ", " + element30 + "]";
        String row2 = "Row 2: [" + element01 + ", " + element11 + ", " + element21 + ", " + element31 + "]";
        String row3 = "Row 3: [" + element02 + ", " + element12 + ", " + element22 + ", " + element32 + "]";
        String row4 = "Row 4: [" + element03 + ", " + element13 + ", " + element23 + ", " + element33 + "]";

        return row1 + "\n" + row2 + "\n" + row3 + "\n" + row4;
    }

}
