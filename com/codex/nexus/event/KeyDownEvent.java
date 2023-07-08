package com.codex.nexus.event;

import com.codex.nexus.input.Key;
import com.codex.nexus.core.Window;

public class KeyDownEvent {

    private Window window;
    private Key key;

    public KeyDownEvent(Window window, Key key) {
        this.window = window;
        this.key = key;
    }

    public Window getWindow() {
        return window;
    }

    public Key getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "KeyDownEvent: " + key;
    }

}
