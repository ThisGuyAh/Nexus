package com.nexus.input;

import static org.lwjgl.glfw.GLFW.*;

public final class Input {

    // The update thread may be removed; volatile fields will not be needed as input is only queried in
    // Application::Update.

    private static final int MAX_KEYS = GLFW_KEY_LAST + 1;
    private static final int MAX_MOUSE_BUTTONS = GLFW_MOUSE_BUTTON_LAST + 1;
    private static final boolean[] keysDown = new boolean[MAX_KEYS];
    private static final boolean[] mouseButtons = new boolean[MAX_MOUSE_BUTTONS];
    private static volatile double mouseX;
    private static volatile double mouseY;
    private static volatile double mouseDeltaX;
    private static volatile double mouseDeltaY;
    private static volatile boolean firstMouse = true;
    private static volatile double scrollX;
    private static volatile double scrollY;

    private Input() {
    }

    public static void keyCallback(int key, int scancode, int action, int mods) {
        if (key < 0 || key >= MAX_KEYS) return;

        if (action == GLFW_PRESS) {
            keysDown[key] = true;
        } else if (action == GLFW_RELEASE) {
            keysDown[key] = false;
        }
        // GLFW_REPEAT is ignored for now
    }

    public static void mouseButtonCallback(int button, int action, int mods) {
        if (button < 0 || button >= MAX_MOUSE_BUTTONS) return;

        if (action == GLFW_PRESS) {
            mouseButtons[button] = true;
        } else if (action == GLFW_RELEASE) {
            mouseButtons[button] = false;
        }
    }

    public static void cursorPosCallback(double x, double y) {
        if (firstMouse) {
            mouseX = x;
            mouseY = y;
            firstMouse = false;
            return;
        }

        mouseDeltaX += x - mouseX;
        mouseDeltaY += y - mouseY;

        mouseX = x;
        mouseY = y;
    }

    public static void scrollCallback(double offsetX, double offsetY) {
        scrollX += offsetX;
        scrollY += offsetY;
    }

    public static boolean isKeyDown(int key) {
        return key >= 0 && key < MAX_KEYS && keysDown[key];
    }

    public static boolean isKeyDown(Key key) {
        return isKeyDown(key.getCode()); // adjust if your enum uses another name
    }

    public static boolean isMouseButtonDown(int button) {
        return button >= 0 && button < MAX_MOUSE_BUTTONS && mouseButtons[button];
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    // “Consume” deltas each frame on the update thread
    public static double consumeMouseDeltaX() {
        double dx = mouseDeltaX;

        mouseDeltaX = 0.0;

        return dx;
    }

    public static double consumeMouseDeltaY() {
        double dy = mouseDeltaY;

        mouseDeltaY = 0.0;

        return dy;
    }

    public static double consumeScrollX() {
        double sx = scrollX;

        scrollX = 0.0;

        return sx;
    }

    public static double consumeScrollY() {
        double sy = scrollY;

        scrollY = 0.0;

        return sy;
    }

}