package com.codex.nexus.model;

import com.codex.nexus.math.Vector;
import com.codex.nexus.math.Vector2;
import com.codex.nexus.math.Vector3;
import com.codex.nexus.math.Vector4;
import com.codex.nexus.memory.DataType;
import com.codex.nexus.memory.IndexBuffer;
import com.codex.nexus.memory.VertexArray;
import com.codex.nexus.memory.VertexBuffer;
import com.codex.nexus.memory.VertexElement;
import com.codex.nexus.memory.VertexLayout;

import java.util.ArrayList;
import java.util.List;

import static com.codex.nexus.utility.Documents.*;
import static java.lang.Float.*;
import static java.lang.Integer.*;

public class WavefrontLoader {

    private WavefrontLoader() {
    }

    public static Model parseWavefrontFile(String path) {
        List<List<String>> groups = split("o ", readFile(path));
        List<Mesh> meshes = new ArrayList<>();

        for (var group : groups) {
            meshes.add(constructMesh(group));
        }

        return new Model(meshes.toArray(new Mesh[0]));
    }

    private static Mesh constructMesh(List<String> lines) {
        String name = null;
        List<Vector> positions = new ArrayList<>();
        List<Vector> texcoords = new ArrayList<>();
        List<Vector> normals = new ArrayList<>();
        List<Integer> positionIndices = new ArrayList<>();
        List<Integer> texcoordIndices = new ArrayList<>();
        List<Integer> normalIndices = new ArrayList<>();

        for (var line : lines) {
            String[] tokens = line.split("\\s+");

            switch (tokens[0]) {
                case "o" ->
                    name = tokens[1];
                case "v" ->
                    positions.add(new Vector3(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3])));
                case "vt" -> 
                    texcoords.add(new Vector2(parseFloat(tokens[1]), 1 - parseFloat(tokens[2])));
                case "vn" ->
                    normals.add(new Vector3(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3])));
                case "f" -> {
                    String[] vertexData1 = tokens[1].split("/");
                    String[] vertexData2 = tokens[2].split("/");
                    String[] vertexData3 = tokens[3].split("/");

                    positionIndices.add(parseInt(vertexData1[0]) - 1);
                    positionIndices.add(parseInt(vertexData2[0]) - 1);
                    positionIndices.add(parseInt(vertexData3[0]) - 1);
                    texcoordIndices.add(parseInt(vertexData1[1]) - 1);
                    texcoordIndices.add(parseInt(vertexData2[1]) - 1);
                    texcoordIndices.add(parseInt(vertexData3[1]) - 1);
                    normalIndices.add(parseInt(vertexData1[2]) - 1);
                    normalIndices.add(parseInt(vertexData2[2]) - 1);
                    normalIndices.add(parseInt(vertexData3[2]) - 1);
                }
            }
        }

        reorder(texcoords, positionIndices, texcoordIndices);
        reorder(normals, positionIndices, normalIndices);

        List<Vector> vertices = new ArrayList<>();

        for (int i = 0; i < positions.size(); i++) {
            vertices.add(positions.get(i));
            vertices.add(texcoords.get(i));
            vertices.add(normals.get(i));
        }

        //Test 
        VertexLayout vertexLayout = new VertexLayout(new VertexElement[] {
            new VertexElement("position", DataType.FLOAT3, false),
            new VertexElement("texcoord", DataType.FLOAT2, false),
            new VertexElement("normal", DataType.FLOAT3, false)
        });
        VertexBuffer vertexBuffer = new VertexBuffer(vertices.toArray(new Vector[0]), vertexLayout);
        IndexBuffer indexBuffer = new IndexBuffer(positionIndices.stream().mapToInt((Integer i) -> i).toArray());
        VertexArray vertexArray = new VertexArray(vertexBuffer);
        Material material = new Material("null", new Vector4(), new Vector4(), new Vector4(), 1.0F);

        vertexArray.setIndexBuffer(indexBuffer);

        return new Mesh(name, vertexArray, material);
    }

    private static void reorder(List<Vector> data, List<Integer> positionIndices, List<Integer> dataIndices) {
        List<Vector> reorderedData = new ArrayList<>();
        int length = positionIndices.size();

        for (int i = 0; i < length; i++) {
            reorderedData.add(null);
        }

        for (int i = 0; i < length; i++) {
            int positionIndex = positionIndices.get(i);

            reorderedData.set(positionIndex, data.get(dataIndices.get(i)));
        }

        data.clear();
        data.addAll(reorderedData);
    }

}
