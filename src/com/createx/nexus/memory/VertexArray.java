package com.createx.nexus.memory;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * A {@code VertexArray} stores one or more {@code VertexBuffer}s (and optionally) an {@code IndexBuffer}.
 *
 * @author Christopher Ruley
 */
public class VertexArray {

    /**
     * The {@code VertexBuffer} array.
     */
    private VertexBuffer[] vertexBuffers;

    /**
     * The unique identifier.
     */
    private int handle;

    /**
     * The (optional) {@code IndexBuffer}.
     */
    private IndexBuffer indexBuffer;

    /**
     * Constructs a {@code VertexArray} and associates the provided {@code VertexBuffer}s.
     *
     * @param vertexBuffers The {@code VertexBuffer}s.
     */
    public VertexArray(VertexBuffer... vertexBuffers) {
        this.vertexBuffers = vertexBuffers;
        handle = glGenVertexArrays();

        bind();

        int index = 0;

        for (var vertexBuffer : vertexBuffers) {
            VertexLayout vertexLayout = vertexBuffer.getVertexLayout();
            VertexElement[] vertexElements = vertexLayout.getVertexElements();

            vertexBuffer.bind();

            for (var vertexElement : vertexElements) {
                DataType dataType = vertexElement.getDataType();

                glEnableVertexAttribArray(index);
                glVertexAttribPointer(index, dataType.getComponentCount(), dataType.getOpenGLType(),
                    vertexElement.isNormalized(), vertexLayout.getStride(), vertexElement.getOffset());

                index++;
            }
        }

        unbind();
    }

    /**
     * Gets the {@code VertexBuffer} array.
     *
     * @return the {@code VertexBuffer} array.
     */
    public VertexBuffer[] getVertexBuffers() {
        return vertexBuffers;
    }

    /**
     * Gets the (optional) {@code IndexBuffer}. This value is null if this {@code VertexArray} does not use an
     * {@code IndexBuffer}.
     *
     * @return The (optional) {@code IndexBuffer}.
     */
    public IndexBuffer getIndexBuffer() {
        return indexBuffer;
    }

    /**
     * Sets the {@code IndexBuffer}.
     *
     * @param indexBuffer The {@code IndexBuffer}.
     */
    public void setIndexBuffer(IndexBuffer indexBuffer) {
        this.indexBuffer = indexBuffer;

        bind();
        indexBuffer.bind();
        unbind();
    }

    /**
     * Binds this {@code VertexArray}.
     */
    public void bind() {
        glBindVertexArray(handle);
    }

    /**
     * Unbinds this {@code VertexArray}.
     */
    public void unbind() {
        glBindVertexArray(0);
    }

    /**
     * Deletes this {@code VertexArray} and the associated {@code VertexBuffer}s (and {@code IndexBuffer}, if used).
     */
    public void delete() {
        glDeleteVertexArrays(handle);

        for (var vertexBuffer : vertexBuffers) {
            vertexBuffer.delete();
        }

        if (indexBuffer != null) {
            indexBuffer.delete();
        }
    }

}
