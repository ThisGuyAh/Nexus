package com.codex.nexus.event;

import com.codex.nexus.input.Key;
import com.codex.nexus.core.Window;

public class KeyPressEvent {

    private Window window;
    private Key key;
    private boolean repeat;

    public KeyPressEvent(Window window, Key key, boolean repeat) {
        this.window = window;
        this.key = key;
        this.repeat = repeat;
    }

    public Window getWindow() {
        return window;
    }

    public Key getKey() {
        return key;
    }

    public boolean isRepeat() {
        return repeat;
    }

    @Override
    public String toString() {
        return "KeyPressEvent: " + key;
    }

}
