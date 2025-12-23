package com.nexus.scene;

import com.nexus.math.Vector3;

public class Camera {

    private Vector3 position;
    private Vector3 rotation;
    private Vector3 previousPosition;
    private Vector3 previousRotation;

    public Camera() {
        position = new Vector3();
        rotation = new Vector3();
        previousPosition = new Vector3(position);
        previousRotation = new Vector3(rotation);
    }

    public Camera(Vector3 position, Vector3 rotation) {
        this.position = position;
        this.rotation = rotation;
        previousPosition = new Vector3(position);
        previousRotation = new Vector3(rotation);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getRotation() {
        return rotation;
    }

    public Vector3 getPreviousPosition() {
        return previousPosition;
    }

    public Vector3 getPreviousRotation() {
        return previousRotation;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setRotation(Vector3 rotation) {
        this.rotation = rotation;
    }

    public void update() {
        previousPosition.x = position.x;
        previousPosition.y = position.y;
        previousPosition.z = position.z;
        previousRotation.x = rotation.x;
        previousRotation.y = rotation.y;
        previousRotation.z = rotation.z;
    }

}