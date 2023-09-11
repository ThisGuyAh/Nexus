package com.codex.nexus.event;

import com.codex.nexus.core.Window;

/**
 * A {@code WindowDestroyEvent} notifies subscribers when a {@code Window} is created.
 *
 * @author Christopher Ruley
 */
public class WindowDestroyEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Instantiates a {@code WindowDestroyEvent}.
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
