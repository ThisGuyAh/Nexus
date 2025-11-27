package com.nexus.event;

import com.nexus.core.Window;
import com.nexus.input.Key;

/**
 * A {@code KeyDownEvent} notifies a subscriber when a {@code Key} is held down.
 *
 * @author Christopher Ruley
 */
public class KeyDownEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The {@code Key} held down.
     */
    private Key key;

    /**
     * Constructs a {@code KeyDownEvent}.
     *
     * @param window the {@code Window}.
     * @param key    the {@code Key} held down.
     */
    public KeyDownEvent(Window window, Key key) {
        this.window = window;
        this.key = key;
    }

    /**
     * Gets the {@code Window}.
     *
     * @return window the {@code Window}.
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Gets the {@code Key} held down.
     *
     * @return the {@code Key} held down.
     */
    public Key getKey() {
        return key;
    }

    /**
     * Gets a {@code String} representation of this {@code KeyDownEvent}.
     *
     * @return a {@code String} representation of this {@code KeyDownEvent}.
     */
    @Override
    public String toString() {
        return "KeyDownEvent: Window = " + window.getTitle() + ", Key = " + key;
    }

}