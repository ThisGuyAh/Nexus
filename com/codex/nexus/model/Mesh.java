package com.codex.nexus.model;

import com.codex.nexus.memory.IndexBuffer;
import com.codex.nexus.memory.VertexArray;
import com.codex.nexus.memory.VertexBuffer;

public class Mesh {

	private String name;
	private VertexArray vertexArray;
	private VertexBuffer vertexBuffer;
	private IndexBuffer indexBuffer;
	private Material material;

	public Mesh(String name, VertexArray vertexArray, VertexBuffer vertexBuffer, IndexBuffer indexBuffer,
			Material material) {
		this.name = name;
		this.vertexArray = vertexArray;
		this.vertexBuffer = vertexBuffer;
		this.indexBuffer = indexBuffer;
		this.material = material;
	}

	public String getName() {
		return name;
	}

	public VertexArray getVertexArray() {
		return vertexArray;
	}

	public VertexBuffer getVertexBuffer() {
		return vertexBuffer;
	}

	public IndexBuffer getIndexBuffer() {
		return indexBuffer;
	}

	public Material getMaterial() {
		return material;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}

	public void bind() {
		vertexArray.bind();
	}

	public void unbind() {
		vertexArray.unbind();
	}

	public void delete() {
		vertexArray.delete();
		vertexBuffer.delete();
		indexBuffer.delete();
	}

}
