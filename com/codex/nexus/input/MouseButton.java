package com.codex.nexus.input;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public enum MouseButton {

    ONE(GLFW_MOUSE_BUTTON_1),
    TWO(GLFW_MOUSE_BUTTON_2),
    THREE(GLFW_MOUSE_BUTTON_3),
    FOUR(GLFW_MOUSE_BUTTON_4),
    FIVE(GLFW_MOUSE_BUTTON_5),
    SIX(GLFW_MOUSE_BUTTON_6),
    SEVEN(GLFW_MOUSE_BUTTON_7),
    EIGHT(GLFW_MOUSE_BUTTON_8),
    LAST(GLFW_MOUSE_BUTTON_LAST);

    private final int code;

    private static final Map<Integer, MouseButton> glfwTypes = new HashMap<>();

    static {
        for (var mouseButton : values()) {
            glfwTypes.put(mouseButton.code, mouseButton);
        }
    }

    MouseButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MouseButton getFromGLFWType(int code) {
        return glfwTypes.get(code);
    }

}
