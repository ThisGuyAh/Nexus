package com.codex.nexus.event;

import com.codex.nexus.input.MouseButton;

/**
 * A {@code MouseButtonPressEvent} notifies subscribers when a {@code MouseButton} is pressed.
 *
 * @author Christopher Ruley
 */
public class MouseButtonPressEvent {

    /**
     * The {@code MouseButton} pressed.
     */
    private MouseButton mouseButton;

    /**
     * Constructs a {@code MouseButtonPressEvent}.
     *
     * @param mouseButton the {@code MouseButton} pressed.
     */
    public MouseButtonPressEvent(MouseButton mouseButton) {
        this.mouseButton = mouseButton;
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
     * Gets a {@code String} representation of this {@code MouseButtonPressEvent}, including the {@code MouseButton}
     * pressed.
     *
     * @return a {@code String} representation of this {@code MouseButtonPressEvent}.
     */
    @Override
    public String toString() {
        return "MouseButtonPressEvent: " + mouseButton;
    }

}
