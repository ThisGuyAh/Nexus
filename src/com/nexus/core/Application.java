package com.nexus.core;

import com.link.event.Bus;
import com.link.event.Subscribe;
import com.nexus.event.WindowCloseEvent;
import com.nexus.event.WindowResizeEvent;
import com.nexus.event.WindowMinimizeEvent;
import static com.nexus.utility.Time.getCurrentTimeSeconds;
import static com.nexus.utility.Time.sync;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glViewport;

/**
 * @author Christopher Ruley
 */
public abstract class Application {

    /**
     * The main {@code Bus} for the {@code Application}.
     */
    private Bus bus;

    /**
     * The main {@code Window} for the {@code Application}.
     */
    private Window window;

    /**
     * Whether the {@code Application} is running.
     */
    private boolean running;

    /**
     * Whether the {@code Application} is minimized.
     */
    private boolean minimized;

    /**
     * Whether vertical synchronization is enabled.
     */
    private boolean vsync;

    /**
     * The updates-per-second.
     */
    private int ups;

    /**
     * The frames-per-second.
     */
    private int fps;

    /**
     * Constructs an {@code Application}.
     */
    protected Application() {
        bus = new Bus();
        window = new Window(bus);
    }

    public final Bus getBus() {
        return bus;
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
     * The user-implemented method for the creation stage.
     */
    protected abstract void onCreate();

    /**
     * The user-implemented method for the updating stage.
     */
    protected abstract void onUpdate();

    /**
     * The user-implemented method for the rendering stage.
     *
     * @param interpolation the value used for rendering according to updates.
     */
    protected abstract void onRender(double interpolation);

    /**
     * The user-implemented method for the destruction stage.
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
     * Runs the {@code Application}.
     */
    private void run() {
        try {
            bus.register(this);

            if (!glfwInit()) {
                throw new IllegalStateException("Failed to initialize GLFW!");
            }

            window.create();

            if (!window.isCreated()) {
                throw new IllegalStateException("Failed to create the window!");
            }

            window.setContextCurrent(true);
            onCreate();

            if (!window.isVisible()) {
                window.setVisible(true);
            }

            final double maxFrameTime = 0.25D;
            final double updateInterval = 1.0D / 60.0D; // TODO Add functionality for changing this value
            double previousTime = getCurrentTimeSeconds();
            double accumulator = 0.0D;
            double counter = 0.0D;
            int upsCounter = 0;
            int fpsCounter = 0;

            while (running) {
                glfwPollEvents();

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
                    fps = fpsCounter;
                    upsCounter = 0;
                    fpsCounter = 0;
                    counter = 0;
                }
                if (!minimized) {
                    onRender(accumulator / updateInterval);
                    window.swapBuffers();

                    fpsCounter++;
                }
                if (!vsync) {
                    sync(120); // TODO Add ability to change this during runtime.
                }
            }
        } finally {
            onDestroy();
            window.destroy();
            glfwTerminate();
        }
    }

    /**
     * Listens for a {@code WindowResizeEvent} and updates the {@code Application} resized state.
     *
     * @param event the event listed for.
     */
    @Subscribe
    private void onEvent(WindowResizeEvent event) {
        glViewport(0, 0, window.getWidth(), window.getHeight());
    }

    /**
     * Listens for a {@code WindowMinimizeEvent} and updates the {@code Application} minimized state.
     *
     * @param event the event listed for.
     */
    @Subscribe
    private void onEvent(WindowMinimizeEvent event) {
        minimized = event.isMinimized();
    }

    /**
     * Listens for a {@code WindowCloseEvent} and updates the {@code Application} running state.
     *
     * @param event the event listed for.
     */
    @Subscribe
    private void onEvent(WindowCloseEvent event) {
        running = false;
    }

}