package com.nexus.render;

import com.nexus.core.Window;
import com.nexus.math.Matrix4;
import com.nexus.math.Vector3;
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

    public static void setDepthMask(boolean depthMask) {
        glDepthMask(depthMask);
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

    // TODO Fix over-allocation of Vector3 and Matrix4
    public static void draw(Window window, ShaderProgram shaderProgram, Camera camera, Entity entity, Light light,
                            float interpolation) {
        Vector3 interpolatedCameraPosition = new Vector3();
        Vector3 interpolatedCameraRotation = new Vector3();
        Vector3 interpolatedEntityPosition = new Vector3();
        Vector3 interpolatedEntityRotation = new Vector3();

        Vector3.lerp(interpolation, camera.getPreviousPosition(), camera.getPosition(), interpolatedCameraPosition);
        Vector3.lerp(interpolation, camera.getPreviousRotation(), camera.getRotation(), interpolatedCameraRotation);
        Vector3.lerp(interpolation, entity.getPreviousPosition(), entity.getPosition(), interpolatedEntityPosition);
        Vector3.lerp(interpolation, entity.getPreviousRotation(), entity.getRotation(), interpolatedEntityRotation);

        Matrix4 transformation = new Matrix4();
        Matrix4 view = new Matrix4();
        Matrix4 projection = new Matrix4();

        transformation.setTransformation(interpolatedEntityPosition, interpolatedEntityRotation, entity.getScale());
        view.setView(interpolatedCameraPosition, interpolatedCameraRotation);
        projection.setPerspectiveProjection(window.getWidth(), window.getHeight(), 90.0F, 0.1F, 1000.0F);

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

            if (model.getVertexArray().getIndexBuffer() == null) {
                glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());
            } else {
                glDrawElements(GL_TRIANGLES, mesh.getIndexCount(), GL_UNSIGNED_INT, (long) mesh.getIndexOffset() * Integer.BYTES);
            }
        }

        model.unbind();
        shaderProgram.unbind();
    }

    public static void drawPoints(Window window, ShaderProgram shaderProgram, Camera camera, Model model) {
        Matrix4 view = new Matrix4();
        Matrix4 projection = new Matrix4();

        view.setView(camera.getPosition(), camera.getRotation());
        projection.setPerspectiveProjection(window.getWidth(), window.getHeight(), 90.0F, 0.1F, 1000.0F);

        shaderProgram.bind();
        shaderProgram.uploadUniform("view", view);
        shaderProgram.uploadUniform("projection", projection);
        model.bind();

        for (var mesh : model.getMeshes()) {
            if (model.getVertexArray().getIndexBuffer() == null) {
                glDrawArrays(GL_POINTS, 0, mesh.getVertexCount());
            } else {
                glDrawElements(GL_POINTS, mesh.getIndexCount(), GL_UNSIGNED_INT, (long) mesh.getIndexOffset() * Integer.BYTES);
            }
        }

        model.unbind();
        shaderProgram.unbind();
    }

    public static void clear() {
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
    }

}