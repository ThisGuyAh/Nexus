package com.codex.nexus.render;

import static com.codex.nexus.utility.Documents.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import com.codex.nexus.math.Vector2;
import com.codex.nexus.math.Vector3;
import com.codex.nexus.math.Vector4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * 
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
	 * 
	 * @return the array of {@code Shader}s.
	 */
	private static final Shader[] getShadersFromFile(String path) {
		List<List<String>> groups = split("#type", read(path));
		List<Shader> shaders = new ArrayList<>();

		for (var group : groups) {
			String firstLine = group.get(0);

			group.remove(0);

			String source = concatenate(group);

			switch (firstLine) {
				case "#type vertex":
					shaders.add(new Shader(PipelineStage.VERTEX, source));
					break;

				case "#type geometry":
					shaders.add(new Shader(PipelineStage.GEOMETRY, source));
					break;

				case "#type tess control":
					shaders.add(new Shader(PipelineStage.TESS_CONTROL, source));
					break;

				case "#type tess evaluation":
					shaders.add(new Shader(PipelineStage.TESS_EVALUATION, source));
					break;

				case "#type fragment":
					shaders.add(new Shader(PipelineStage.FRAGMENT, source));
					break;

				case "#type compute":
					shaders.add(new Shader(PipelineStage.COMPUTE, source));
					break;
			}
		}

		return shaders.toArray(new Shader[0]);
	}

	/**
	 * Gets the cached uniform location. The uniform location is queried and cached if it doesn't exist.
	 * 
	 * @param name the name of the uniform.
	 * 
	 * @return the cached uniform location.
	 */
	private int getUniformLocation(String name) {
		if (!uniformLocations.containsKey(name)) {
			uniformLocations.put(name, glGetUniformLocation(handle, name));
		}

		return uniformLocations.get(name);
	}

	/**
	 * Uploads a uniform to the corresponding {@code Shader}.
	 * 
	 * @param name the name of the uniform.
	 * @param value the value to be uploaded.
	 */
	public void uploadUniform(String name, float value) {
		glUniform1f(getUniformLocation(name), value);
	}

	/**
	 * Uploads a uniform to the corresponding {@code Shader}.
	 * 
	 * @param name the name of the uniform.
	 * @param value the value to be uploaded.
	 */
	public void uploadUniform(String name, Vector2 value) {
		glUniform2f(getUniformLocation(name), value.x, value.y);
	}

	/**
	 * Uploads a uniform to the corresponding {@code Shader}.
	 * 
	 * @param name the name of the uniform.
	 * @param value the value to be uploaded.
	 */
	public void uploadUniform(String name, Vector3 value) {
		glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
	}

	/**
	 * Uploads a uniform to the corresponding {@code Shader}.
	 * 
	 * @param name the name of the uniform.
	 * @param value the value to be uploaded.
	 */
	public void uploadUniform(String name, Vector4 value) {
		glUniform4f(getUniformLocation(name), value.x, value.y, value.z, value.w);
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
