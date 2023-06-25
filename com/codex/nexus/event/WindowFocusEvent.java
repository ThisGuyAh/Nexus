package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowFocusEvent {

    private Window window;
    private boolean focused;

    public WindowFocusEvent(Window window, boolean focused) {
        this.window = window;
        this.focused = focused;
    }

    public Window getWindow() {
        return window;
    }

    public boolean isFocused() {
        return focused;
    }

}
