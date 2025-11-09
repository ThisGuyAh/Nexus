package com.createx.nexus.event;

import com.createx.nexus.core.Window;

/**
 * A {@code WindowMaximizeEvent} notifies subscribers when a {@code Window} is maximized or restored.
 *
 * @author Christopher Ruley
 */
public class WindowMaximizeEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Whether the {@code Window} was maximized or restored.
     */
    private boolean maximized;

    /**
     * Consctructs a {@code WindowMaximizeEvent}.
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

    @Override
    public String toString() {
        return "WindowMaximizeEvent: Window = " + window.getTitle() + ", Maximized = " + maximized;
    }

}
