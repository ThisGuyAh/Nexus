package com.nexus.event;

import com.link.event.Event;
import com.nexus.core.Window;

/**
 * A {@code WindowMaximizeEvent} notifies subscribers when a {@code Window} is maximized or restored.
 *
 * @author Christopher Ruley
 */
public class WindowMaximizeEvent extends Event {

    /**
     * The {@code Window}.
     */
    private final Window window;

    /**
     * Whether the {@code Window} was maximized or restored.
     */
    private final boolean maximized;

    /**
     * Constructs a {@code WindowMaximizeEvent}.
     *
     * @param window    the {@code Window}.
     * @param maximized whether the {@code Window} was maximized or restored.
     */
    public WindowMaximizeEvent(Window window, boolean maximized) {
        this.window = window;
        this.maximized = maximized;
    }

    public Window getWindow() {
        return window;
    }

    public boolean isMaximized() {
        return maximized;
    }

}