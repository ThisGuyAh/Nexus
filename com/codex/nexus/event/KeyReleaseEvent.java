package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class KeyReleaseEvent {

    private Window window;
    private int code;

    public KeyReleaseEvent(Window window, int code) {
        this.window = window;
        this.code = code;
    }

    public Window getWindow() {
        return window;
    }

    public int getCode() {
        return code;
    }

}
