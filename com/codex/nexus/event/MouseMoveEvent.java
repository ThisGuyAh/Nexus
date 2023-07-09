package com.codex.nexus.event;

import com.codex.nexus.math.Vector2;

/**
 * A {@code MouseMoveEvent} notifies subscribers when the mouse moves.
 *
 * @author Christopher Ruley
 */
public class MouseMoveEvent {

    /**
     * The mouse position.
     */
    private Vector2 position;

    /**
     * Constructs a {@code MouseMoveEvent}.
     *
     * @param position the mouse position.
     */
    public MouseMoveEvent(Vector2 position) {
        this.position = position;
    }

    /**
     * Gets the mouse position.
     *
     * @return the mouse position.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Gets a {@code String} representation of this {@code MouseMoveEvent}.
     *
     * @return a {@code String} representation of this {@code MouseMoveEvent}.
     */
    @Override
    public String toString() {
        return "MouseMoveEvent: " + position.toString();
    }

}
