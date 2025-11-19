package com.nexus.scene;

import com.nexus.math.Vector3;
import com.nexus.model.Model;

public class Entity {

    private Model model;
    private Vector3 position;
    private Vector3 rotation;
    private Vector3 scale;
    private Vector3 previousPosition;
    private Vector3 previousRotation;

    public Entity(Model model, Vector3 position, Vector3 rotation, Vector3 scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        previousPosition = position;
        previousRotation = rotation;
    }

    public Model getModel() {
        return model;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getRotation() {
        return rotation;
    }

    public Vector3 getScale() {
        return scale;
    }

    public Vector3 getPreviousPosition() {
        return previousPosition;
    }

    public Vector3 getPreviousRotation() {
        return previousRotation;
    }

    public void update() {
        previousPosition = position;
        previousRotation = rotation;
    }

}
