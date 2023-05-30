package com.codex.nexus.math;

public class Matrix2 {

	public float element00;
	public float element01;
	public float element10;
	public float element11;

	public Matrix2() {
		setIdentity();
	}

	public Matrix2(Matrix2 other) {
		element00 = other.element00;
		element01 = other.element01;
		element10 = other.element10;
		element11 = other.element11;
	}

	public Matrix2(float element00, float element01, float element10, float element11) {
		this.element00 = element00;
		this.element01 = element01;
		this.element10 = element10;
		this.element11 = element11;
	}

	public float getDeterminant() {
		return element00 * element11 - element01 * element10;
	}

	public Matrix2 setZero() {
		element00 = 0.0F;
		element01 = 0.0F;
		element10 = 0.0F;
		element11 = 0.0F;

		return this;
	}

	public Matrix2 setIdentity() {
		element00 = 1.0F;
		element01 = 0.0F;
		element10 = 0.0F;
		element11 = 1.0F;

		return this;
	}

	public Matrix2 add(Matrix2 other) {
		element00 += other.element00;
		element01 += other.element01;
		element10 += other.element10;
		element11 += other.element11;

		return this;
	}

	public Matrix2 subtract(Matrix2 other) {
		element00 -= other.element00;
		element01 -= other.element01;
		element10 -= other.element10;
		element11 -= other.element11;

		return this;
	}

	public Matrix2 multiply(Matrix2 other) {
		float value00 = element00 * other.element00 + element10 * other.element01;
		float value01 = element01 * other.element00 + element11 * other.element01;
		float value10 = element00 * other.element10 + element10 * other.element11;
		float value11 = element01 * other.element10 + element11 * other.element11;

		element00 = value00;
		element01 = value01;
		element10 = value10;
		element11 = value11;

		return this;
	}

	public Matrix2 negate() {
		element00 = -element00;
		element01 = -element01;
		element10 = -element10;
		element11 = -element11;

		return this;
	}

	public Matrix2 invert() {
		float determinant = getDeterminant();

		if (determinant != 0) {
			float inverseDeterminant = 1.0F / determinant;
			float value00 = element11 * inverseDeterminant;
			float value01 = -element01 * inverseDeterminant;
			float value10 = -element10 * inverseDeterminant;
			float value11 = element00 * inverseDeterminant;

			element00 = value00;
			element01 = value01;
			element10 = value10;
			element11 = value11;
		}

		return this;
	}

}
