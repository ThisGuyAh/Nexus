package com.codex.nexus.model;

import com.codex.nexus.memory.VertexArray;

public class Mesh {

    private String name;
    private VertexArray vertexArray;
    private Material material;
    private int vertexCount;

    public Mesh(String name, VertexArray vertexArray, Material material) {
        this.name = name;
        this.vertexArray = vertexArray;
        this.material = material;
        vertexCount = vertexArray.getIndexBuffer().getCount();
    }

    public String getName() {
        return name;
    }

    public VertexArray getVertexArray() {
        return vertexArray;
    }

    public Material getMaterial() {
        return material;
    }

    public int getVertexCount() {
        return vertexCount;
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
    }

}
