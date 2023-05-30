package com.codex.nexus.math;

public class Matrix3 {

	public float element00;
	public float element01;
	public float element02;
	public float element10;
	public float element11;
	public float element12;
	public float element20;
	public float element21;
	public float element22;

	public Matrix3() {
		setIdentity();
	}

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

	public float getDeterminant() {
		return element00 * (element11 * element22 - element12 * element21)
				+ element01 * (element12 * element20 - element10 * element22)
				+ element02 * (element10 * element21 - element11 * element20);
	}

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

	public Matrix3 add(Matrix3 other) {
		element00 += other.element00;
		element01 += other.element01;
		element02 += other.element02;
		element10 += other.element10;
		element11 += other.element11;
		element12 += other.element12;
		element20 += other.element20;
		element21 += other.element21;
		element22 += other.element22;

		return this;
	}

	public Matrix3 subtrtact(Matrix3 other) {
		element00 -= other.element00;
		element01 -= other.element01;
		element02 -= other.element02;
		element10 -= other.element10;
		element11 -= other.element11;
		element12 -= other.element12;
		element20 -= other.element20;
		element21 -= other.element21;
		element22 -= other.element22;

		return this;
	}

	public Matrix3 multiply(Matrix3 other) {
		float value00 = element00 * other.element00 + element10 * other.element01 + element20 * other.element02;
		float value01 = element01 * other.element00 + element11 * other.element01 + element21 * other.element02;
		float value02 = element02 * other.element00 + element12 * other.element01 + element22 * other.element02;
		float value10 = element00 * other.element10 + element10 * other.element11 + element20 * other.element12;
		float value11 = element01 * other.element10 + element11 * other.element11 + element21 * other.element12;
		float value12 = element02 * other.element10 + element12 * other.element11 + element22 * other.element12;
		float value20 = element00 * other.element20 + element10 * other.element21 + element20 * other.element22;
		float value21 = element01 * other.element20 + element11 * other.element21 + element21 * other.element22;
		float value22 = element02 * other.element20 + element12 * other.element21 + element22 * other.element22;

		element00 = value00;
		element01 = value01;
		element02 = value02;
		element10 = value10;
		element11 = value11;
		element12 = value12;
		element20 = value20;
		element21 = value21;
		element22 = value22;

		return this;
	}

	public Matrix3 negate() {
		element00 = -element00;
		element01 = -element01;
		element02 = -element02;
		element10 = -element10;
		element11 = -element11;
		element12 = -element12;
		element20 = -element20;
		element21 = -element21;
		element22 = -element22;

		return this;
	}

	public Matrix3 invert() {
		float determinant = getDeterminant();

		if (determinant != 0) {
			float inverseDeterminant = 1.0F / determinant;

			float value00 = (element11 * element22 - element12 * element21) * inverseDeterminant;
			float value01 = (-element10 * element22 + element12 * element20) * inverseDeterminant;
			float value02 = (element10 * element21 - element11 * element20) * inverseDeterminant;
			float value10 = (-element01 * element22 + element02 * element21) * inverseDeterminant;
			float value11 = (element00 * element22 - element02 * element20) * inverseDeterminant;
			float value12 = (-element00 * element21 + element01 * element20) * inverseDeterminant;
			float value20 = (element01 * element12 - element02 * element11) * inverseDeterminant;
			float value21 = (-element00 * element12 + element02 * element10) * inverseDeterminant;
			float value22 = (element00 * element11 - element01 * element10) * inverseDeterminant;

			element00 = value00;
			element11 = value01;
			element22 = value02;
			element01 = value10;
			element10 = value11;
			element20 = value12;
			element02 = value20;
			element12 = value21;
			element21 = value22;
		}

		return this;
	}

}
