package com.codex.nexus.event;

import com.codex.nexus.math.Vector2;

/**
 * A {@code MouseScrollEvent} notifies subscribers when the mouse scrolls.
 *
 * @author Christopher Ruley
 */
public class MouseScrollEvent {

    /**
     * The mouse scroll offset.
     */
    private Vector2 offset;

    /**
     * Constructs a {@code MouseScrollEvent}.
     *
     * @param offset the mouse scroll offset.
     */
    public MouseScrollEvent(Vector2 offset) {
        this.offset = offset;
    }

    /**
     * Gets the mouse scroll offset.
     *
     * @return the mouse scroll offset.
     */
    public Vector2 getOffset() {
        return offset;
    }

    /**
     * Gets a {@code String} representation of this {@code MouseScrollEvent}.
     *
     * @return a {@code String} representation of this {@code MouseScrollEvent}.
     */
    @Override
    public String toString() {
        return "MouseScrollEvent: " + offset.toString();
    }

}
