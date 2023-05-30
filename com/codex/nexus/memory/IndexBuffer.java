package com.codex.nexus.memory;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.opengl.GL15.*;

public class IndexBuffer {

	private int[] indices;
	private int handle;

	public IndexBuffer(int[] indices) {
		this.indices = indices;

		handle = glGenBuffers();

		bind();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createIntBuffer(indices.length).put(indices).flip(), GL_STATIC_DRAW);
	}

	public int[] getIndices() {
		return indices;
	}

	public int getHandle() {
		return handle;
	}

	public void bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, handle);
	}

	public void unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void delete() {
		glDeleteBuffers(handle);
	}

}
