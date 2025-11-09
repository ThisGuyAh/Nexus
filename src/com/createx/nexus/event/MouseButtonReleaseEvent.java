package com.createx.nexus.event;

import com.createx.nexus.core.Window;
import com.createx.nexus.core.MouseButton;

/**
 * A {@code MouseButtonReleaseEvent} notifies subscribers when a {@code MouseButton} is released.
 *
 * @author Christopher Ruley
 */
public class MouseButtonReleaseEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The {@code MouseButton} released.
     */
    private MouseButton mouseButton;

    /**
     * Constructs a {@code MouseButtonReleaseEvent}.
     *
     * @param window      the {@code Window}.
     * @param mouseButton the {@code MouseButton} released.
     */
    public MouseButtonReleaseEvent(Window window, MouseButton mouseButton) {
        this.window = window;
        this.mouseButton = mouseButton;
    }

    public Window getWindow() {
        return window;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    @Override
    public String toString() {
        return "MouseButtonReleaseEvent: Window = " + window.getTitle() + ", MouseButton = " + mouseButton;
    }

}
