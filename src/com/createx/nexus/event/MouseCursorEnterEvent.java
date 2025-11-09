package com.createx.nexus.event;

import com.createx.nexus.core.Window;

/**
 * A {@code MouseCursorEnterEvent} notifies subscribers when the mouse cursor enters or leaves the boundaries of a
 * {@code Window}.
 *
 * @author Christopher Ruley
 */
public class MouseCursorEnterEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Whether the mouse cursor has entered or left the boundaries of a {@code Window}.
     */
    private boolean entered;

    /**
     * Instantiates a {@code MouseCursorMoveEvent}.
     *
     * @param window the {@code Window}.
     * @param entered whether the mouse cursor has entered or left the boundaries of a {@code Window}.
     */
    public MouseCursorEnterEvent(Window window, boolean entered) {
        this.window = window;
        this.entered = entered;
    }

    public Window getWindow() {
        return window;
    }

    public boolean hasEntered() {
        return entered;
    }

    @Override
    public String toString() {
        return "MouseCursorEnterEvent: Window = " + window.getTitle();
    }

}
