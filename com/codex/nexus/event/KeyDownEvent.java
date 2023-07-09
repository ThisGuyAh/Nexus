package com.codex.nexus.event;

import com.codex.nexus.input.Key;

/**
 * A {@code KeyDownEvent} notifies a subscriber when a {@code Key} is held down.
 *
 * @author Christopher Ruley
 */
public class KeyDownEvent {

    /**
     * The {@code Key} that was pressed.
     */
    private Key key;

    /**
     * Constructs a {@code KeyDownEvent}.
     *
     * @param key the {@code Key} that was pressed.
     */
    public KeyDownEvent(Key key) {
        this.key = key;
    }

    /**
     * Gets the {@code Key} that was pressed.
     *
     * @return the {@code Key} that was pressed.
     */
    public Key getKey() {
        return key;
    }

    /**
     * Gets a {@code String} representation of this {@code KeyDownEvent} to include the pressed {@code Key}.
     *
     * @return a {@code String} representation of this {@code KeyDownEvent}.
     */
    @Override
    public String toString() {
        return "KeyDownEvent: " + key;
    }

}
