package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.input.Key;

/**
 * A {@code KeyPressEvent} notifies a subscriber when a {@code Key} is pressed.
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

    /**
     * Gets the {@code Window}.
     *
     * @return window the {@code Window}.
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Gets the {@code Key} pressed.
     *
     * @return the {@code Key} pressed.
     */
    public Key getKey() {
        return key;
    }

    /**
     * Gets whether the {@code Key} pressed is repeating.
     *
     * @return whether the {@code Key} pressed is repeating.
     */
    public boolean isRepeat() {
        return repeat;
    }

    /**
     * Gets a {@code String} representation of this {@code KeyPressEvent}.
     *
     * @return a {@code String} representation of this {@code KeyPressEvent}.
     */
    @Override
    public String toString() {
        return "KeyPressEvent: Window = " + window.getTitle() + ", Key = " + key;
    }

}
