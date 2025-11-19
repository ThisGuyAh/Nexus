package com.nexus.event;

import com.nexus.core.Window;
import com.nexus.core.Key;

/**
 * A {@code KeyReleaseEvent} notifies subscribers when a {@code Key} is released.
 *
 * @author Christopher Ruley
 */
public class KeyReleaseEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The {@code Key} released.
     */
    private Key key;

    /**
     * Constructs a {@code KeyReleaseEvent}.
     *
     * @param window the {@code Window}.
     * @param key    the {@code Key} released.
     */
    public KeyReleaseEvent(Window window, Key key) {
        this.window = window;
        this.key = key;
    }

    public Window getWindow() {
        return window;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "KeyReleaseEvent: Window = " + window.getTitle() + ", Key = " + key;
    }

}
