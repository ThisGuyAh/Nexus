package com.codex.nexus.event;

import com.codex.nexus.core.Window;

/**
 * A {@code WindowFocusEvent} notifies subscribers when a {@code Window} resizes.
 *
 * @author Christopher Ruley
 */
public class WindowResizeEvent {

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * The old width.
     */
    private int oldWidth;

    /**
     * The old height.
     */
    private int oldHeight;

    /**
     * Instantiates a {@code WindowResizeEvent}.
     * 
     * @param window the {@code Window}.
     * @param oldWidth the old width.
     * @param oldHeight the old height.
     */
    public WindowResizeEvent(Window window, int oldWidth, int oldHeight) {
        this.window = window;
        this.oldWidth = oldWidth;
        this.oldHeight = oldHeight;
    }

    public Window getWindow() {
        return window;
    }

    public int getOldWidth() {
        return oldWidth;
    }

    public int getOldHeight() {
        return oldHeight;
    }

    @Override
    public String toString() {
        return "WindowResizeEvent: Window = " + window.getTitle() + ", Width = " + window.getWidth() + ", Height = "
            + window.getHeight() + ", OldWidth = " + oldWidth + ", OldHeight = " + oldHeight;
    }

}
