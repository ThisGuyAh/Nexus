package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowCreateEvent {

    private Window window;

    public WindowCreateEvent(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

}
