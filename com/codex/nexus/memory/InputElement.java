package com.codex.nexus.memory;

public class InputElement {

	private String name;
	private int componentCount;

	public InputElement(String name, int componentCount) {
		this.name = name;
		this.componentCount = componentCount;
	}
	
	public String getName() {
		return name;
	}

	public int getComponentCount() {
		return componentCount;
	}

}
