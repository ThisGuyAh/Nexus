package com.codex.nexus.memory;

import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

	private int handle;

	public VertexArray() {
		handle = glGenVertexArrays();

		bind();
	}

	public void bind() {
		glBindVertexArray(handle);
	}

	public void unbind() {
		glBindVertexArray(0);
	}

	public void delete() {
		glDeleteVertexArrays(handle);
	}

}
