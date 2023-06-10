package com.codex.nexus.memory;

/**
 * An {@code InputElement} is a description of a vertex attribute.
 * 
 * <p>
 * This is commonly describing a position, normal, texture coordinate, or color.
 * 
 * @see DataType
 * @see InputLayout
 * 
 * @author Christopher Ruley
 */
public class InputElement {

	/**
	 * The name.
	 */
	private String name;

	/**
	 * The {@code DataType}.
	 */
	private DataType dataType;

	/**
	 * Whether or not this should be normalized or converted directly as
	 * fixed-point values. If true, values are mapped to the range [-1, 1] (for
	 * signed values) or [0, 1] (for unsigned values) when accessed and
	 * converted to floating points.
	 */
	private boolean normalized;

	/**
	 * The offset (in bytes), or displacement. This value is calculated during
	 * the creation of the {@code InputLayout}.
	 */
	int offset;

	/**
	 * Constructs an {@code InputElement}.
	 * 
	 * @param name the name.
	 * @param dataType the {@code DataType}.
	 * @param normalized whether or not this is normalized.
	 */
	public InputElement(String name, DataType dataType, boolean normalized) {
		this.name = name;
		this.dataType = dataType;
		this.normalized = normalized;
	}

	/**
	 * Gets the name
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
	 * Gets whether or not this should be normalized or converted directly as
	 * fixed-point values. If true, values are mapped to the range [-1, 1] (for
	 * signed values) or [0, 1] (for unsigned values) when accessed and
	 * converted to floating points.
	 * 
	 * @return whether or not this should be normalized or converted directly as
	 * fixed-point values.
	 */
	public boolean isNormalized() {
		return normalized;
	}

	/**
	 * Gets the offset (in bytes), or displacement. This value is calculated
	 * during the creation of the {@code InputLayout}.
	 * 
	 * @return the offset (in bytes).
	 */
	public int getOffset() {
		return offset;
	}

}
