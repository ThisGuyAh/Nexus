package com.codex.nexus.memory;

/**
 * {@code DataType} defines data types and their attributes.
 * 
 * @see InputElement
 * @see InputLayout
 * 
 * @author Christopher Ruley
 */
public enum DataType {

	INT(1, 1 * 4),
	INT2(2, 2 * 4),
	INT3(3, 3 * 4),
	INT4(4, 4 * 4),
	FLOAT(1, 1 * 4),
	FLOAT2(2, 2 * 4),
	FLOAT3(3, 3 * 4),
	FLOAT4(4, 4 * 4);

	/**
	 * The number of components.
	 */
	int componentCount;

	/**
	 * The size (in bytes), or the product of the number of components and the
	 * number of corresponding bytes.
	 */
	int size;

	/**
	 * Constructs a {@code DataType}.
	 * 
	 * @param componentCount the number of components.
	 * @param size the size (in bytes), or the product of the number of
	 * components and the number of corresponding bytes.
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
	 * Gets the size (in bytes), or the product of the number of components and
	 * the number of corresponding bytes.
	 * 
	 * @return the size (in bytes).
	 */
	public int getSize() {
		return size;
	}

}
