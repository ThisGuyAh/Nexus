package com.nexus.event;

import com.nexus.core.Window;

/**
 * A {@code WindowCloseEvent} notifies subscribers when a {@code Window} is closed.
 *
 * @author Christopher Ruley
 */
public class WindowCloseEvent {

    private Window window;

    public WindowCloseEvent(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

    @Override
    public String toString() {
        return "WindowCloseEvent: Window = " + window.getTitle();
    }

}