package com.codex.nexus.core;

import com.codex.nexus.event.EventBus;
import com.codex.nexus.event.WindowCreateEvent;
import com.codex.nexus.event.WindowDestroyEvent;
import com.codex.nexus.event.WindowFocusEvent;
import com.codex.nexus.event.WindowMaximizeEvent;
import com.codex.nexus.event.WindowMoveEvent;
import com.codex.nexus.event.WindowResizeEvent;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

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
     *
     * @param title the title.
     * @param width the width (in pixels).
     * @param height the height (in pixels).
     * @param vSync whether vertical synchronization is enabled.
     */
    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;

        glfwInit();
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        handle = glfwCreateWindow(width, height, title, NULL, NULL);

        setCallbacks();

        try (MemoryStack memoryStack = stackPush()) {
            IntBuffer storedWidth = memoryStack.mallocInt(1);
            IntBuffer storedHeight = memoryStack.mallocInt(1);
            GLFWVidMode glfwVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwGetWindowSize(handle, storedWidth, storedHeight);

            x = (glfwVidMode.width() - storedWidth.get(0)) / 2;
            y = (glfwVidMode.height() - storedHeight.get(0)) / 2;

            glfwSetWindowPos(handle, x, y);
        }

        glfwMakeContextCurrent(handle);
        glfwSwapInterval(vSync ? 1 : 0);
        glfwShowWindow(handle);
    }

    /**
     * Gets the title.
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the width (in pixels).
     *
     *@return the width (in pixels).
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height (in pixels).
     *
     * @return the height (in pixels).
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets whether vertical synchronization is enabled.
     *
     * @return whether vertical synchronization is enabled.
     */
    public boolean isVSync() {
        return vSync;
    }

    /**
     * Gets the unique identification.
     *
     * @return the unique identification.
     */
    public long getHandle() {
        return handle;
    }

    /**
     * Gets the x position.
     *
     * @return the x position.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y position.
     *
     * @return the y position.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the title.
     *
     * @param title the title.
     */
    public void setTitle(String title) {
        glfwSetWindowTitle(handle, this.title = title);
    }

    /**
     * Sets the width (in pixels).
     *
     * @param width the width (in pixels).
     */
    public void setWidth(int width) {
        glfwSetWindowSize(handle, width, height);
    }

    /**
     * Sets the height (in pixels).
     *
     * @param height the height (in pixels).
     */
    public void setHeight(int height) {
        glfwSetWindowSize(handle, width, height);
    }

    /**
     * Sets whether vertical synchronization is enabled.
     *
     * @param vSync whether vertical synchronization is enabled.
     */
    public void setVSync(boolean vSync) {
        this.vSync = vSync;
    }

    /**
     * Sets the x position.
     *
     * @param x the x position.
     */
    public void setX(int x) {
        glfwSetWindowPos(handle, x, y);
    }

    /**
     * Sets the y position.
     *
     * @param y the y position.
     */
    public void setY(int y) {
        glfwSetWindowPos(handle, x, y);
    }

    /**
     * Updates the {@code Window}.
     */
    public void update() {
        glfwSwapBuffers(handle);
        glfwPollEvents();
    }

    /**
     * Destroys the {@code Window} and frees all memory allocations.
     */
    public void destroy() {
        glfwDestroyWindow(handle);
        glfwFreeCallbacks(handle);
        glfwTerminate();
    }

    /**
     * Sets the callbacks.
     */
    private void setCallbacks() {
        EventBus eventBus = Application.getInstance().getEventBus();

        eventBus.publish(new WindowCreateEvent(this));
        glfwSetWindowFocusCallback(handle, (handle, focused) -> {
            eventBus.publish(new WindowFocusEvent(this, focused));
        });
        glfwSetWindowMaximizeCallback(handle, (handle, maximized) -> {
            eventBus.publish((new WindowMaximizeEvent(this, maximized)));
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
        glfwSetWindowCloseCallback(handle, handle -> {
            eventBus.publish(new WindowDestroyEvent(this));
        });
    }

}
