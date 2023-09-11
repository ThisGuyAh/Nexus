package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.core.Key;

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
