package com.codex.nexus.memory;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class VertexBuffer {

	private float[] vertices;
	private InputLayout inputLayout;
	private int handle;

	public VertexBuffer(float[] vertices, InputLayout inputLayout) {
		this.vertices = vertices;
		this.inputLayout = inputLayout;

		handle = glGenBuffers();

		bind();
		glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(vertices.length).put(vertices).flip(), GL_STATIC_DRAW);

		InputElement[] inputElements = inputLayout.getInputElements();
		int offset = 0;

		for (int i = 0; i < inputElements.length; i++) {
			int componentCount = inputElements[i].getComponentCount();

			glVertexAttribPointer(i, componentCount, GL_FLOAT, false, inputLayout.getSize(), offset);
			offset += componentCount * Float.BYTES;
		}

	}

	public float[] getVertices() {
		return vertices;
	}

	public InputLayout getInputLayout() {
		return inputLayout;
	}

	public long getHandle() {
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
