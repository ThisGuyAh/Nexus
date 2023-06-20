package com.codex.nexus.render;

import static com.codex.nexus.utility.Documents.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import com.codex.nexus.math.Vector2;
import com.codex.nexus.math.Vector3;
import com.codex.nexus.math.Vector4;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShaderProgram {

	private Shader[] shaders;

	private int handle;

	private Map<String, Integer> uniformLocations;

	public ShaderProgram(Shader... shaders) {
		this.shaders = shaders;

		handle = glCreateProgram();

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

	public ShaderProgram(String path) {
		this(getShadersFromFile(path));
	}

	public Shader[] getShaders() {
		return shaders;
	}

	public int getHandle() {
		return handle;
	}

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

	private int getUniformLocation(String name) {
		if (!uniformLocations.containsKey(name)) {
			uniformLocations.put(name, glGetUniformLocation(handle, name));
		}

		return uniformLocations.get(name);
	}

	public void uploadUniform(String name, float value) {
		glUniform1f(getUniformLocation(name), value);
	}

	public void uploadUniform(String name, Vector2 value) {
		glUniform2f(getUniformLocation(name), value.x, value.y);
	}

	public void uploadUniform(String name, Vector3 value) {
		glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
	}

	public void uploadUniform(String name, Vector4 value) {
		glUniform4f(getUniformLocation(name), value.x, value.y, value.z, value.w);
	}

	public void bind() {
		glUseProgram(handle);
	}

	public void unbind() {
		glUseProgram(0);
	}

	public void delete() {
		glDeleteProgram(handle);
	}

}
