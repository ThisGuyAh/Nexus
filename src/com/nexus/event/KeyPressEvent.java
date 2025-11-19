package com.nexus.event;

import com.nexus.core.Window;
import com.nexus.input.Key;

/**
 * A {@code KeyPressEvent} notifies subscribers when a {@code Key} is pressed.
 *
 * @author Christopher Ruley
 */
public class KeyPressEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The {@code Key} pressed.
     */
    private Key key;

    /**
     * Whether the {@code Key} pressed is repeating.
     */
    private boolean repeat;

    /**
     * Constructs a {@code KeyPressEvent}.
     *
     * @param window the {@code Window}.
     * @param key    the {@code Key} pressed.
     * @param repeat whether the {@code Key} pressed is repeating.
     */
    public KeyPressEvent(Window window, Key key, boolean repeat) {
        this.window = window;
        this.key = key;
        this.repeat = repeat;
    }

    public Window getWindow() {
        return window;
    }

    public Key getKey() {
        return key;
    }

    public boolean isRepeat() {
        return repeat;
    }

    @Override
    public String toString() {
        return "KeyPressEvent: Window = " + window.getTitle() + ", Key = " + key;
    }

}
