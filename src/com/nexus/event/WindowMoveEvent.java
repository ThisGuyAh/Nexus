package com.nexus.event;

import com.nexus.core.Window;

/**
 * A {@code WindowFocusEvent} notifies subscribers when a {@code Window} moves.
 *
 * @author Christopher Ruley
 */
public class WindowMoveEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The previous x position.
     */
    private int previousX;

    /**
     * The previous y position.
     */
    private int previousY;

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

    @Override
    public String toString() {
        return "WindowMoveEvent: Window = " + window.getTitle() + ", X = " + window.getX() + ", Y = " + window.getY()
            + ", Previous X = " + previousX + ", Previous Y = " + previousY;
    }

}