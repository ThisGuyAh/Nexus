package com.codex.nexus.utility;

import static java.nio.ByteBuffer.*;
import static java.nio.ByteOrder.*;

import com.codex.nexus.math.Matrix2;
import com.codex.nexus.math.Matrix3;
import com.codex.nexus.math.Matrix4;
import com.codex.nexus.math.Vector2;
import com.codex.nexus.math.Vector3;
import com.codex.nexus.math.Vector4;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * {@code Memory} defines methods used for memory allocations.
 */
public class Memory {

    /**
     * Cannot construct {@code Memory}.
     */
    private Memory() {
    }

    /**
     * Stores an array of bytes in a {@code ByteBuffer}.
     *
     * @param data the array of bytes to be stored.
     * @return a {@code ByteBuffer} containing the stored data.
     */
    public static ByteBuffer store(byte[] data) {
        return allocateDirect(data.length).order(nativeOrder()).put(data).flip();
    }

    /**
     * Stores an array of ints in a {@code IntBuffer}.
     *
     * @param data the array of ints to be stored.
     * @return a {@code IntBuffer} containing the stored data.
     */
    public static IntBuffer store(int[] data) {
        return allocateDirect(data.length << 2).order(nativeOrder()).asIntBuffer().put(data).flip();
    }

    /**
     * Stores an array of floats in a {@code FloatBuffer}.
     *
     * @param data the array of floats to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(float[] data) {
        return allocateDirect(data.length << 2).order(nativeOrder()).asFloatBuffer().put(data).flip();
    }

    /**
     * Stores a {@code Vector2} in a {@code FloatBuffer}.
     *
     * @param data the {@code Vector2} to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(Vector2 data) {
        return store(data.toArray());
    }

    /**
     * Stores a {@code Vector3} in a {@code FloatBuffer}.
     *
     * @param data the {@code Vector3} to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(Vector3 data) {
        return store(data.toArray());
    }

    /**
     * Stores a {@code Vector4} in a {@code FloatBuffer}.
     *
     * @param data the {@code Vector4} to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(Vector4 data) {
        return store(data.toArray());
    }

    /**
     * Stores a {@code Matrix2} in a {@code FloatBuffer}.
     *
     * @param data the {@code Matrix2} to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(Matrix2 data) {
        return store(data.toArray());
    }

    /**
     * Stores a {@code Matrix3} in a {@code FloatBuffer}.
     *
     * @param data the {@code Matrix3} to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(Matrix3 data) {
        return store(data.toArray());
    }

    /**
     * Stores a {@code Matrix4} in a {@code FloatBuffer}.
     *
     * @param data the {@code Matrix4} to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(Matrix4 data) {
        return store(data.toArray());
    }

}
