package com.codex.nexus.memory;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * {@code VertexBuffer} stores vertex data and an {@code InputLayout}.
 * 
 * @see InputLayout
 * @see VertexArray
 * 
 * @author Christopher Ruley
 */
public class VertexBuffer {

	/**
	 * The stored data.
	 */
	private float[] data;

	/**
	 * The {@code InputLayout}.
	 */
	private InputLayout inputLayout;

	/**
	 * The unique identifier.
	 */
	private int handle;

	/**
	 * Constructs a {@code VertexBuffer}.
	 * 
	 * @param data the data to store.
	 * @param inputLayout the {@code InputLayout}.
	 */
	public VertexBuffer(float[] data, InputLayout inputLayout) {
		this.data = data;
		this.inputLayout = inputLayout;
		handle = glGenBuffers();

		bind();
		glBufferData(GL_ARRAY_BUFFER,
				createFloatBuffer(data.length).put(data).flip(),
				GL_STATIC_DRAW);
	}

	/**
	 * Gets the stored data.
	 * 
	 * @return the stored data.
	 */
	public float[] getData() {
		return data;
	}

	/**
	 * Gets the {@code InputLayout}.
	 * 
	 * @return the {@code InputLayout}.
	 */
	public InputLayout getInputLayout() {
		return inputLayout;
	}

	/**
	 * Sets the stored data.
	 * 
	 * @param data the data to store.
	 */
	public void setData(float[] data) {
		this.data = data;

		bind();
		glBufferSubData(GL_ARRAY_BUFFER, 0, data);
	}

	/**
	 * Binds this {@code VertexBuffer}.
	 */
	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, handle);
	}

	/**
	 * Unbinds this {@code VertexBuffer}.
	 */
	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	/**
	 * Deletes this {@code VertexBuffer}.
	 */
	public void delete() {
		glDeleteBuffers(handle);
	}

}
