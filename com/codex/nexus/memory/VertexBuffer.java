package com.codex.nexus.memory;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.opengl.GL15.*;

public class VertexBuffer {

	private InputLayout inputLayout;
	private float[] data;
	private int handle;

	public VertexBuffer(InputLayout inputLayout, float[] data) {
		this.inputLayout = inputLayout;
		this.data = data;

		handle = glGenBuffers();

		bind();
		glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(data.length).put(data).flip(), GL_STATIC_DRAW);
	}

	public InputLayout getInputLayout() {
		return inputLayout;
	}

	public float[] getData() {
		return data;
	}

	public int getHandle() {
		return handle;
	}

	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, handle);
	}

	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void delete() {
		glDeleteBuffers(handle);
	}

}
