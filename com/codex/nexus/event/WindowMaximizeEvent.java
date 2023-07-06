package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowMaximizeEvent {

    private Window window;
    private boolean maximized;

    public WindowMaximizeEvent(Window window, boolean maximized) {
        this.window = window;
        this.maximized = maximized;
    }

    public Window getWindow() {
        return window;
    }

    public boolean isMaximized() {
        return maximized;
    }

}
