package com.codex.nexus.memory;

/**
 * Describes a single input element of vertex data within the {@link InputLayout}.
 * 
 * @author Christopher Ruley
 */
public class InputElement {

	/**
	 * The name of this input element.
	 */
	private String name;

	/**
	 * The component count of this input element.
	 */
	private int componentCount;

	/**
	 * Whether or not this input element is normalized.
	 */
	private boolean normalized;

	/**
	 * The offset (in bytes) of this input element. This is used to determine the position of the input element relative
	 * to the start of a buffer. This field's value is calculated during the creation of the {@link InputLayout}.
	 */
	int offset;

	/**
	 * Constructs an input element.
	 * 
	 * @param name The name of this input element.
	 * @param componentCount The component count of this input element.
	 * @param normalized Whether or not this input element is nornalized.
	 */
	public InputElement(String name, int componentCount, boolean normalized) {
		this.name = name;
		this.componentCount = componentCount;
		this.normalized = normalized;
	}

	/**
	 * Gets the name of this input element.
	 * 
	 * @return The name of this input element.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the component count of this input element.
	 * 
	 * @return The component count of this input element.
	 */
	public int getComponentCount() {
		return componentCount;
	}

	/**
	 * Gets whether or not this input element is normalized.
	 * 
	 * @return Whether or not this input element is normalized.
	 */
	public boolean isNormalized() {
		return normalized;
	}

	/**
	 * Gets the offset, in bytes, of this input element. This is used to determine the position of the input element
	 * relative to the start of a buffer. This field's value is calculated during the creation of the
	 * {@link InputLayout}.
	 * 
	 * @return The offset, in bytes, of this input element.
	 */
	public int getOffset() {
		return offset;
	}

}
