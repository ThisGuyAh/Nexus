package com.nexus.event;

import com.link.event.Event;
import com.nexus.core.Window;

/**
 * A {@code WindowDestroyEvent} notifies subscribers when a {@code Window} is destroyed.
 *
 * @author Christopher Ruley
 */
public class WindowDestroyEvent extends Event {

    /**
     * The {@code Window}.
     */
    private final Window window;

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

}