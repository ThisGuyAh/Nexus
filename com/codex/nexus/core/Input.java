package com.codex.nexus.core;

import com.codex.nexus.event.EventBus;
import com.codex.nexus.event.KeyDownEvent;
import com.codex.nexus.event.KeyPressEvent;
import com.codex.nexus.event.KeyReleaseEvent;
import com.codex.nexus.event.MouseButtonDownEvent;
import com.codex.nexus.event.MouseButtonPressEvent;
import com.codex.nexus.event.MouseButtonReleaseEvent;
import com.codex.nexus.event.MouseMoveEvent;
import com.codex.nexus.event.MouseScrollEvent;
import com.codex.nexus.math.Vector2;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

/**
 * {@code Input} is an all-static class responsible for providing the logic used to process user input per
 * {@code Window}.
 *
 * @author Christopher Ruley
 */
public class Input {

    /**
     * The actively held {@code Key}s with their corresponding {@code Window}.
     */
    private static Map<Key, Window> activeKeys = new HashMap<>();

    /**
     * The actively held {@code MouseButton}s with their corresponding {@code Window}.
     */
    private static Map<MouseButton, Window> activeMouseButtons = new HashMap<>();

    /**
     * The {@code EventBus}.
     */
    private static EventBus eventBus = Application.getInstance().getEventBus();

    /**
     * {@code Input} cannot be instantiated.
     */
    private Input() {
    }

    /**
     * Updates the input by querying for cached active {@code Key}s and {@code MouseButton}s, and publishing their
     * respective events. This method is called from the {@code Application}.
     */
    static void update() {
        for (var activeKey : activeKeys.keySet()) {
            eventBus.publish(new KeyDownEvent(activeKeys.get(activeKey), activeKey));
        }
        for (var activeMouseButton : activeMouseButtons.keySet()) {
            eventBus.publish(new MouseButtonDownEvent(activeMouseButtons.get(activeMouseButton), activeMouseButton));
        }
    }

    /**
     * Handles {@code Key} actions and publishes their respective events, and caches or removes
     * {@code Key}s for publishing {@code KeyDownEvent}s. This method is called from the {@code Window}.
     *
     * @param window the window.
     * @param key    the {@code Key}.
     * @param action the {@code Key} action.
     */
    static void keyCallback(Window window, Key key, int action) {
        switch (action) {
            case GLFW_PRESS -> {
                eventBus.publish(new KeyPressEvent(window, key, false));
                activeKeys.put(key, window);
            }
            case GLFW_RELEASE -> {
                eventBus.publish(new KeyReleaseEvent(window, key));
                activeKeys.remove(key);
            }
            case GLFW_REPEAT -> eventBus.publish(new KeyPressEvent(window, key, true));
        }
    }

    /**
     * Handles {@code MouseButton} actions and publishes their respective events, and caches or removes
     * {@code MouseButton}s for publishing {@code MouseButtonDownEvent}s. This method is called from the {@code Window}.
     *
     * @param window      the {@code Window}.
     * @param mouseButton the {@code MouseButton}.
     * @param action      the {@code MouseButton} action.
     */
    static void mouseButtonCallback(Window window, MouseButton mouseButton, int action) {
        switch (action) {
            case GLFW_PRESS -> {
                eventBus.publish(new MouseButtonPressEvent(window, mouseButton));
                activeMouseButtons.put(mouseButton, window);
            }
            case GLFW_RELEASE -> {
                eventBus.publish(new MouseButtonReleaseEvent(window, mouseButton));
                activeMouseButtons.remove(mouseButton);
            }
        }
    }

    /**
     * Handles mouse movement and publishes the respective event. This method is called from the {@code Window}.
     *
     * @param window the {@code Window}.
     * @param x      the x offset.
     * @param y      the y offset.
     */
    static void cursorCallback(Window window, double x, double y) {
        eventBus.publish(new MouseMoveEvent(window, new Vector2((float) x, (float) y)));
    }

    /**
     * Handles mouse scrolling and publishes the respective event. This method is called from the {@code Window}.
     *
     * @param window the {@code Window}.
     * @param x      the x offset.
     * @param y      the y offset.
     */
    static void scrollCallback(Window window, double x, double y) {
        eventBus.publish(new MouseScrollEvent(window, new Vector2((float) x, (float) y)));
    }

}
