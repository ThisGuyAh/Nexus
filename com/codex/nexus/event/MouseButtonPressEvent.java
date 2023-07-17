package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.input.MouseButton;

/**
 * A {@code MouseButtonPressEvent} notifies subscribers when a {@code MouseButton} is pressed.
 *
 * @author Christopher Ruley
 */
public class MouseButtonPressEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The {@code MouseButton} pressed.
     */
    private MouseButton mouseButton;

    /**
     * Constructs a {@code MouseButtonPressEvent}.
     *
     * @param window      the {@code Window}.
     * @param mouseButton the {@code MouseButton} pressed.
     */
    public MouseButtonPressEvent(Window window, MouseButton mouseButton) {
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
     * Gets the {@code MouseButton} pressed.
     *
     * @return the {@code MouseButton} pressed.
     */
    public MouseButton getMouseButton() {
        return mouseButton;
    }

    /**
     * Gets a {@code String} representation of this {@code MouseButtonPressEvent}.
     *
     * @return a {@code String} representation of this {@code MouseButtonPressEvent}.
     */
    @Override
    public String toString() {
        return "MouseButtonPressEvent: Window = " + window.getTitle() + ", MouseButton = " + mouseButton;
    }

}
