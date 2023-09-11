package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.math.Vector2;

/**
 * A {@code MouseWheelScrollEvent} notifies subscribers when the mouse wheel scrolls.
 *
 * @author Christopher Ruley
 */
public class MouseWheelScrollEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The mouse wheel scroll offset.
     */
    private Vector2 offset;

    /**
     * Constructs a {@code MouseScrollEvent}.
     *
     * @param window the {@code Window}.
     * @param offset the mouse wheel scroll offset.
     */
    public MouseWheelScrollEvent(Window window, Vector2 offset) {
        this.window = window;
        this.offset = offset;
    }

    public Window getWindow() {
        return window;
    }

    public Vector2 getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "MouseScrollEvent: Window = " + window.getTitle() + ", " + offset;
    }

}
