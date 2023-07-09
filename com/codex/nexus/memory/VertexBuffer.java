package com.codex.nexus.memory;

import static com.codex.nexus.utility.Memory.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * {@code VertexBuffer} stores vertex data and an {@code VertexLayout}.
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
