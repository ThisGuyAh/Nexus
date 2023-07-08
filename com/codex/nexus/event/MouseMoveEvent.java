package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.math.Vector2;

public class MouseMoveEvent {

    private Window window;
    private Vector2 position;

    public MouseMoveEvent(Window window, Vector2 position) {
        this.window = window;
        this.position = position;
    }

    public Window getWindow() {
        return window;
    }

    public Vector2 getPosition() {
        return position;
    }

}
