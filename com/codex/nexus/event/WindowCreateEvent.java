package com.codex.nexus.event;

import com.codex.nexus.core.Window;

/**
 * A {@code WindowCreateEvent} notifies subscribers when a {@code Window} is created.
 *
 * @author Christopher Ruley
 */
public class WindowCreateEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Constructs a {@code WindowCreateEvent}.
     *
     * @param window the {@code Window}.
     */
    public WindowCreateEvent(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

    @Override
    public String toString() {
        return "WindowCreateEvent: Window = " + window.getTitle();
    }

}
