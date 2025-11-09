package com.createx.nexus.event;

import com.createx.nexus.core.Window;
import com.createx.nexus.core.MouseButton;

/**
 * A {@code MouseButtonPressEvent} notifies subscribers when a {@code MouseButton} is pressed.
 *
 * @author Christopher Ruley
 */
public class MouseButtonPressEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The {@code MouseButton} pressed.
     */
    private MouseButton mouseButton;

    /**
     * Constructs a {@code MouseButtonPressEvent}.
     *
     * @param window      the {@code Window}.
     * @param mouseButton the {@code MouseButton} pressed.
     */
    public MouseButtonPressEvent(Window window, MouseButton mouseButton) {
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
        return "MouseButtonPressEvent: Window = " + window.getTitle() + ", MouseButton = " + mouseButton;
    }

}
