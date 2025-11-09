package com.createx.nexus.render;

import com.createx.nexus.math.Matrix2;
import com.createx.nexus.math.Matrix3;
import com.createx.nexus.math.Matrix4;
import com.createx.nexus.math.Vector2;
import com.createx.nexus.math.Vector3;
import com.createx.nexus.math.Vector4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.createx.nexus.utility.Documents.*;
import static com.createx.nexus.utility.Memory.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * A {@code ShaderProgram} contains at least two {@code Shader}s belonging to the vertex and fragment pipeline stages,
 * and optionally additional {@code Shader}s.
 *
 * @author Christopher Ruley
 */
public class ShaderProgram {

    /**
     * The {@code Shader} array.
     */
    private Shader[] shaders;

    /**
     * The unique identification.
     */
    private int handle;

    /**
     * The map used to cache uniform locations for quick retrieval.
     */
    private Map<String, Integer> uniformLocations;

    /**
     * Constructs a {@code ShaderProgram} with the specified {@code Shader}s. A {@code ShaderProgram} should be
     * constructed with at least two {@code Shader}s belonging to the vertex and fragment pipeline stages.
     *
     * @param shaders the {@code Shader} array.
     */
    public ShaderProgram(Shader... shaders) {
        if (!isPipelineComplete(shaders)) {
            for (var shader : shaders) {
                shader.delete();
            }

            throw new RuntimeException();
        }

        this.shaders = shaders;
        handle = glCreateProgram();
        uniformLocations = new HashMap<>();

        for (var shader : shaders) {
            glAttachShader(handle, shader.getHandle());
        }

        glLinkProgram(handle);

        if (glGetProgrami(handle, GL_LINK_STATUS) == GL_FALSE) {
            String errorMessage = glGetProgramInfoLog(handle);

            glDeleteProgram(handle);

            for (var shader : shaders) {
                shader.delete();
            }

            throw new RuntimeException(errorMessage);
        }

        for (var shader : shaders) {
            glDetachShader(handle, shader.getHandle());
        }
    }

    /**
     * Constructs a {@code ShaderProgram} with the specified file path.
     *
     * @param path the file path.
     */
    public ShaderProgram(String path) {
        this(getShadersFromFile(path));
    }

    /**
     * Gets the {@code Shader} array.
     *
     * @return the {@code Shader} array.
     */
    public Shader[] getShaders() {
        return shaders;
    }

    /**
     * Gets the unique identification.
     *
     * @return the unique identification.
     */
    public int getHandle() {
        return handle;
    }

    /**
     * Gets whether the pipeline is complete, or when at least two shaders belong to the vertex and fragment pipeline
     * stages.
     *
     * @param shaders the {@code Shader} array.
     * @return whether the pipeline is complete.
     */
    private boolean isPipelineComplete(Shader[] shaders) {
        if (shaders.length < 2) {
            return false;
        }

        boolean vertex = false;
        boolean fragment = false;

        for (var shader : shaders) {
            PipelineStage pipelineStage = shader.getPipelineStage();

            if (pipelineStage == PipelineStage.VERTEX) {
                vertex = true;
            } else if (pipelineStage == PipelineStage.FRAGMENT) {
                fragment = true;
            }
        }

        return vertex && fragment;
    }

    /**
     * Gets the array of {@code Shader}s from a file.
     *
     * @param path the file path.
     * @return the array of {@code Shader}s.
     */
    private static Shader[] getShadersFromFile(String path) {
        List<List<String>> groups = split("#type", readFile(path));
        List<Shader> shaders = new ArrayList<>();

        for (var group : groups) {
            String firstLine = group.get(0);

            group.remove(0);

            String source = concatenate(group);

            switch (firstLine) {
                case "#type vertex"     -> shaders.add(new Shader(PipelineStage.VERTEX, source));
                case "#type geometry"   -> shaders.add(new Shader(PipelineStage.GEOMETRY, source));
                case "#type control"    -> shaders.add(new Shader(PipelineStage.CONTROL, source));
                case "#type evaluation" -> shaders.add(new Shader(PipelineStage.EVALUATION, source));
                case "#type fragment"   -> shaders.add(new Shader(PipelineStage.FRAGMENT, source));
                case "#type compute"    -> shaders.add(new Shader(PipelineStage.COMPUTE, source));
            }
        }

        return shaders.toArray(new Shader[0]);
    }

    /**
     * Gets the cached uniform location. The uniform location is queried and cached if it doesn't exist.
     *
     * @param name the name of the uniform.
     * @return the cached uniform location.
     */
    private int getUniformLocation(String name) {
        if (!uniformLocations.containsKey(name)) {
            uniformLocations.put(name, glGetUniformLocation(handle, name));
        }

        return uniformLocations.get(name);
    }

    /**
     * Uploads an int uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the int value to be uploaded.
     */
    public void uploadUniform(String name, int value) {
        glUniform1i(getUniformLocation(name), value);
    }

    /**
     * Uploads an int array uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the int array value to be uploaded.
     */
    public void uploadUniform(String name, int[] value) {
        glUniform1iv(getUniformLocation(name), value);
    }

    /**
     * Uploads a float uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the float value to be uploaded.
     */
    public void uploadUniform(String name, float value) {
        glUniform1f(getUniformLocation(name), value);
    }

    /**
     * Uploads a float array uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the float array value to be uploaded.
     */
    public void uploadUniform(String name, float[] value) {
        glUniform1fv(getUniformLocation(name), value);
    }

    /**
     * Uploads a {@code Vector2} uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the {@code Vector2} value to be uploaded.
     */
    public void uploadUniform(String name, Vector2 value) {
        glUniform2f(getUniformLocation(name), value.x, value.y);
    }

    /**
     * Uploads a {@code Vector3} uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the {@code Vector3} value to be uploaded.
     */
    public void uploadUniform(String name, Vector3 value) {
        glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
    }

    /**
     * Uploads a {@code Vector4} uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the {@code Vector4} value to be uploaded.
     */
    public void uploadUniform(String name, Vector4 value) {
        glUniform4f(getUniformLocation(name), value.x, value.y, value.z, value.w);
    }

    /**
     * Uploads a {@code Matrix2} uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the {@code Matrix2} value to be uploaded.
     */
    public void uploadUniform(String name, Matrix2 value) {
        glUniformMatrix2fv(getUniformLocation(name), false, store(value));
    }

    /**
     * Uploads a {@code Matrix3} uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the {@code Matrix3} value to be uploaded.
     */
    public void uploadUniform(String name, Matrix3 value) {
        glUniformMatrix3fv(getUniformLocation(name), false, store(value));
    }

    /**
     * Uploads a {@code Matrix4} uniform to the corresponding {@code Shader}.
     *
     * @param name  the name of the uniform.
     * @param value the {@code Matrix4} value to be uploaded.
     */
    public void uploadUniform(String name, Matrix4 value) {
        glUniformMatrix4fv(getUniformLocation(name), false, store(value));
    }

    /**
     * Binds this {@code ShaderProgram}.
     */
    public void bind() {
        glUseProgram(handle);
    }

    /**
     * Unbinds this {@code ShaderProgram}.
     */
    public void unbind() {
        glUseProgram(0);
    }

    /**
     * Deletes this {@code ShaderProgram}.
     */
    public void delete() {
        glDeleteProgram(handle);
    }

}
