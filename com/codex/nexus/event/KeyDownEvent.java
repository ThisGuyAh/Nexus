package com.codex.nexus.event;

import com.codex.nexus.input.Key;

/**
 * A {@code KeyDownEvent} notifies a subscriber when a {@code Key} is held down.
 *
 * @author Christopher Ruley
 */
public class KeyDownEvent {

    /**
     * The {@code Key} held down.
     */
    private Key key;

    /**
     * Constructs a {@code KeyDownEvent}.
     *
     * @param key the {@code Key} held down.
     */
    public KeyDownEvent(Key key) {
        this.key = key;
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
     * Gets a {@code String} representation of this {@code KeyDownEvent}, including the {@code Key} held down.
     *
     * @return a {@code String} representation of this {@code KeyDownEvent}.
     */
    @Override
    public String toString() {
        return "KeyDownEvent: " + key;
    }

}
