package com.nexus.core;

import com.link.event.Bus;
import com.nexus.event.*;
import com.nexus.input.Input;
import com.nexus.input.Key;
import com.nexus.input.MouseButton;
import org.lwjgl.glfw.GLFWVidMode;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * A {@code Window} is a graphical control element occupying an area of the screen used to display information.
 *
 * @author Christopher Ruley
 */
public class Window {

    /**
     * The title.
     */
    private String title;

    /**
     * The width (in pixels).
     */
    private int width;

    /**
     * The height (in pixels).
     */
    private int height;

    /**
     * The unique identification.
     */
    private long handle;

    /**
     * The x position.
     */
    private int x;

    /**
     * The y position.
     */
    private int y;

    /**
     * Whether created or failed.
     */
    private boolean created;

    /**
     * Whether visible or hidden.
     */
    private boolean visible;

    /**
     * Constructs a {@code Window}.
     */
    public Window() {
        title = "";
        width = 960;
        height = 540;
    }

    /**
     * Constructs a {@code Window}.
     *
     * @param title  the title.
     * @param width  the width (in pixels).
     * @param height the height (in pixels).
     */
    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getHandle() {
        return handle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCreated() {
        return created;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isRunning() {
        return !glfwWindowShouldClose(handle);
    }

    public void setTitle(String title) {
        this.title = title;

        glfwSetWindowTitle(handle, title);
    }

    public void setWidth(int width) {
        glfwSetWindowSize(handle, width, height);
    }

    public void setHeight(int height) {
        glfwSetWindowSize(handle, width, height);
    }

    /**
     * Sets the x position. The field is updated with the new value in the callback.
     *
     * @param x the new x position
     */
    public void setX(int x) {
        glfwSetWindowPos(handle, x, y);
    }

    /**
     * Sets the y position. The field is updated with the new value in the callback.
     *
     * @param y the new y position.
     */
    public void setY(int y) {
        glfwSetWindowPos(handle, x, y);
    }

    /**
     * Sets the visibility, which is false by default. This is particularly useful for changing the default values of
     * the {@code Window} before it becomes visible.
     *
     * @param visible whether visible or hidden.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;

        if (visible) {
            glfwShowWindow(handle);
        } else {
            glfwHideWindow(handle);
        }
    }

    /**
     * Sets the OpenGL context to either current or detached for the calling {@code Thread}. The OpenGL context should
     * always be detached after it's {@code Thread} is done executing tasks, ensuring it is made available for a
     * subsequent thread.
     *
     * @param contextCurrent whether the OpenGL context should be current or detached.
     */
    public void setContextCurrent(boolean contextCurrent) {
        if (contextCurrent) {
            glfwMakeContextCurrent(handle);
            createCapabilities();
        } else {
            glfwMakeContextCurrent(NULL);
        }
    }

    /**
     * Sets each callback and integrates it with the corresponding event.
     */
    private void setCallbacks() {
        Bus bus = Bus.getInstance();

        glfwSetWindowCloseCallback(handle, handle -> {
            bus.publish(new WindowCloseEvent(this));
        });
        glfwSetWindowFocusCallback(handle, (handle, focused) -> {
            bus.publish(new WindowFocusEvent(this, focused));
        });
        glfwSetWindowMaximizeCallback(handle, (handle, maximized) -> {
            bus.publish(new WindowMaximizeEvent(this, maximized));
        });
        glfwSetWindowIconifyCallback(handle, (handle, minimized) -> {
            bus.publish(new WindowMinimizeEvent(this, minimized));
        });
        glfwSetWindowRefreshCallback(handle, (handle) -> {
            bus.publish(new WindowRefreshEvent(this));
        });
        glfwSetWindowPosCallback(handle, (handle, x, y) -> {
            int oldX = this.x;
            int oldY = this.y;

            this.x = x;
            this.y = y;

            bus.publish(new WindowMoveEvent(this, oldX, oldY));
        });
        glfwSetWindowSizeCallback(handle, (handle, width, height) -> {
            int oldWidth = this.width;
            int oldHeight = this.height;

            this.width = width;
            this.height = height;

            bus.publish(new WindowResizeEvent(this, oldWidth, oldHeight));
        });
        glfwSetKeyCallback(handle, (handle, key, scancode, action, mods) -> {
            Input.keyCallback(key, scancode, action, mods);

            switch (action) {
                case GLFW_PRESS -> {
                    bus.publish(new KeyPressEvent(this, Key.getFromGLFWType(key), false));
                }
                case GLFW_REPEAT -> {
                    bus.publish(new KeyPressEvent(this, Key.getFromGLFWType(key), true));
                }
                case GLFW_RELEASE -> {
                    bus.publish(new KeyReleaseEvent(this, Key.getFromGLFWType(key)));
                }
            }
        });
        glfwSetMouseButtonCallback(handle, (window, button, action, mods) -> {
            Input.mouseButtonCallback(button, action, mods);

            bus.publish(new MouseButtonPressEvent(this, MouseButton.getFromGLFWType(button)));
        });
    }

    /**
     * Sets the x and y position relative to center.
     */
    public void setCentered() {
        try (var memoryStack = stackPush()) {
            IntBuffer storedWidth = memoryStack.mallocInt(1);
            IntBuffer storedHeight = memoryStack.mallocInt(1);
            GLFWVidMode glfwVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwGetWindowSize(handle, storedWidth, storedHeight);

            x = (glfwVidMode.width() - storedWidth.get(0)) / 2;
            y = (glfwVidMode.height() - storedHeight.get(0)) / 2;

            glfwSetWindowPos(handle, x, y);
        }
    }

    /**
     * Creates the {@code Window}.
     */
    public void create() {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        handle = glfwCreateWindow(width, height, title, NULL, NULL);
        created = handle != 0L;

        if (!created) {
            return;
        }

        setCallbacks();
        setCentered();
        Bus.getInstance().publish(new WindowCreateEvent(this));
    }

    /**
     * Swaps the front and back buffers.
     */
    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }

    /**
     * Processes pending events.
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * Destroys the {@code Window}.
     */
    public void destroy() {
        if (!created) {
            return;
        }

        glfwDestroyWindow(handle);
        glfwFreeCallbacks(handle);
        Bus.getInstance().publish(new WindowDestroyEvent(this));
    }

}