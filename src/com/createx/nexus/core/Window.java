package com.createx.nexus.core;

import com.createx.nexus.event.EventBus;
import com.createx.nexus.event.WindowCreateEvent;
import com.createx.nexus.event.WindowDestroyEvent;
import com.createx.nexus.event.WindowFocusEvent;
import com.createx.nexus.event.WindowMaximizeEvent;
import com.createx.nexus.event.WindowMinimizeEvent;
import com.createx.nexus.event.WindowMoveEvent;
import com.createx.nexus.event.WindowRefreshEvent;
import com.createx.nexus.event.WindowResizeEvent;
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
     * Whether vertical synchronization is enabled.
     */
    private boolean vSync;

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
     * Constructs a {@code Window}.
     */
    public Window() {
        title = "";
        width = 960;
        height = 540;
        vSync = false;
    }

    /**
     * Constructs a {@code Window}.
     *
     * @param title  the title.
     * @param width  the width (in pixels).
     * @param height the height (in pixels).
     * @param vSync  whether vertical synchronization is enabled.
     */
    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
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

    public boolean isVSync() {
        return vSync;
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

    public void setVSync(boolean vSync) {
        this.vSync = vSync;

        glfwSwapInterval(vSync ? 1 : 0);
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
     * Sets each callback and integrates it with the corresponding event.
     */
    private void setCallbacks() {
        EventBus eventBus = EventBus.getInstance();

        eventBus.publish(new WindowCreateEvent(this));
        glfwSetWindowCloseCallback(handle, handle -> {
            eventBus.publish(new WindowDestroyEvent(this));
        });
        glfwSetWindowFocusCallback(handle, (handle, focused) -> {
            eventBus.publish(new WindowFocusEvent(this, focused));
        });
        glfwSetWindowMaximizeCallback(handle, (handle, maximized) -> {
            eventBus.publish(new WindowMaximizeEvent(this, maximized));
        });
        glfwSetWindowIconifyCallback(handle, (handle, minimized) -> {
            eventBus.publish(new WindowMinimizeEvent(this, minimized));
        });
        glfwSetWindowRefreshCallback(handle, (handle) -> {
            eventBus.publish(new WindowRefreshEvent(this));
        });
        glfwSetWindowPosCallback(handle, (handle, x, y) -> {
            int oldX = this.x;
            int oldY = this.y;

            this.x = x;
            this.y = y;

            eventBus.publish(new WindowMoveEvent(this, oldX, oldY));
        });
        glfwSetWindowSizeCallback(handle, (handle, width, height) -> {
            int oldWidth = this.width;
            int oldHeight = this.height;

            this.width = width;
            this.height = height;

            eventBus.publish(new WindowResizeEvent(this, oldWidth, oldHeight));
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
     * Creates the {@code Window}.
     */
    public void create() {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        handle = glfwCreateWindow(width, height, title, NULL, NULL);

        setCallbacks();
        setCentered();
        glfwSwapInterval(vSync ? 1 : 0);
        glfwShowWindow(handle);
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
     * Destroys the {@code Window} and terminates GLFW if all instances are destroyed.
     */
    public void destroy() {
        glfwDestroyWindow(handle);
        glfwFreeCallbacks(handle);
    }

}
