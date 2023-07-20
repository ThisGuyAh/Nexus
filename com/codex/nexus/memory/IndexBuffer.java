package com.codex.nexus.memory;

import static com.codex.nexus.utility.Memory.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * An {@code IndexBuffer} stores index data, which determines the order in which vertices should be drawn.
 *
 * @author Christopher Ruley
 */
public class IndexBuffer {

    /**
     * The stored indices.
     */
    private int[] indices;

    /**
     * The number of indices.
     */
    private int count;

    /**
     * The unique identification.
     */
    private int handle;

    /**
     * Constructs an {@code IndexBuffer}.
     *
     * @param indices the indices to store.
     */
    public IndexBuffer(int[] indices) {
        this.indices = indices;
        count = indices.length;
        handle = glGenBuffers();

        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, store(indices), GL_STATIC_DRAW);
    }

    /**
     * Gets the stored indices.
     *
     * @return the stored indices.
     */
    public int[] getIndices() {
        return indices;
    }

    /**
     * Gets the number of indices.
     *
     * @return the number of indices.
     */
    public int getCount() {
        return count;
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
     * Binds this {@code IndexBuffer}.
     */
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, handle);
    }

    /**
     * Unbinds this {@code IndexBuffer}.
     */
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * Deletes this {@code IndexBuffer}.
     */
    public void delete() {
        glDeleteBuffers(handle);
    }

}
