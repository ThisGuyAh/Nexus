package com.codex.nexus.memory;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

	private VertexBuffer[] vertexBuffers;
	private int handle;
	private IndexBuffer indexBuffer;

	public VertexArray(VertexBuffer... vertexBuffers) {
		this.vertexBuffers = vertexBuffers;
		handle = glGenVertexArrays();

		bind();

		for (VertexBuffer vertexBuffer : vertexBuffers) {
			vertexBuffer.bind();

			InputLayout inputLayout = vertexBuffer.getInputLayout();
			InputElement[] inputElements = inputLayout.getInputElements();
			int offset = 0;

			for (int i = 0; i < inputElements.length; i++) {
				int componentCount = inputElements[i].getComponentCount();

				glVertexAttribPointer(i, componentCount, GL_FLOAT, false, inputLayout.getStride(), offset);
				offset += componentCount * Float.BYTES;
			}
		}

		unbind();
	}

	public VertexBuffer[] getVertexBuffers() {
		return vertexBuffers;
	}
	
	public int getHandle() {
		return handle;
	}

	public IndexBuffer getIndexBuffer() {
		return indexBuffer;
	}

	public void setIndexBuffer(IndexBuffer indexBuffer) {
		this.indexBuffer = indexBuffer;

		bind();
		indexBuffer.bind();
		unbind();
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
