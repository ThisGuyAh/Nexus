package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.math.Vector2;

public class MouseScrollEvent {

    private Window window;
    private Vector2 offset;

    public MouseScrollEvent(Window window, Vector2 offset) {
        this.window = window;
        this.offset = offset;
    }

    public Window getWindow() {
        return window;
    }

    public Vector2 getOffset() {
        return offset;
    }

}
