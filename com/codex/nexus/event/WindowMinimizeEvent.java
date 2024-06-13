package com.codex.nexus.event;

import com.codex.nexus.core.Window;

/**
 * A {@code WindowMinimizeEvent} notifies subscribers when a {@code Window} is minimized or restored.
 *
 * @author Christopher Ruley
 */
public class WindowMinimizeEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Whether the {@code Window} was minimized or restored.
     */
    private boolean minimized;

    /**
     * Instantiates a {@code WindowMaximizeEvent}.
     *
     * @param window    the {@code Window}.
     * @param maximized whether the {@code Window} was maximized or restored.
     */
    public WindowMinimizeEvent(Window window, boolean maximized) {
        this.window = window;
        this.minimized = maximized;
    }

    public Window getWindow() {
        return window;
    }

    public boolean isMinimized() {
        return minimized;
    }

    @Override
    public String toString() {
        return "WindowMinimizeEvent: Window = " + window.getTitle() + ", Minimized = " + minimized;
    }

}
