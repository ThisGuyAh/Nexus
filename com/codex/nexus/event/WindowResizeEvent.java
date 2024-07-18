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
     * The previous width.
     */
    private int previousWidth;

    /**
     * The previous height.
     */
    private int previousHeight;

    /**
     * Constructs a {@code WindowResizeEvent}.
     *
     * @param window         the {@code Window}.
     * @param previousWidth  the width before resizing.
     * @param previousHeight the height before resizing.
     */
    public WindowResizeEvent(Window window, int previousWidth, int previousHeight) {
        this.window = window;
        this.previousWidth = previousWidth;
        this.previousHeight = previousHeight;
    }

    public Window getWindow() {
        return window;
    }

    public int getPreviousWidth() {
        return previousWidth;
    }

    public int getPreviousHeight() {
        return previousHeight;
    }

    @Override
    public String toString() {
        return "WindowResizeEvent: Window = " + window.getTitle() + ", Width = " + window.getWidth() + ", Height = "
            + window.getHeight() + ", Previous Width = " + previousWidth + ", Previous Height = " + previousHeight;
    }

}
