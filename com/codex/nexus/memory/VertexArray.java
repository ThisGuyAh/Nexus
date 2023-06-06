package com.codex.nexus.memory;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Stores vertex buffers (and optionally) an index buffer for later referencing.
 * 
 * @author Christopher Ruley
 */
public class VertexArray {

	/**
	 * The vertex buffers associated with this vertex array.
	 */
	private VertexBuffer[] vertexBuffers;

	/**
	 * The unique identifier for this vertex array.
	 */
	private int handle;

	/**
	 * The (optional) index buffer associated with this vertex array.
	 */
	private IndexBuffer indexBuffer;

	/**
	 * Constructs this vertex array and sets the vertex attributes according to the provided vertex buffers.
	 * 
	 * @param vertexBuffers The vertex buffer(s) to be associated with this vertex array.
	 */
	public VertexArray(VertexBuffer... vertexBuffers) {
		this.vertexBuffers = vertexBuffers;

		handle = glGenVertexArrays();

		bind();

		for (var vertexBuffer : vertexBuffers) {
			vertexBuffer.bind();

			InputLayout inputLayout = vertexBuffer.getInputLayout();
			InputElement[] inputElements = inputLayout.getInputElements();

			for (int i = 0; i < inputElements.length; i++) {
				InputElement inputElement = inputElements[i];

				glEnableVertexAttribArray(i);
				glVertexAttribPointer(i, inputElement.getComponentCount(), GL_FLOAT, inputElement.isNormalized(),
						inputLayout.getStride(), inputElement.getOffset());
			}
		}

		unbind();
	}

	/**
	 * Gets the (optional) index buffer associated with this vertex array. This value may be null if this vertex array
	 * does not use an index buffer.
	 * 
	 * @return The (optional) index buffer assocated with this vertex array.
	 */
	public IndexBuffer getIndexBuffer() {
		return indexBuffer;
	}

	/**
	 * Sets the index buffer and associates it with this vertex array.
	 * 
	 * @param indexBuffer The index buffer to be associated with this vertex array.
	 */
	public void setIndexBuffer(IndexBuffer indexBuffer) {
		this.indexBuffer = indexBuffer;

		bind();
		indexBuffer.bind();
		unbind();
	}

	/**
	 * Binds this vertex array.
	 */
	public void bind() {
		glBindVertexArray(handle);
	}

	/**
	 * Unbinds this vertex array.
	 */
	public void unbind() {
		glBindVertexArray(0);
	}

}
