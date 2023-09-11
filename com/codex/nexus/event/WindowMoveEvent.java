package com.codex.nexus.event;

import com.codex.nexus.core.Window;

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
     * The old x position.
     */
    private int oldX;

    /**
     * The old y position.
     */
    private int oldY;

    /**
     * Instantiates a {@code WindowMoveEvent}.
     * 
     * @param window the {@code Window}.
     * @param oldX the old x position.
     * @param oldY the old y position.           
     */
    public WindowMoveEvent(Window window, int oldX, int oldY) {
        this.window = window;
        this.oldX = oldX;
        this.oldY = oldY;
    }
    
    public Window getWindow() {
        return window;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    @Override
    public String toString() {
        return "WindowMoveEvent: Window = " + window.getTitle() + ", X = " + window.getX() + ", Y = " + window.getY()
            + ", OldX = " + oldX + ", OldY = " + oldY;
    }

}
