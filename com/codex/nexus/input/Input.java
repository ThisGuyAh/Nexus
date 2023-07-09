package com.codex.nexus.input;

import com.codex.nexus.core.Application;
import com.codex.nexus.core.Window;
import com.codex.nexus.event.Event;
import com.codex.nexus.event.EventBus;
import com.codex.nexus.event.KeyDownEvent;
import com.codex.nexus.event.KeyPressEvent;
import com.codex.nexus.event.KeyReleaseEvent;
import com.codex.nexus.event.MouseButtonDownEvent;
import com.codex.nexus.event.MouseButtonPressEvent;
import com.codex.nexus.event.MouseButtonReleaseEvent;
import com.codex.nexus.event.MouseMoveEvent;
import com.codex.nexus.event.MouseScrollEvent;
import com.codex.nexus.event.WindowFocusEvent;
import com.codex.nexus.math.Vector2;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private static EventBus eventBus = Application.getInstance().getEventBus();
    private static List<Key> activeKeys = new ArrayList<>();
    private static List<MouseButton> activeMouseButtons = new ArrayList<>();
    private Window window;

    public Input() {
        eventBus.register(this);
    }

    public void update() {
        for (var activeKey : activeKeys) {
            eventBus.publish(new KeyDownEvent(window, activeKey));
        }
        for (var activeMouseButton : activeMouseButtons) {
            eventBus.publish(new MouseButtonDownEvent(window, activeMouseButton));
        }
    }

    @Event
    public void onEvent(WindowFocusEvent event) {
        if (!event.isFocused() || event.getWindow() == window) {
            return;
        }

        window = event.getWindow();

        glfwSetKeyCallback(window.getHandle(), (handle, keyCode, scanCode, action, mods) -> {
            Key key = Key.getFromGLFWType(keyCode);

            switch (action) {
                case GLFW_PRESS -> {
                    eventBus.publish(new KeyPressEvent(window, key, false));
                    activeKeys.add(key);
                }
                case GLFW_RELEASE -> {
                    eventBus.publish(new KeyReleaseEvent(window, key));
                    activeKeys.remove(key);
                }
                case GLFW_REPEAT -> eventBus.publish(new KeyPressEvent(window, key, true));
            }
        });
        glfwSetMouseButtonCallback(window.getHandle(), (handle, mouseButtonCode, action, mods) -> {
            MouseButton mouseButton = MouseButton.getFromGLFWType(mouseButtonCode);

            switch (action) {
                case GLFW_PRESS -> {
                    eventBus.publish(new MouseButtonPressEvent(window, mouseButton));
                    activeMouseButtons.add(mouseButton);
                }
                case GLFW_RELEASE -> {
                    eventBus.publish(new MouseButtonReleaseEvent(window, mouseButton));
                    activeMouseButtons.remove(mouseButton);
                }
            }
        });
        glfwSetCursorPosCallback(window.getHandle(), (handle, x, y) -> {
            eventBus.publish(new MouseMoveEvent(window, new Vector2((float) x, (float) y)));
        });
        glfwSetScrollCallback(window.getHandle(), (window1, x, y) -> {
            eventBus.publish(new MouseScrollEvent(window, new Vector2((float) x, (float) y)));
        });
    }

}
