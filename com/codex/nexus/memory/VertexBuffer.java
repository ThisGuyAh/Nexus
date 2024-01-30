package com.codex.nexus.memory;

import com.codex.nexus.math.Vector;

import static com.codex.nexus.utility.Arrays.*;
import static com.codex.nexus.utility.Memory.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * {@code VertexBuffer} stores vertex data and a {@code VertexLayout}.
 *
 * @author Christopher Ruley
 */
public class VertexBuffer {

    /**
     * The stored data.
     */
    private float[] data;

    /**
     * The {@code VertexLayout}.
     */
    private VertexLayout vertexLayout;

    /**
     * The unique identification.
     */
    private int handle;

    /**
     * Constructs a {@code VertexBuffer}.
     *
     * @param data         the data to store.
     * @param vertexLayout the {@code VertexLayout}.
     */
    public VertexBuffer(float[] data, VertexLayout vertexLayout) {
        this.data = data;
        this.vertexLayout = vertexLayout;
        handle = glGenBuffers();

        bind();
        glBufferData(GL_ARRAY_BUFFER, store(data), GL_STATIC_DRAW);
    }

    /**
     * Constructs a {@code VertexBuffer}.
     *
     * @param data         the data to store.
     * @param vertexLayout the {@code VertexLayout}.
     */
    public VertexBuffer(Vector[] data, VertexLayout vertexLayout) {
        this(getFromVectors(data), vertexLayout);
    }

    /**
     * Gets the stored data.
     *
     * @return the stored data.
     */
    public float[] getData() {
        return data;
    }

    /**
     * Gets the {@code VertexLayout}.
     *
     * @return the {@code VertexLayout}.
     */
    public VertexLayout getVertexLayout() {
        return vertexLayout;
    }

    /**
     * Gets the unique identification.
     *
     * @return the unique identification.
     */
    public int getHandle() {
        return handle;
    }

    /**
     * Gets a float array from a {@code Vector} array.
     *
     * @param vectors the data to convert.
     * @return a float array from a {@code Vector} array.
     */
    private static float[] getFromVectors(Vector[] vectors) {
        float[] output = new float[0];

        for (var vector : vectors) {
            output = add(output, vector.toArray());
        }

        return output;
    }

    /**
     * Sets the stored data.
     *
     * @param data the data to store.
     */
    public void setData(float[] data) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, 0, this.data = data);
    }

    /**
     * Binds this {@code VertexBuffer}.
     */
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, handle);
    }

    /**
     * Unbinds this {@code VertexBuffer}.
     */
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    /**
     * Deletes this {@code VertexBuffer}.
     */
    public void delete() {
        glDeleteBuffers(handle);
    }

}
