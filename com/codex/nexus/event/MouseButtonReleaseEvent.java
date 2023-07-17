package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.input.MouseButton;

/**
 * A {@code MouseButtonReleaseEvent} notifies subscribers when a {@code MouseButton} is released.
 *
 * @author Christopher Ruley
 */
public class MouseButtonReleaseEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The {@code MouseButton} released.
     */
    private MouseButton mouseButton;

    /**
     * Constructs a {@code MouseButtonReleaseEvent}.
     *
     * @param window      the {@code Window}.
     * @param mouseButton the {@code MouseButton} released.
     */
    public MouseButtonReleaseEvent(Window window, MouseButton mouseButton) {
        this.window = window;
        this.mouseButton = mouseButton;
    }

    /**
     * Gets the {@code Window}.
     *
     * @return window the {@code Window}.
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Gets the {@code MouseButton} released.
     *
     * @return the {@code MouseButton} released.
     */
    public MouseButton getMouseButton() {
        return mouseButton;
    }

    /**
     * Gets a {@code String} representation of this {@code MouseButtonReleaseEvent}.
     *
     * @return a {@code String} representation of this {@code MouseButtonReleaseEvent}.
     */
    @Override
    public String toString() {
        return "MouseButtonReleaseEvent: Window = " + window.getTitle() + ", MouseButton = " + mouseButton;
    }

}
