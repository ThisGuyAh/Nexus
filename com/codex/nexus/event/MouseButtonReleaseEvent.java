package com.codex.nexus.event;

import com.codex.nexus.input.MouseButton;

/**
 * A {@code MouseButtonReleaseEvent} notifies subscribers when a {@code MouseButton} is released.
 *
 * @author Christopher Ruley
 */
public class MouseButtonReleaseEvent {

    /**
     * The {@code MouseButton} released.
     */
    private MouseButton mouseButton;

    /**
     * Constructs a {@code MouseButtonReleaseEvent}.
     *
     * @param mouseButton the {@code MouseButton} released.
     */
    public MouseButtonReleaseEvent(MouseButton mouseButton) {
        this.mouseButton = mouseButton;
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
     * Gets a {@code String} representation of this {@code MouseButtonReleaseEvent}, including the {@code MouseButton}
     * released.
     *
     * @return a {@code String} representation of this {@code MouseButtonReleaseEvent}.
     */
    @Override
    public String toString() {
        return "MouseButtonReleaseEvent: " + mouseButton;
    }

}
