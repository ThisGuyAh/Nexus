package com.codex.nexus.memory;

public class InputLayout {

	private InputElement[] inputElements;
	private int stride;

	public InputLayout(InputElement... inputElements) {
		this.inputElements = inputElements;

		for (InputElement inputElement : inputElements) {
			stride += inputElement.getComponentCount() * Float.BYTES;
		}
	}

	public InputElement[] getInputElements() {
		return inputElements;
	}

	public int getStride() {
		return stride;
	}

}
