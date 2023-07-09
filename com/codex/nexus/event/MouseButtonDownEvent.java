package com.codex.nexus.event;

import com.codex.nexus.input.MouseButton;

/**
 * A {@code MouseButtonDownEvent} notifies subscribers when a {@code MouseButton} is held down.
 *
 * @author Christopher Ruley
 */
public class MouseButtonDownEvent {

    /**
     * The {@code MouseButton} held down.
     */
    private MouseButton mouseButton;

    /**
     * Constructs a {@code MouseButton}.
     *
     * @param mouseButton the {@code MouseButton} held down.
     */
    public MouseButtonDownEvent(MouseButton mouseButton) {
        this.mouseButton = mouseButton;
    }

    /**
     * Gets the {@code MouseButton} held down.
     *
     * @return the {@code MouseButton} held down.
     */
    public MouseButton getMouseButton() {
        return mouseButton;
    }

    /**
     * Gets a {@code String} representation of this {@code MouseButtonPressEvent}, including the {@code MouseButton}
     * held down.
     *
     * @return a {@code String} representation of this {@code MouseButtonPressEvent}.
     */
    @Override
    public String toString() {
        return "MouseButtonDownEvent: " + mouseButton;
    }

}
