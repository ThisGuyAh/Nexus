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

public class Memory {

    private Memory() {
    }

    public static ByteBuffer store(byte[] data) {
        return allocateDirect(data.length).order(nativeOrder()).put(data).flip();
    }

    public static IntBuffer store(int[] data) {
        return allocateDirect(data.length << 2).order(nativeOrder()).asIntBuffer().put(data).flip();
    }

    public static FloatBuffer store(float[] data) {
        return allocateDirect(data.length << 2).order(nativeOrder()).asFloatBuffer().put(data).flip();
    }

    public static FloatBuffer store(Vector2 data) {
        return store(data.toArray());
    }

    public static FloatBuffer store(Vector3 data) {
        return store(data.toArray());
    }

    public static FloatBuffer store(Vector4 data) {
        return store(data.toArray());
    }

    public static FloatBuffer store(Matrix2 data) {
        return store(data.toArray());
    }

    public static FloatBuffer store(Matrix3 data) {
        return store(data.toArray());
    }

    public static FloatBuffer store(Matrix4 data) {
        return store(data.toArray());
    }

}
