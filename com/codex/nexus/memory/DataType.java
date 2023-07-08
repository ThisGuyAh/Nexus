package com.codex.nexus.memory;

/**
 * {@code DataType} defines data types and their attributes.
 *
 * @author Christopher Ruley
 */
public enum DataType {

    INT(1, 4),
    INT2(2, 8),
    INT3(3, 12),
    INT4(4, 16),
    FLOAT(1, 4),
    FLOAT2(2, 8),
    FLOAT3(3, 12),
    FLOAT4(4, 16);

    /**
     * The number of components.
     */
    private final int componentCount;

    /**
     * The size (in bytes), or the product of the component count and the corresponding bytes.
     */
    private final int size;

    /**
     * Constructs a {@code DataType}.
     *
     * @param componentCount the number of components.
     * @param size           the size (in bytes), or the product of the component count and the corresponding bytes.
     */
    DataType(int componentCount, int size) {
        this.componentCount = componentCount;
        this.size = size;
    }

    /**
     * Gets the number of components.
     *
     * @return the number of components.
     */
    public int getComponentCount() {
        return componentCount;
    }

    /**
     * Gets the size (in bytes), or the product of the component count and the corresponding bytes.
     *
     * @return the size (in bytes).
     */
    public int getSize() {
        return size;
    }

}
