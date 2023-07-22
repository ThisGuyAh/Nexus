package com.codex.nexus.math;

public class Matrix4 extends Matrix {

    public float element00;
    public float element01;
    public float element02;
    public float element03;
    public float element10;
    public float element11;
    public float element12;
    public float element13;
    public float element20;
    public float element21;
    public float element22;
    public float element23;
    public float element30;
    public float element31;
    public float element32;
    public float element33;

    public Matrix4() {
        setIdentity();
    }

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

    public Matrix4 setTransformation(Vector3 translation, Vector3 rotation, Vector3 scale) {
        setIdentity();
        translate(translation);
        rotate(rotation.x, new Vector3(1.0F, 0.0F, 0.0F));
        rotate(rotation.y, new Vector3(0.0F, 1.0F, 0.0F));
        rotate(rotation.z, new Vector3(0.0F, 0.0F, 1.0F));
        scale(scale);

        return this;
    }

    public Matrix4 setView(Vector3 translation, Vector3 rotation) {
        setIdentity();
        rotate(rotation.x, new Vector3(1.0F, 0.0F, 0.0F));
        rotate(rotation.y, new Vector3(0.0F, 1.0F, 0.0F));
        rotate(rotation.z, new Vector3(0.0F, 0.0F, 1.0F));
        translate(new Vector3(translation).negate());

        return this;
    }

    public Matrix4 setProjection(int width, int height, float fieldOfView, float nearPlane, float farPlane) {
        setIdentity();

        float aspectRatio = (float) width / (float) height;
        float yScale = (float) (1.0F / Math.tan(Math.toRadians(fieldOfView / 2.0F)) * aspectRatio);
        float xScale = yScale / aspectRatio;
        float frustumLength = farPlane - nearPlane;

        element00 = xScale;
        element11 = yScale;
        element22 = -((nearPlane + farPlane) / frustumLength);
        element23 = -1.0F;
        element32 = -(2.0F * nearPlane * farPlane / frustumLength);
        element33 = 0.0F;

        return this;
    }

    public Matrix4 add(Matrix4 other) {
        element00 += other.element00;
        element01 += other.element01;
        element02 += other.element02;
        element03 += other.element03;
        element10 += other.element10;
        element11 += other.element11;
        element12 += other.element12;
        element13 += other.element13;
        element20 += other.element20;
        element21 += other.element21;
        element22 += other.element22;
        element23 += other.element23;
        element30 += other.element30;
        element31 += other.element31;
        element32 += other.element32;
        element33 += other.element33;

        return this;
    }

    public Matrix4 subtract(Matrix4 other) {
        element00 -= other.element00;
        element01 -= other.element01;
        element02 -= other.element02;
        element03 -= other.element03;
        element10 -= other.element10;
        element11 -= other.element11;
        element12 -= other.element12;
        element13 -= other.element13;
        element20 -= other.element20;
        element21 -= other.element21;
        element22 -= other.element22;
        element23 -= other.element23;
        element30 -= other.element30;
        element31 -= other.element31;
        element32 -= other.element32;
        element33 -= other.element33;

        return this;
    }

    public Matrix4 multiply(Matrix4 other) {
        float value00 = element00 * other.element00 + element10 * other.element01 + element20 * other.element02
            + element30 * other.element03;
        float value01 = element01 * other.element00 + element11 * other.element01 + element21 * other.element02
            + element31 * other.element03;
        float value02 = element02 * other.element00 + element12 * other.element01 + element22 * other.element02
            + element32 * other.element03;
        float value03 = element03 * other.element00 + element13 * other.element01 + element23 * other.element02
            + element33 * other.element03;
        float value10 = element00 * other.element10 + element10 * other.element11 + element20 * other.element12
            + element30 * other.element13;
        float value11 = element01 * other.element10 + element11 * other.element11 + element21 * other.element12
            + element31 * other.element13;
        float value12 = element02 * other.element10 + element12 * other.element11 + element22 * other.element12
            + element32 * other.element13;
        float value13 = element03 * other.element10 + element13 * other.element11 + element23 * other.element12
            + element33 * other.element13;
        float value20 = element00 * other.element20 + element10 * other.element21 + element20 * other.element22
            + element30 * other.element23;
        float value21 = element01 * other.element20 + element11 * other.element21 + element21 * other.element22
            + element31 * other.element23;
        float value22 = element02 * other.element20 + element12 * other.element21 + element22 * other.element22
            + element32 * other.element23;
        float value23 = element03 * other.element20 + element13 * other.element21 + element23 * other.element22
            + element33 * other.element23;
        float value30 = element00 * other.element30 + element10 * other.element31 + element20 * other.element32
            + element30 * other.element33;
        float value31 = element01 * other.element30 + element11 * other.element31 + element21 * other.element32
            + element31 * other.element33;
        float value32 = element02 * other.element30 + element12 * other.element31 + element22 * other.element32
            + element32 * other.element33;
        float value33 = element03 * other.element30 + element13 * other.element31 + element23 * other.element32
            + element33 * other.element33;

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

        return this;
    }

    public Matrix4 negate() {
        element00 = -element00;
        element01 = -element01;
        element02 = -element02;
        element03 = -element03;
        element10 = -element10;
        element11 = -element11;
        element12 = -element12;
        element13 = -element13;
        element20 = -element20;
        element21 = -element21;
        element22 = -element22;
        element23 = -element23;
        element30 = -element30;
        element31 = -element31;
        element32 = -element32;
        element33 = -element33;

        return this;
    }

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

    public Matrix4 translate(Vector3 translation) {
        element30 += element00 * translation.x + element10 * translation.y + element20 * translation.z;
        element31 += element01 * translation.x + element11 * translation.y + element21 * translation.z;
        element32 += element02 * translation.x + element12 * translation.y + element22 * translation.z;
        element33 += element03 * translation.x + element13 * translation.y + element23 * translation.z;

        return this;
    }

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

    @Override
    public String toString() {
        String row1 = "Row 1: [" + element00 + ", " + element10 + ", " + element20 + ", " + element30 + "]";
        String row2 = "Row 2: [" + element01 + ", " + element11 + ", " + element21 + ", " + element31 + "]";
        String row3 = "Row 3: [" + element02 + ", " + element12 + ", " + element22 + ", " + element32 + "]";
        String row4 = "Row 4: [" + element03 + ", " + element13 + ", " + element23 + ", " + element33 + "]";

        return row1 + "\n" + row2 + "\n" + row3 + "\n" + row4;
    }

}
