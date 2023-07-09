package com.codex.nexus.event;

import com.codex.nexus.input.Key;

/**
 * A {@code KeyPressEvent} notifies a subscriber when a {@code Key} is pressed.
 *
 * @author Christopher Ruley
 */
public class KeyPressEvent {

    /**
     * The {@code Key} that was pressed.
     */
    private Key key;

    /**
     * Whether the {@code Key} pressed is repeating.
     */
    private boolean repeat;

    /**
     * Constructs a {@code KeyPressEvent}.
     *
     * @param key    the {@code Key} that was pressed.
     * @param repeat whether the {@code Key} pressed is repeating.
     */
    public KeyPressEvent(Key key, boolean repeat) {
        this.key = key;
        this.repeat = repeat;
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
     * Gets whether the {@code Key} pressed is repeating.
     *
     * @return whether the {@code Key} pressed is repeating.
     */
    public boolean isRepeat() {
        return repeat;
    }

    /**
     * Gets a {@code String} representation of this {@code KeyPressEvent} to include the pressed {@code Key}.
     *
     * @return a {@code String} representation of this {@code KeyPressEvent}.
     */
    @Override
    public String toString() {
        return "KeyPressEvent: " + key;
    }

}
