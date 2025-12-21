package com.nexus.event;

import com.link.event.Event;
import com.nexus.core.Window;

/**
 * A {@code WindowCloseEvent} notifies subscribers when a {@code Window} is closed.
 *
 * @author Christopher Ruley
 */
public class WindowCloseEvent extends Event {

    private final Window window;

    public WindowCloseEvent(Window window) {
        super();
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

}