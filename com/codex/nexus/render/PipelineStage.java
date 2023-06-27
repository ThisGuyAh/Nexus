package com.codex.nexus.render;

import static org.lwjgl.opengl.GL43.*;

/**
 * {@code PipelineStage} defines the rendering pipeline stages.
 *
 * @author Christopher Ruley
 */
public enum PipelineStage {

	VERTEX,
	GEOMETRY,
	TESS_CONTROL,
	TESS_EVALUATION,
	FRAGMENT,
	COMPUTE;

	/**
	 * Gets the OpenGL type corresponding with this {@code PipelineStage}.
	 *
	 * @return the OpenGL type.
	 */
	public int getOpenGLType() {
		return switch (this) {
			case VERTEX -> GL_VERTEX_SHADER;
			case GEOMETRY -> GL_GEOMETRY_SHADER;
			case TESS_CONTROL -> GL_TESS_CONTROL_SHADER;
			case TESS_EVALUATION -> GL_TESS_EVALUATION_SHADER;
			case FRAGMENT -> GL_FRAGMENT_SHADER;
			case COMPUTE -> GL_COMPUTE_SHADER;
		};
	}

}
