package com.codex.nexus.memory;

/**
 * Describes the layout within a {@link VertexBuffer}.
 * 
 * @author Christopher Ruley
 */
public class InputLayout {

	/**
	 * The input element(s).
	 */
	private InputElement[] inputElements;

	/**
	 * The stride (in bytes) of a vertex.
	 */
	private int stride;

	/**
	 * Constructs an input layout and calculates the offsets of each input element and the stride.
	 * 
	 * @param inputElements The input element(s).
	 */
	public InputLayout(InputElement... inputElements) {
		this.inputElements = inputElements;

		int offset = 0;

		for (var inputElement : inputElements) {
			inputElement.offset = offset;

			int size = inputElement.getComponentCount() * Float.BYTES;

			offset += size;
			stride += size;
		}
	}

	/**
	 * Gets the input element(s).
	 */
	public InputElement[] getInputElements() {
		return inputElements;
	}

	/**
	 * Gets the stride (in bytes) of a vertex.
	 */
	public int getStride() {
		return stride;
	}

}
