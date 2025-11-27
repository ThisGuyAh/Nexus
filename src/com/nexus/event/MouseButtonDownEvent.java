package com.nexus.event;

import com.nexus.core.Window;
import com.nexus.input.MouseButton;

/**
 * A {@code MouseButtonDownEvent} notifies subscribers when a {@code MouseButton} is held down.
 *
 * @author Christopher Ruley
 */
public class MouseButtonDownEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The {@code MouseButton} held down.
     */
    private MouseButton mouseButton;

    /**
     * Constructs a {@code MouseButton}.
     *
     * @param window      the {@code Window}.
     * @param mouseButton the {@code MouseButton} held down.
     */
    public MouseButtonDownEvent(Window window, MouseButton mouseButton) {
        this.window = window;
        this.mouseButton = mouseButton;
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
     * Gets the {@code MouseButton} held down.
     *
     * @return the {@code MouseButton} held down.
     */
    public MouseButton getMouseButton() {
        return mouseButton;
    }

    /**
     * Gets a {@code String} representation of this {@code MouseButtonDownEvent}.
     *
     * @return a {@code String} representation of this {@code MouseButtonDownEvent}.
     */
    @Override
    public String toString() {
        return "MouseButtonDownEvent: Window = " + window.getTitle() + ", MouseButton = " + mouseButton;
    }

}