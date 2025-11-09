package com.createx.nexus.scene;

import com.createx.nexus.math.Vector3;

public class Camera {

    private Vector3 position;
    private Vector3 rotation;

    public Camera() {
        position = new Vector3();
        rotation = new Vector3();
    }

    public Camera(Vector3 position, Vector3 rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getRotation() {
        return rotation;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setRotation(Vector3 rotation) {
        this.rotation = rotation;
    }

}
