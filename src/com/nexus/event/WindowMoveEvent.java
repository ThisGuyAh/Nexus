package com.nexus.event;

import com.link.event.Event;
import com.nexus.core.Window;

/**
 * A {@code WindowFocusEvent} notifies subscribers when a {@code Window} moves.
 *
 * @author Christopher Ruley
 */
public class WindowMoveEvent extends Event {

    /**
     * The {@code Window}.
     */
    private final Window window;

    /**
     * The previous x position.
     */
    private final int previousX;

    /**
     * The previous y position.
     */
    private final int previousY;

    /**
     * Constructs a {@code WindowMoveEvent}.
     *
     * @param window    the {@code Window}.
     * @param previousX the x position before moving.
     * @param previousY the y position before moving.
     */
    public WindowMoveEvent(Window window, int previousX, int previousY) {
        this.window = window;
        this.previousX = previousX;
        this.previousY = previousY;
    }

    public Window getWindow() {
        return window;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

}