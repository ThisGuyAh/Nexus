package com.nexus.utility;

import com.nexus.math.Matrix;
import com.nexus.math.Vector;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static java.nio.ByteBuffer.*;
import static java.nio.ByteOrder.*;

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
     * Stores a {@code Vector} in a {@code FloatBuffer}.
     *
     * @param data the {@code Vector} to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(Vector data) {
        return store(data.toArray());
    }

    /**
     * Stores a {@code Matrix} in a {@code FloatBuffer}.
     *
     * @param data the {@code Matrix} to be stored.
     * @return a {@code FloatBuffer} containing the stored data.
     */
    public static FloatBuffer store(Matrix data) {
        return store(data.toArray());
    }

}
