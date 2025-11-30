package com.nexus.model;

/**
 * @author Christopher Ruley
 */
public class Mesh {

    /**
     * The name.
     */
    private final String name;

    /**
     * The {@code Material}.
     */
    private final Material material;

    /**
     * The total number of vertices.
     */
    private final int vertexCount;

    /**
     * The total number of indices.
     */
    private final int indexCount;

    /**
     * The starting position of the indices.
     */
    private final int indexOffset;

    /**
     * Constructs a {@code Mesh}.
     *
     * @param name        the name.
     * @param material    the {@code Material}.
     * @param vertexCount the total number of vertices.
     * @param indexCount  the total number of indices.
     * @param indexOffset the starting position of the indices.
     */
    public Mesh(String name, Material material, int vertexCount, int indexCount, int indexOffset) {
        this.name = name;
        this.material = material;
        this.vertexCount = vertexCount;
        this.indexCount = indexCount;
        this.indexOffset = indexOffset;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getIndexCount() {
        return indexCount;
    }

    public int getIndexOffset() {
        return indexOffset;
    }

}