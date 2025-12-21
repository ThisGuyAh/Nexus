package com.nexus.event;

import com.link.event.Event;
import com.nexus.core.Window;

/**
 * A {@code WindowMinimizeEvent} notifies subscribers when a {@code Window} is minimized or restored.
 *
 * @author Christopher Ruley
 */
public class WindowMinimizeEvent extends Event {

    /**
     * The {@code Window}.
     */
    private final Window window;

    /**
     * Whether the {@code Window} was minimized or restored.
     */
    private final boolean minimized;

    /**
     * Constructs a {@code WindowMaximizeEvent}.
     *
     * @param window    the {@code Window}.
     * @param minimized whether the {@code Window} was minimized or restored.
     */
    public WindowMinimizeEvent(Window window, boolean minimized) {
        this.window = window;
        this.minimized = minimized;
    }

    public Window getWindow() {
        return window;
    }

    public boolean isMinimized() {
        return minimized;
    }

}