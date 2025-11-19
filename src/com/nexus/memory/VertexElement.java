package com.nexus.memory;

/**
 * A {@code VertexElement} is a characteristic of a vertex, commonly being a position, normal, or texture coordinate.
 *
 * @author Christopher Ruley
 */
public class VertexElement {

    /**
     * The name.
     */
    private String name;

    /**
     * The {@code DataType}.
     */
    private DataType dataType;

    /**
     * Whether data should be normalized or converted directly as fixed-point values. If true, values are mapped to
     * the range [-1, 1] (for signed values) or [0, 1] (for unsigned values) when accessed and converted to floating
     * points.
     */
    private boolean normalized;

    /**
     * The offset (in bytes), or displacement. This value is calculated during the creation of the {@code VertexLayout}.
     */
    int offset;

    /**
     * Constructs an {@code VertexElement}.
     *
     * @param name       the name.
     * @param dataType   the {@code DataType}.
     * @param normalized whether data should be normalized or converted directly as fixed-point values.
     */
    public VertexElement(String name, DataType dataType, boolean normalized) {
        this.name = name;
        this.dataType = dataType;
        this.normalized = normalized;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the {@code DataType}.
     *
     * @return the {@code DataType}.
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * Gets whether this should be normalized or converted directly as fixed-point values. If true, values are mapped to
     * the range [-1, 1] (for signed values) or [0, 1] (for unsigned values) when accessed and converted to floating
     * points.
     *
     * @return whether data should be normalized or converted directly as fixed-point values.
     */
    public boolean isNormalized() {
        return normalized;
    }

    /**
     * Gets the offset (in bytes), or displacement. This value is calculated during the creation of the
     * {@code VertexLayout}.
     *
     * @return the offset (in bytes).
     */
    public int getOffset() {
        return offset;
    }

}
