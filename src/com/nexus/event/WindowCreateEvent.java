package com.nexus.event;

import com.link.event.Event;
import com.nexus.core.Window;

/**
 * A {@code WindowCreateEvent} notifies subscribers when a {@code Window} is created.
 *
 * @author Christopher Ruley
 */
public class WindowCreateEvent extends Event {

    /**
     * The {@code Window}.
     */
    private final Window window;

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

}