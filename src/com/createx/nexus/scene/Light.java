package com.createx.nexus.scene;

import com.createx.nexus.math.Vector3;
import com.createx.nexus.math.Vector4;

public class Light {

    private Vector3 position;
    private Vector4 color;

    public Light(Vector3 position, Vector4 color) {
        this.position = position;
        this.color = color;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector4 getColor() {
        return color;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setColor(Vector4 color) {
        this.color = color;
    }

}
