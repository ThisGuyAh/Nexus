package com.nexus.utility;

import com.nexus.math.Vector;
import com.nexus.math.Vector2;
import com.nexus.math.Vector3;
import com.nexus.math.Vector4;
import com.nexus.memory.*;
import com.nexus.model.Material;
import com.nexus.model.Mesh;
import com.nexus.model.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nexus.utility.Documents.readFile;
import static com.nexus.utility.Documents.split;
import static java.lang.Float.parseFloat;

/**
 * {@code OBJ} is a Wavefront .obj and .mtl loader. It functions properly, however it is not in a finalized and
 * polished state.
 *
 * @author Christopher Ruley
 */
public class OBJ {

    public static final String MTL_NEWMTL = "newmtl";
    public static final String MTL_KA     = "Ka";
    public static final String MTL_KD     = "Kd";
    public static final String MTL_KS     = "Ks";
    public static final String MTL_NS     = "Ns";
    public static final String OBJ_O      = "o";
    public static final String OBJ_V      = "v";
    public static final String OBJ_VT     = "vt";
    public static final String OBJ_VN     = "vn";
    public static final String OBJ_USEMTL = "usemtl";
    public static final String OBJ_F      =  "f";

    /**
     * Cannot construct {@code OBJ}.
     */
    private OBJ() {
    }

    public static Model open(String mtlPath, String objPath) {
        return parseOBJ(objPath, parseMTL(mtlPath));
    }

    private static List<Material> parseMTL(String mtlPath) {
        List<Material> materials = new ArrayList<>();

        for (var lines : split(MTL_NEWMTL, readFile(mtlPath))) {
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

            materials.add(new Material(name, ambientColor, diffuseColor, specularColor, shininess));
        }

        return materials;
    }

    private static Model parseOBJ(String objPath, List<Material> materials) {
        List<String> lines = readFile(objPath);

        List<Vector3> positions = new ArrayList<>();
        List<Vector2> texcoords =  new ArrayList<>();
        List<Vector3> normals = new ArrayList<>();

        boolean hasTexcoords = false;
        boolean hasNormals = false;

        List<Vector> vertices = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        Map<String, Integer> vertexMap = new HashMap<>();
        int[] nextVertexIndex = new int[] { 0 };

        List<Mesh> meshes = new ArrayList<>();
        String currentObjectName = "";
        Material currentMaterial = new Material();
        int currentIndexStart = 0;

        for (var line : lines) {
            if (line.isBlank()) {
                continue;
            }

            String[] tokens = line.split("\\s+");

            if (tokens.length == 0) {
                continue;
            }

            switch (tokens[0]) {
                case OBJ_O -> {
                    closeCurrentMesh(indices, meshes, currentObjectName, currentMaterial, currentIndexStart);

                    currentObjectName = tokens[1];
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
                    closeCurrentMesh(indices, meshes, currentObjectName, currentMaterial, currentIndexStart);

                    for (var mat : materials) {
                        if (mat.getName().equals(tokens[1])) {
                            currentMaterial = mat;
                        }
                    }
                }
                case OBJ_F -> {
                    for (int i = 1; i <= 3; i++) {
                        String[] vertexData = tokens[i].split("/");

                        int posIndex = Integer.parseInt(vertexData[0]) - 1;
                        int texIndex = -1;
                        int normIndex = -1;

                        if (vertexData.length > 1 && !vertexData[1].isEmpty()) {
                            texIndex = Integer.parseInt(vertexData[1]) - 1;
                        }
                        if (vertexData.length > 2 && !vertexData[2].isEmpty()) {
                            normIndex = Integer.parseInt(vertexData[2]) - 1;
                        }

                        int finalIndex = getOrCreateVertexIndex(
                                posIndex,
                                texIndex,
                                normIndex,
                                hasTexcoords,
                                hasNormals,
                                positions,
                                texcoords,
                                normals,
                                vertices,
                                vertexMap,
                                nextVertexIndex
                        );

                        indices.add(finalIndex);
                    }
                }
            }
        }

        closeCurrentMesh(indices, meshes, currentObjectName, currentMaterial, currentIndexStart);

        // Build layout + buffers + model, same as before
        List<VertexElement> vertexElements = new ArrayList<>();
        vertexElements.add(new VertexElement("position", DataType.FLOAT3, false));

        if (hasTexcoords) {
            vertexElements.add(new VertexElement("texcoord", DataType.FLOAT2, false));
        }
        if (hasNormals) {
            vertexElements.add(new VertexElement("normal", DataType.FLOAT3, false));
        }

        VertexLayout layout       = new VertexLayout(vertexElements.toArray(new VertexElement[0]));
        VertexBuffer vertexBuffer = new VertexBuffer(vertices.toArray(new Vector[0]), layout);
        VertexArray vertexArray   = new VertexArray(vertexBuffer);
        IndexBuffer indexBuffer   = new IndexBuffer(indices.stream().mapToInt(i -> i).toArray());
        vertexArray.setIndexBuffer(indexBuffer);

        Mesh[] meshArray = meshes.toArray(new Mesh[0]);

        return new Model(vertexArray, meshArray);
    }

    private static int getOrCreateVertexIndex(int posIndex, int texIndex, int normIndex, boolean hasTexcoords,
                                              boolean hasNormals, List<Vector3> positions, List<Vector2> texcoords,
                                              List<Vector3> normals, List<Vector> vertices, Map<String,
                    Integer> vertexMap, int[] nextVertexIndexHolder) {
        String key = posIndex + "/" + texIndex + "/" + normIndex;

        Integer existing = vertexMap.get(key);
        if (existing != null) {
            return existing;
        }

        // Create new vertex
        Vector3 position = positions.get(posIndex);
        vertices.add(position);

        if (hasTexcoords && texIndex >= 0) {
            vertices.add(texcoords.get(texIndex));
        }
        if (hasNormals && normIndex >= 0) {
            vertices.add(normals.get(normIndex));
        }

        int newIndex = nextVertexIndexHolder[0]++;

        vertexMap.put(key, newIndex);

        return newIndex;
    }

    private static void closeCurrentMesh(List<Integer> indices, List<Mesh> meshes, String name, Material material,
                                         int indexStart) {
        int currentIndexCount = indices.size() - indexStart;

        if (currentIndexCount > 0) {
            meshes.add(new Mesh(name, material, indexStart, currentIndexCount));
        }
    }

}