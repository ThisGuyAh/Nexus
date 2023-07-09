package com.codex.nexus.event;

import com.codex.nexus.input.Key;

/**
 * A {@code KeyReleaseEvent} notifies a subscriber when a {@code Key} is released.
 *
 * @author Christopher Ruley
 */
public class KeyReleaseEvent {

    /**
     * The {@code Key} that was released.
     */
    private Key key;

    /**
     * Constructs a {@code KeyReleaseEvent}.
     * 
     * @param key the {@code Key} that was released.
     */
    public KeyReleaseEvent(Key key) {
        this.key = key;
    }

    /**
     * Gets the {@code Key} that was released.
     * 
     * @return the {@code Key} that was released.
     */
    public Key getKey() {
        return key;
    }

    /**
     * Gets a {@code String} representation of this {@code KeyReleaseEvent} to include the pressed {@code Key}.
     *
     * @return a {@code String} representation of this {@code KeyReleaseEvent}.
     */
    @Override
    public String toString() {
        return "KeyReleaseEvent: " + key;
    }

}
