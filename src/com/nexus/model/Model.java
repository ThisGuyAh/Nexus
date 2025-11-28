package com.nexus.model;

import com.nexus.memory.VertexArray;

public class Model {

    private final VertexArray vertexArray;
    private final Mesh[] meshes;

    public Model(VertexArray vertexArray, Mesh[] meshes) {
        this.vertexArray = vertexArray;
        this.meshes = meshes;
    }

    public VertexArray getVertexArray() {
        return vertexArray;
    }

    public Mesh[] getMeshes() {
        return meshes;
    }

    public void bind() {
        vertexArray.bind();
    }

    public void unbind() {
        vertexArray.unbind();
    }

}