package com.nexus.event;

import com.nexus.core.Window;

/**
 * A {@code WindowDestroyEvent} notifies subscribers when a {@code Window} is destroyed.
 *
 * @author Christopher Ruley
 */
public class WindowDestroyEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Constructs a {@code WindowDestroyEvent}.
     *
     * @param window the {@code Window}.
     */
    public WindowDestroyEvent(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

    @Override
    public String toString() {
        return "WindowDestroyEvent: Window = " + window.getTitle();
    }

}