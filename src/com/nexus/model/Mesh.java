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
     * The starting position of the indices.
     */
    private final int indexOffset;

    /**
     * The total number of indices.
     */
    private final int indexCount;

    /**
     * Constructs a {@code Mesh}.
     *
     * @param name        the name.
     * @param material    the {@code Material}.
     * @param indexOffset the starting position of the indices.
     * @param indexCount  the total number of indices.
     */
    public Mesh(String name, Material material, int indexOffset, int indexCount) {
        this.name = name;
        this.material = material;
        this.indexOffset = indexOffset;
        this.indexCount = indexCount;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public int getIndexOffset() {
        return indexOffset;
    }

    public int getIndexCount() {
        return indexCount;
    }

}