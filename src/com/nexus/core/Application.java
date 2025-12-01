
package com.nexus.core;

import com.nexus.event.*;

import java.util.concurrent.locks.ReentrantLock;

import static com.nexus.utility.Time.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author Christopher Ruley
 */
public abstract class Application {

    // TODO Add multiple window functionality safely with context ownership for rendering.

    /**
     * The main {@code Window} for the {@code Application}.
     */
    private final Window window;

    /**
     * The context {@code ReentrantLock}.
     */
    private final ReentrantLock contextReentrantLock;

    /**
     * The updating {@code Thread}.
     */
    private Thread updateThread;

    /**
     * The rendering {@code Thread}.
     */
    private Thread renderThread;

    /**
     * Whether the {@code Application} is running.
     */
    private volatile boolean running;

    /**
     * Whether the {@code Application} is minimized.
     */
    private volatile boolean minimized;

    /**
     * Whether the {@code Application} is resized.
     */
    private volatile boolean resized;

    /**
     * Whether vertical synchronization is enabled.
     */
    private volatile boolean vsync;

    /**
     * The updates-per-second.
     */
    private volatile int ups;

    /**
     * The frames-per-second.
     */
    private volatile int fps;

    /**
     * The value used for rendering according to updates.
     */
    private volatile double interpolation;

    /**
     * Constructs an {@code Application}.
     */
    protected Application() {
        window = new Window();
        contextReentrantLock = new ReentrantLock();
    }

    public final Window getWindow() {
        return window;
    }

    public final boolean isRunning() {
        return running;
    }

    public final boolean isMinimized() {
        return minimized;
    }

    public final boolean isVsync() {
        return vsync;
    }

    public final int getUPS() {
        return ups;
    }

    public final int getFPS() {
        return fps;
    }

    /**
     * The user-implemented method for the creation stage. Called on the main {@code Thread}.
     */
    protected abstract void onCreate();

    /**
     * The user-implemented method for the updating stage. Called on the update {@code Thread}.
     */
    protected abstract void onUpdate();

    /**
     * The user-implemented method for the rendering stage. Called on the render {@code Thread}.
     *
     * @param interpolation the value used for rendering according to updates.
     */
    protected abstract void onRender(double interpolation);

    /**
     * The user-implemented method for the destruction stage. Called on the main {@code Thread}.
     */
    protected abstract void onDestroy();

    /**
     * Starts the {@code Application}.
     */
    public final void start() {
        if (running) {
            return;
        }

        running = true;

        run();
    }

    /**
     * Stops the {@code Application}.
     */
    public final void stop() {
        running = false;
    }

    /**
     * Restarts the {@code Application}.
     */
    public final void restart() {
        stop();
        start();
    }

    /**
     * Runs the {@code Application}.
     */
    private void run() {
       try {
           create();
       } finally {
           destroy();
       }
    }

    /**
     * The creation stage. Called on the main {@code Thread}.
     */
    private void create() {
        EventBus.getInstance().register(this);

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW!");
        }

        window.create();

        if (!window.isCreated()) {
            throw new IllegalStateException("Failed to create the window!");
        }

        window.setContextCurrent(true);
        onCreate();
        window.setContextCurrent(false);

        if (!window.isVisible()) {
            window.setVisible(true);
        }

        updateThread = new Thread(this::update, "Update");
        renderThread = new Thread(this::render, "Render");
        minimized = false;
        resized = true;
        vsync = false;
        ups = 0;
        fps = 0;
        interpolation = 0.0D;

        updateThread.start();
        renderThread.start();

        while (running) {
            glfwPollEvents();
        }
    }

    /**
     * The updating stage. Called on the update {@code Thread}.
     */
    private void update() {
        final double maxFrameTime = 0.25D;
        final double updateInterval = 1.0D / 60.0D; // TODO Add functionality for changing this value
        double previousTime = getCurrentTimeSeconds();
        double accumulator = 0.0D;
        double counter = 0.0D;
        int upsCounter = 0;

        while (running) {
            double currentTime = getCurrentTimeSeconds();
            double elapsedTime = currentTime - previousTime;

            if (elapsedTime > maxFrameTime) {
                elapsedTime = maxFrameTime;
            }

            previousTime = currentTime;
            accumulator += elapsedTime;
            counter += elapsedTime;

            while (accumulator >= updateInterval) {
                onUpdate();

                upsCounter++;
                accumulator -= updateInterval;
            }

            if (counter >= 1.0) {
                ups = upsCounter;
                upsCounter = 0;
                counter = 0;
            }

            interpolation = accumulator / updateInterval;
        }
    }

    /**
     * The rendering stage. Called on the render {@code Thread}.
     */
    private void render() {
        contextReentrantLock.lock();

        try {
            window.setContextCurrent(true);

            while (running) {
                if(resized) {
                    glViewport(0, 0, window.getWidth(), window.getHeight());

                    resized = false;
                }
                if (!minimized) {
                    onRender(interpolation);
                    window.swapBuffers();
                }
                if (!vsync) {
                    sync(120); // TODO Add ability to change this during runtime.
                }
            }
        } finally {
            window.setContextCurrent(false);
            contextReentrantLock.unlock();
        }
    }

    /**
     * The destruction stage. Called on the main {@code Thread}.
     */
    private void destroy() {
        stop();

        try {
            if (updateThread != null) {
                updateThread.join();
            }
            if (renderThread != null) {
                renderThread.join();
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

        onDestroy();
        window.destroy();
        glfwTerminate();
    }

    /**
     * Listens for a {@code WindowMinimizeEvent} and updates the {@code Application} minimized state.
     *
     * @param event the event listed for.
     */
    @Event
    private void onEvent(WindowMinimizeEvent event) {
        if (event.getWindow().equals(window)) {
            minimized = event.isMinimized();
        }
    }

    /**
     * Listens for a {@code WindowResizeEvent} and updates the {@code Application} resized state.
     *
     * @param event the event listed for.
     */
    @Event
    private void onEvent(WindowResizeEvent event) {
        if (event.getWindow().equals(window)) {
            resized = true;
        }
    }

    /**
     * Listens for a {@code WindowDestroyEvent} and updates the {@code Application} running state.
     *
     * @param event the event listed for.
     */
    @Event
    private void onEvent(WindowDestroyEvent event) {
        if (event.getWindow().equals(window)) {
            stop();
        }
    }

}