package com.codex.nexus.event;

import com.codex.nexus.core.Window;
import com.codex.nexus.input.MouseButton;

public class MouseButtonPressEvent {

    private Window window;
    private MouseButton mouseButton;

    public MouseButtonPressEvent(Window window, MouseButton mouseButton) {
        this.window = window;
        this.mouseButton = mouseButton;
    }

    public Window getWindow() {
        return window;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    @Override
    public String toString() {
        return "MouseButtonPressEvent: " + mouseButton;
    }

}
