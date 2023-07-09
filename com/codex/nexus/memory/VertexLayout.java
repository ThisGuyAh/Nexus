package com.codex.nexus.memory;

/**
 * A {@code VertexLayout} stores one or more {@code VertexElement}s to provide an interpretation of vertex data stored
 * in a {@code VertexBuffer}.
 *
 * @author Christopher Ruley
 */
public class VertexLayout {

    /**
     * The {@code VertexElement} array.
     */
    private VertexElement[] vertexElements;

    /**
     * The stride (in bytes), or the total of each {@code VertexElement}'s size.
     */
    private int stride;

    /**
     * Constructs an {@code VertexLayout} and calculates the offsets of each
     * {@code VertexElement} and the stride.
     *
     * @param vertexElements the {@code VertexElement} array.
     */
    public VertexLayout(VertexElement... vertexElements) {
        this.vertexElements = vertexElements;

        int offset = 0;

        for (var inputElement : vertexElements) {
            inputElement.offset = offset;

            int size = inputElement.getDataType().getSize();

            offset += size;
            stride += size;
        }
    }

    /**
     * Gets the {@code VertexElement} array.
     *
     * @return the {@code VertexElement} array.
     */
    public VertexElement[] getVertexElements() {
        return vertexElements;
    }

    /**
     * Gets the stride (in bytes), or the total of each {@code VertexElement}'s
     * size.
     *
     * @return the stride (in bytes).
     */
    public int getStride() {
        return stride;
    }

}
