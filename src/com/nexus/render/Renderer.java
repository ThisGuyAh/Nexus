package com.nexus.render;

import com.nexus.core.Window;
import com.nexus.math.Matrix4;
import com.nexus.math.Vector4;
import com.nexus.model.Material;
import com.nexus.model.Model;
import com.nexus.model.Texture;
import com.nexus.scene.Camera;
import com.nexus.scene.Entity;
import com.nexus.scene.Light;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Renderer {

    /**
     * Cannot construct {@code Renderer}.
     */
    private Renderer() {
    }

    public static void setDepthTesting(boolean depthTesting) {
        if (depthTesting) {
            glEnable(GL_DEPTH_TEST);
        } else {
            glDisable(GL_DEPTH_TEST);
        }
    }

    public static void setFaceCulling(boolean faceCulling) {
        if (faceCulling) {
            glEnable(GL_CULL_FACE);
        } else {
            glDisable(GL_CULL_FACE);
        }
    }

    public static void setClearColor(Vector4 color) {
        glClearColor(color.x, color.y, color.z, color.w);
    }

    public static void setViewport(int x, int y, int width, int height) {
        glViewport(x, y, width, height);
    }

    public static void draw(Window window, ShaderProgram shaderProgram, Camera camera, Entity entity, Light light) {
        Matrix4 transformation = new Matrix4();
        Matrix4 view = new Matrix4();
        Matrix4 projection = new Matrix4();

        transformation.setTransformation(entity.getPosition(), entity.getRotation(), entity.getScale());
        view.setView(camera.getPosition(), camera.getRotation());
        projection.setPerspectiveProjection(window.getWidth(), window.getHeight(), 120.0F, 0.1F, 1000.0F);

        shaderProgram.bind();
        shaderProgram.uploadUniform("transformation", transformation);
        shaderProgram.uploadUniform("view", view);
        shaderProgram.uploadUniform("projection", projection);
        shaderProgram.uploadUniform("lightPosition", light.getPosition());
        shaderProgram.uploadUniform("lightColor", light.getColor());

        Model model = entity.getModel();

        model.bind();

        for (var mesh : model.getMeshes()) {
            Material material = mesh.getMaterial();
            Texture texture = material.getTexture();
            boolean textured = texture != null;

            shaderProgram.uploadUniform("ambientColor", material.getAmbientColor());
            shaderProgram.uploadUniform("diffuseColor", material.getDiffuseColor());
            shaderProgram.uploadUniform("specularColor", material.getSpecularColor());
            shaderProgram.uploadUniform("shininess", material.getShininess());
            shaderProgram.uploadUniform("textured", textured);

            if (textured) {
                glActiveTexture(GL_TEXTURE0);
                shaderProgram.uploadUniform("txr", 0);
                texture.bind();
            }

            glDrawElements(GL_TRIANGLES, mesh.getIndexCount(), GL_UNSIGNED_INT, (long) mesh.getIndexOffset() * Integer.BYTES);
        }

        model.unbind();
        shaderProgram.unbind();

    }

    public static void clear() {
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
    }

}