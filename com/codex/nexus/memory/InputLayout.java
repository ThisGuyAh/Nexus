package com.codex.nexus.memory;

/**
 * An {@code InputLayout} stores one or more {@code InputElement}s to provide
 * an interpretation of vertex data stored in a {@code VertexBuffer}.
 * 
 * @see DataType
 * @see InputElement
 * @see VertexBuffer
 * 
 * @author Christopher Ruley
 */
public class InputLayout {

	/**
	 * The {@code InputElement} array.
	 */
	private InputElement[] inputElements;

	/**
	 * The stride (in bytes), or the total of each {@code InputElement}'s size.
	 */
	private int stride;

	/**
	 * Constructs an {@code InputLayout} and calculates the offsets of each
	 * {@code InputElement} and the stride.
	 * 
	 * @param inputElements the {@code InputElement} array.
	 */
	public InputLayout(InputElement... inputElements) {
		this.inputElements = inputElements;

		int offset = 0;

		for (var inputElement : inputElements) {
			inputElement.offset = offset;

			int size = inputElement.getDataType().getSize();

			offset += size;
			stride += size;
		}
	}

	/**
	 * Gets the {@code InputElement} array.
	 * 
	 * @return the {@code InputElement} array.
	 */
	public InputElement[] getInputElements() {
		return inputElements;
	}

	/**
	 * Gets the stride (in bytes), or the total of each {@code InputElement}'s
	 * size.
	 * 
	 * @return the stride (in bytes).
	 */
	public int getStride() {
		return stride;
	}

}
