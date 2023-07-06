package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowMoveEvent {

    private Window window;
    private int oldX;
    private int oldY;

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

}
