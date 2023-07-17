package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.math.Vector2;

/**
 * A {@code MouseMoveEvent} notifies subscribers when the mouse moves.
 *
 * @author Christopher Ruley
 */
public class MouseMoveEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The mouse position.
     */
    private Vector2 position;

    /**
     * Constructs a {@code MouseMoveEvent}.
     *
     * @param window   the {@code Window}.
     * @param position the mouse position.
     */
    public MouseMoveEvent(Window window, Vector2 position) {
        this.window = window;
        this.position = position;
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
        return "MouseMoveEvent: Window = " + window.getTitle() + ", " + position.toString();
    }

}
