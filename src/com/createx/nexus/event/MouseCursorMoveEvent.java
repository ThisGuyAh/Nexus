package com.createx.nexus.event;

import com.createx.nexus.core.Window;
import com.createx.nexus.math.Vector2;

/**
 * A {@code MouseCursorMoveEvent} notifies subscribers when the mouse moves.
 *
 * @author Christopher Ruley
 */
public class MouseCursorMoveEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The current mouse cursor position.
     */
    private Vector2 position;

    /**
     * The old mouse cursor position.
     */
    private Vector2 oldPosition;

    /**
     * The delta or difference between the current and old mouse cursor position.
     */
    private Vector2 delta;

    /**
     * Instantiates a {@code MouseCursorMoveEvent}.
     *
     * @param window      the {@code Window}.
     * @param position    the current mouse cursor position.
     * @param oldPosition the old mouse cursor position.
     */
    public MouseCursorMoveEvent(Window window, Vector2 position, Vector2 oldPosition) {
        this.window = window;
        this.position = position;
        this.oldPosition = oldPosition;
        delta = new Vector2(position).subtract(oldPosition);
    }

    public Window getWindow() {
        return window;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getOldPosition() {
        return oldPosition;
    }

    public Vector2 getDelta() {
        return delta;
    }

    @Override
    public String toString() {
        return "MouseCursorMoveEvent: Window = " + window.getTitle() + ", " + position;
    }

}
