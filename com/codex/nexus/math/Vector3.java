package com.codex.nexus.math;

public class Vector3 {

	public float x;
	public float y;
	public float z;

	public Vector3() {
		setZero();
	}

	public Vector3(Vector3 other) {
		x = other.x;
		y = other.y;
		z = other.z;
	}

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getMagnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float getDot(Vector3 other) {
		return x * other.x + y * other.y + z * other.z;
	}

	public Vector3 setZero() {
		x = 0.0F;
		y = 0.0F;
		z = 0.0F;

		return this;
	}

	public Vector3 add(Vector3 other) {
		x += other.x;
		y += other.y;
		z += other.z;

		return this;
	}

	public Vector3 subtract(Vector3 other) {
		x -= other.x;
		y -= other.y;
		z -= other.z;

		return this;
	}

	public Vector3 cross(Vector3 other) {
		float a = y * other.z - z * other.y;
		float b = other.x * z - other.z * x;
		float c = x * other.y - y * other.x;

		x = a;
		y = b;
		z = c;

		return this;
	}

	public Vector3 negate() {
		x = -x;
		y = -y;
		z = -z;

		return this;
	}

	public Vector3 normalize() {
		float magnitude = getMagnitude();

		x /= magnitude;
		y /= magnitude;
		z /= magnitude;

		return this;
	}

}
