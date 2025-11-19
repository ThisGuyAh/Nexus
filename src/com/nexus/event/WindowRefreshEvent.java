package com.nexus.event;

import com.nexus.core.Window;

/**
 * A {@code WindowDestroyEvent} notifies subscribers when a {@code Window} refreshes.
 *
 * @author Christopher Ruley
 */
public class WindowRefreshEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Constructs a {@code WindowRefreshEvent}.
     *
     * @param window the {@code Window}.
     */
    public WindowRefreshEvent(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

}
