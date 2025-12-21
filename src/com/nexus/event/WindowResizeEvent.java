package com.nexus.event;

import com.link.event.Event;
import com.nexus.core.Window;

/**
 * A {@code WindowFocusEvent} notifies subscribers when a {@code Window} resizes.
 *
 * @author Christopher Ruley
 */
public class WindowResizeEvent extends Event {

    /**
     * The {@code Window}.
     */
    private final Window window;

    /**
     * The previous width.
     */
    private final int previousWidth;

    /**
     * The previous height.
     */
    private final int previousHeight;

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

}