package com.codex.nexus.memory;

public class InputLayout {

	private InputElement[] inputElements;
	private int size;

	public InputLayout(InputElement[] inputElements) {
		this.inputElements = inputElements;

		for (InputElement inputElement : inputElements) {
			size += inputElement.getComponentCount() * Float.BYTES;
		}
	}

	public InputElement[] getInputElements() {
		return inputElements;
	}

	public int getSize() {
		return size;
	}

}
