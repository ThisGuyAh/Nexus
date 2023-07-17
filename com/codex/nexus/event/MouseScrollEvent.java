package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.math.Vector2;

/**
 * A {@code MouseScrollEvent} notifies subscribers when the mouse scrolls.
 *
 * @author Christopher Ruley
 */
public class MouseScrollEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The mouse scroll offset.
     */
    private Vector2 offset;

    /**
     * Constructs a {@code MouseScrollEvent}.
     *
     * @param window the {@code Window}.
     * @param offset the mouse scroll offset.
     */
    public MouseScrollEvent(Window window, Vector2 offset) {
        this.window = window;
        this.offset = offset;
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
        return "MouseScrollEvent: Window = " + window.getTitle() + ", " + offset.toString();
    }

}
