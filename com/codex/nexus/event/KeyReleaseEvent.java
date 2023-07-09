package com.codex.nexus.event;

import com.codex.nexus.input.Key;

/**
 * A {@code KeyReleaseEvent} notifies a subscriber when a {@code Key} is released.
 *
 * @author Christopher Ruley
 */
public class KeyReleaseEvent {

    /**
     * The {@code Key} released.
     */
    private Key key;

    /**
     * Constructs a {@code KeyReleaseEvent}.
     *
     * @param key the {@code Key} released.
     */
    public KeyReleaseEvent(Key key) {
        this.key = key;
    }

    /**
     * Gets the {@code Key} released.
     *
     * @return the {@code Key} released.
     */
    public Key getKey() {
        return key;
    }

    /**
     * Gets a {@code String} representation of this {@code KeyReleaseEvent}.
     *
     * @return a {@code String} representation of this {@code KeyReleaseEvent}.
     */
    @Override
    public String toString() {
        return "KeyReleaseEvent: " + key;
    }

}
