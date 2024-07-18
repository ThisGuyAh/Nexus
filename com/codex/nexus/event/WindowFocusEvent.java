package com.codex.nexus.event;

import com.codex.nexus.core.Window;

/**
 * A {@code WindowFocusEvent} notifies subscribers when a {@code Window} gains or loses focus.
 *
 * @author Christopher Ruley
 */
public class WindowFocusEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Whether the {@code Window} has gained or lost focus.
     */
    private boolean focused;

    /**
     * Constructs a {@code WindowFocusEvent}.
     *
     * @param window  the {@code Window}.
     * @param focused whether the {@code Window} has gained or lost focus.
     */
    public WindowFocusEvent(Window window, boolean focused) {
        this.window = window;
        this.focused = focused;
    }

    public Window getWindow() {
        return window;
    }

    public boolean isFocused() {
        return focused;
    }

    @Override
    public String toString() {
        return "WindowFocusEvent: Window = " + window.getTitle() + ", Focused = " + focused;
    }

}
