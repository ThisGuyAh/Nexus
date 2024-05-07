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
     * The instance count.
     */
    private static int instanceCount;

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

    public static int getInstanceCount() {
        return instanceCount;
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

    public void setX(int x) {
        glfwSetWindowPos(handle, x, y);
    }

    public void setY(int y) {
        glfwSetWindowPos(handle, x, y);
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

    private void setPosition(int xOffset, int yOffset) {
        try (var memoryStack = stackPush()) {
            IntBuffer storedWidth = memoryStack.mallocInt(1);
            IntBuffer storedHeight = memoryStack.mallocInt(1);
            GLFWVidMode glfwVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwGetWindowSize(handle, storedWidth, storedHeight);
        }
    }

    /**
     * Initializes the {@code Window}.
     */
    public void create() {
        if (instanceCount == 0) {
            glfwInit();
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        handle = glfwCreateWindow(width, height, title, NULL, NULL);
        instanceCount++;

        setCallbacks();

        // TODO Add functionality for user to set screen position (i.e. LEFT_CENTER, CENTER, RIGHT_CENTER, etc.)

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
     * Updates the {@code Window}.
     */
    public void update() {
        glfwSwapBuffers(handle);
        glfwPollEvents();
    }

    /**
     * Destroys the {@code Window} and terminates GLFW if all instances are destroyed.
     */
    public void destroy() {
        glfwDestroyWindow(handle);
        glfwFreeCallbacks(handle);

        instanceCount--;

        if (instanceCount == 0) {
            glfwTerminate();
        }
    }

}
