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

    public static final String MTL_NEWMTL = "newmtl";
    public static final String MTL_KA = "Ka";
    public static final String MTL_KD = "Kd";
    public static final String MTL_KS = "Ks";
    public static final String MTL_NS = "Ns";
    public static final String OBJ_O = "o";
    public static final String OBJ_V = "v";
    public static final String OBJ_VT = "vt";
    public static final String OBJ_VN = "vn";
    public static final String OBJ_USEMTL = "usemtl";
    public static final String OBJ_F = "f";

    /**
     * {@code WavefrontLoader} cannot be instantiated.
     */
    private WavefrontLoader() {
    }

    public static Model generateModel(String mtlPath, String objPath) {
        Mesh[] meshes = parseOBJFile(parseMTLFile(mtlPath), objPath).toArray(new Mesh[0]);

        return new Model(meshes);
    }

    private static List<Material> parseMTLFile(String path) {
        List<Material> materials = new ArrayList<>();

        for (var lines : split(MTL_NEWMTL, readFile(path))) {
            materials.add(createMaterial(lines));
        }

        return materials;
    }

    private static List<Mesh> parseOBJFile(List<Material> materials, String path) {
        List<Mesh> meshes = new ArrayList<>();

        for (var lines : split(OBJ_O, readFile(path))) {
            meshes.add(createMesh(materials, lines));
        }

        return meshes;
    }

    private static Material createMaterial(List<String> lines) {
        String name = null;
        Vector4 ambientColor = null;
        Vector4 diffuseColor = null;
        Vector4 specularColor = null;
        float shininess = 0.0F;

        for (var line : lines) {
            String[] tokens = line.split("\\s+");

            switch (tokens[0]) {
                case MTL_NEWMTL -> name = tokens[1];
                case MTL_KA ->
                    ambientColor = new Vector4(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3]),
                        1.0F);
                case MTL_KD ->
                    diffuseColor = new Vector4(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3]),
                        1.0F);
                case MTL_KS ->
                    specularColor = new Vector4(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3]),
                        1.0F);
                case MTL_NS -> shininess = parseFloat(tokens[1]);
            }
        }

        return new Material(name, ambientColor, diffuseColor, specularColor, shininess);
    }

    private static Mesh createMesh(List<Material> materials, List<String> lines) {
        String name = null;
        Material material = null;
        List<Vector> positions = new ArrayList<>();
        List<Vector> texcoords = new ArrayList<>();
        List<Vector> normals = new ArrayList<>();
        List<Integer> positionIndices = new ArrayList<>();
        List<Integer> texcoordIndices = new ArrayList<>();
        List<Integer> normalIndices = new ArrayList<>();
        boolean hasTexcoords = false;
        boolean hasNormals = false;

        for (var line : lines) {
            String[] tokens = line.split("\\s+");

            switch (tokens[0]) {
                case OBJ_O -> {
                    name = tokens[1];
                }
                case OBJ_V -> {
                    positions.add(new Vector3(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3])));
                }
                case OBJ_VT -> {
                    texcoords.add(new Vector2(parseFloat(tokens[1]), 1 - parseFloat(tokens[2])));
                    hasTexcoords = true;
                }
                case OBJ_VN -> {
                    normals.add(new Vector3(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3])));
                    hasNormals = true;
                }
                case OBJ_USEMTL -> {
                    for (var mat : materials) {
                        material = mat.getName().equals(tokens[1]) ? mat : new Material();
                    }
                }
                case OBJ_F -> {
                    String[] vertexData1 = tokens[1].split("/");
                    String[] vertexData2 = tokens[2].split("/");
                    String[] vertexData3 = tokens[3].split("/");

                    positionIndices.add(parseInt(vertexData1[0]) - 1);
                    positionIndices.add(parseInt(vertexData2[0]) - 1);
                    positionIndices.add(parseInt(vertexData3[0]) - 1);

                    if (hasTexcoords) {
                        texcoordIndices.add(parseInt(vertexData1[1]) - 1);
                        texcoordIndices.add(parseInt(vertexData2[1]) - 1);
                        texcoordIndices.add(parseInt(vertexData3[1]) - 1);
                    }
                    if (hasNormals) {
                        normalIndices.add(parseInt(vertexData1[2]) - 1);
                        normalIndices.add(parseInt(vertexData2[2]) - 1);
                        normalIndices.add(parseInt(vertexData3[2]) - 1);
                    }
                }
            }
        }

        if (hasTexcoords) {
            reorder(texcoords, positionIndices, texcoordIndices);
        }
        if (hasNormals) {
            reorder(normals, positionIndices, normalIndices);
        }

        List<Vector> vertices = new ArrayList<>();

        for (int i = 0; i < positions.size(); i++) {
            vertices.add(positions.get(i));

            if (hasTexcoords) {
                vertices.add(texcoords.get(i));
            }
            if (hasNormals) {
                vertices.add(normals.get(i));
            }
        }

        VertexLayout vertexLayout = new VertexLayout(new VertexElement[] {
            new VertexElement("position", DataType.FLOAT3, false),
            new VertexElement("texcoord", DataType.FLOAT2, false),
            new VertexElement("normal", DataType.FLOAT3, false)
        });
        VertexBuffer vertexBuffer = new VertexBuffer(vertices.toArray(new Vector[0]), vertexLayout);
        IndexBuffer indexBuffer = new IndexBuffer(positionIndices.stream().mapToInt(i -> i).toArray());
        VertexArray vertexArray = new VertexArray(vertexBuffer);

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
