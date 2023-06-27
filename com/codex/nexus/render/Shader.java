package com.codex.nexus.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * A {@code Shader} is a user-defined program designed to run on some stage of a graphics processor.
 * 
 * @author Christopher Ruley
 */
public class Shader {
	
	/**
	 * The {@code PipelineStage}.
	 */
	private PipelineStage pipelineStage;
	
	/**
	 * The source code.
	 */
	private String sourceCode;

	/**
	 * The unique identification.
	 */
	private int handle;

	/**
	 * Constructs a {@code Shader}.
	 * 
	 * @param pipelineStage the {@code PipelineStage}.
	 * @param sourceCode the source code.
	 */
	public Shader(PipelineStage pipelineStage, String sourceCode) {
		this.pipelineStage = pipelineStage;
		this.sourceCode = sourceCode;
		handle = glCreateShader(pipelineStage.getOpenGLType());

		glShaderSource(handle, sourceCode);
		glCompileShader(handle);

		if (glGetShaderi(handle, GL_COMPILE_STATUS) == GL_FALSE) {
			String errorMessage = glGetShaderInfoLog(handle);

			glDeleteShader(handle);

			throw new RuntimeException(errorMessage);
		}
	}
	
	/**
	 * Gets the {@code PipelineStage}.
	 */
	public PipelineStage getPipelineStage() {
		return pipelineStage;
	}
	
	/**
	 * Gets the source code.
	 */
	public String getSourceCode() {
		return sourceCode;
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
	 * Deletes this {@code Shader}.
	 */
	public void delete() {
		glDeleteShader(handle);
	}

}
