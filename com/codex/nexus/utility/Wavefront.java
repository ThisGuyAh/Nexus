package com.codex.nexus.utility;

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
import com.codex.nexus.model.Material;
import com.codex.nexus.model.Mesh;
import com.codex.nexus.model.Model;
import java.util.ArrayList;
import java.util.List;

import static com.codex.nexus.utility.Documents.*;
import static java.lang.Float.*;
import static java.lang.Integer.*;

public class Wavefront {

    public static final String MTL_NEWMTL = "newmtl";
    public static final String MTL_KA =     "Ka";
    public static final String MTL_KD =     "Kd";
    public static final String MTL_KS =     "Ks";
    public static final String MTL_NS =     "Ns";
    public static final String OBJ_O =      "o";
    public static final String OBJ_V =      "v";
    public static final String OBJ_VT =     "vt";
    public static final String OBJ_VN =     "vn";
    public static final String OBJ_USEMTL = "usemtl";
    public static final String OBJ_F =      "f";

    /**
     * Cannot construct {@code Wavefront}.
     */
    private Wavefront() {
    }

    public static Model open(String mtlPath, String objPath) {
        return new Model(parseOBJ(objPath, parseMTL(mtlPath)).toArray(new Mesh[0]));
    }

    private static List<Material> parseMTL(String mtlPath) {
        List<Material> materials = new ArrayList<>();

        for (var lines : split(MTL_NEWMTL, readFile(mtlPath))) {
            materials.add(generateMaterial(lines));
        }

        return materials;
    }

    private static List<Mesh> parseOBJ(String objPath, List<Material> materials) {
        List<Mesh> meshes = new ArrayList<>();

        for (var lines : split(OBJ_O, readFile(objPath))) {
            meshes.add(generateMesh(materials, lines));
        }

        return meshes;
    }

    private static Material generateMaterial(List<String> lines) {
        String name = null;
        Vector4 ambientColor = null;
        Vector4 diffuseColor = null;
        Vector4 specularColor = null;
        float shininess = 0.0F;

        for (var line : lines) {
            String[] tokens = line.split("\\s+");

            switch (tokens[0]) {
                case MTL_NEWMTL -> {
                    name = tokens[1];
                }
                case MTL_KA -> {
                    ambientColor = new Vector4(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3]),
                        1.0F);
                }
                case MTL_KD -> {
                    diffuseColor = new Vector4(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3]),
                        1.0F);
                }
                case MTL_KS -> {
                    specularColor = new Vector4(parseFloat(tokens[1]), parseFloat(tokens[2]), parseFloat(tokens[3]),
                        1.0F);
                }
                case MTL_NS -> {
                    shininess = parseFloat(tokens[1]);
                }
            }
        }

        return new Material(name, ambientColor, diffuseColor, specularColor, shininess);
    }

    private static Mesh generateMesh(List<Material> materials, List<String> lines) {
        String name = null;
        Material material = new Material();
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
                        if (mat.getName().equals(tokens[1])) {
                            material = mat;
                        }
                    }
                }
                case OBJ_F -> {
                    for (int i = 1; i < 4; i++) {
                        String[] vertexData = tokens[i].split("/");

                        positionIndices.add(parseInt(vertexData[0]) - 1);

                        if (hasTexcoords) {
                            texcoordIndices.add(parseInt(vertexData[1]) - 1);
                        }
                        if (hasNormals) {
                            normalIndices.add(parseInt(vertexData[2]) - 1);
                        }
                    }
                }
            }
        }

        reduce(positionIndices);
        
        /* Notice texture support is commented out - there is a bug associated with supporting textures when a model
           has zero texture indices. The bug causes the normals to be distorted.
         */

        /*if (hasTexcoords) {
            reduce(texcoordIndices);
            reorder(texcoords, positionIndices, texcoordIndices);
        }*/
        if (hasNormals) {
            reduce(normalIndices);
            reorder(normals, positionIndices, normalIndices);
        }

        List<Vector> vertices = new ArrayList<>();

        for (int i = 0; i < positions.size(); i++) {
            vertices.add(positions.get(i));

            /*if (hasTexcoords) {
                //vertices.add(texcoords.get(i));
            }*/
            if (hasNormals) {
                vertices.add(normals.get(i));
            }
        }

        List<VertexElement> vertexElements = new ArrayList<>();

        vertexElements.add(new VertexElement("position", DataType.FLOAT3, false));

        /*if (hasTexcoords) {
           // vertexElements.add(new VertexElement("texcoord", DataType.FLOAT2, false));
        }*/
        if (hasNormals) {
            vertexElements.add(new VertexElement("normal", DataType.FLOAT3, false));
        }

        VertexLayout vertexLayout = new VertexLayout(vertexElements.toArray(new VertexElement[0]));
        VertexBuffer vertexBuffer = new VertexBuffer(vertices.toArray(new Vector[0]), vertexLayout);
        IndexBuffer indexBuffer = new IndexBuffer(positionIndices.stream().mapToInt(i -> i).toArray());
        VertexArray vertexArray = new VertexArray(vertexBuffer);

        vertexArray.setIndexBuffer(indexBuffer);

        return new Mesh(name, vertexArray, material);
    }

    private static void reduce(List<Integer> indices) {
        int minimumIndex = Integer.MAX_VALUE;

        for (var index : indices) {
            minimumIndex = index < minimumIndex ? index : minimumIndex;
        }
        for (int i = 0; i < indices.size(); i++) {
            indices.set(i, indices.get(i) - minimumIndex);
        }
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
