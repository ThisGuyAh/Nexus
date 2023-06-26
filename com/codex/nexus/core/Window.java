package com.codex.nexus.core;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.codex.nexus.event.EventBus;
import com.codex.nexus.event.KeyPressEvent;
import com.codex.nexus.event.KeyReleaseEvent;
import com.codex.nexus.event.WindowCreateEvent;
import com.codex.nexus.event.WindowDestroyEvent;
import com.codex.nexus.event.WindowFocusEvent;
import com.codex.nexus.event.WindowMaximizeEvent;
import com.codex.nexus.event.WindowMoveEvent;
import com.codex.nexus.event.WindowResizeEvent;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import java.nio.IntBuffer;

public class Window {

    private String title;
    private int width;
    private int height;
    private boolean vSync;
    private long handle;
    private int x;
    private int y;

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

        EventBus eventBus = Application.getInstance().getEventBus();

        glfwSetWindowFocusCallback(handle, (handle, focused) -> {
            eventBus.publish(new WindowFocusEvent(this, focused));
        });

        glfwSetWindowMaximizeCallback(handle, (handle, maximized) -> {
            eventBus.publish((new WindowMaximizeEvent(this, maximized)));
        });

        glfwSetWindowPosCallback(handle, (handle, newX, newY) -> {
            this.x = newX;
            this.y = newY;

            eventBus.publish(new WindowMoveEvent(this));
        });

        glfwSetWindowSizeCallback(handle, (handle, newWidth, newHeight) -> {
            this.width = newWidth;
            this.height = newHeight;

            eventBus.publish(new WindowResizeEvent(this));
        });

        glfwSetWindowCloseCallback(handle, handle -> {
            eventBus.publish(new WindowDestroyEvent(this));
        });

        glfwSetKeyCallback(handle, (window, key, scancode, action, mods) -> {
            switch (action) {
                case GLFW_PRESS, GLFW_REPEAT -> eventBus.publish(new KeyPressEvent(this, key));
                case GLFW_RELEASE -> eventBus.publish(new KeyReleaseEvent(this, key));
            }
        });

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

        eventBus.publish(new WindowCreateEvent(this));
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWidth(int width) {
        glfwSetWindowSize(handle, this.width = width, height);
    }

    public void setHeight(int height) {
        glfwSetWindowSize(handle, width, this.height = height);
    }

    public void setVSync(boolean vSync) {
        this.vSync = vSync;
    }

    public void setX(int x) {
        glfwSetWindowPos(handle, this.x = x, y);
    }

    public void setY(int y) {
        glfwSetWindowPos(handle, x, this.y = y);
    }

    public void update() {
        glfwSwapBuffers(handle);
        glfwPollEvents();
    }

    public void delete() {
        glfwDestroyWindow(handle);
        glfwFreeCallbacks(handle);
        glfwTerminate();
    }

}
