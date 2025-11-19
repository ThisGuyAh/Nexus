package com.nexus.core;

import com.nexus.event.Event;
import com.nexus.event.EventBus;
import com.nexus.event.WindowDestroyEvent;
import com.nexus.event.WindowMinimizeEvent;
import java.util.concurrent.locks.ReentrantLock;

import static com.nexus.utility.Time.*;
import static org.lwjgl.glfw.GLFW.*;

/**
 * @author Christopher Ruley
 */
public abstract class Application {

    /**
     * The main {@code Window} for the {@code Application}.
     */
    private Window window;

    /**
     * The creation {@code Thread}.
     */
    private Thread createThread;

    /**
     * The rendering {@code Thread}.
     */
    private Thread renderThread;

    /**
     * The context {@code ReentrantLock}.
     */
    private ReentrantLock contextReentrantLock;

    /**
     * Whether the {@code Application} is running.
     */
    private volatile boolean running;

    /**
     * Whether the {@code Application} is minimized.
     */
    private volatile boolean minimized;

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
        createThread = new Thread(this::create);
        renderThread = new Thread(this::render);
        contextReentrantLock = new ReentrantLock();
        running = false;
        minimized = false;
        ups = 0;
        fps = 0;
        interpolation = 0.0D;
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

    public final int getUPS() {
        return ups;
    }

    public final int getFPS() {
        return fps;
    }

    /**
     * The user-implemented method for the creation stage. Called on the create {@code Thread}.
     */
    protected abstract void onCreate();

    /**
     * The user-implemented method for the updating stage. Called on the main {@code Thread}.
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
        EventBus.getInstance().register(this);

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW");
        }

        window.create();
        createThread.start();
        renderThread.start();
        update();
        destroy();
    }

    /**
     * The creation stage. Called on the create {@code Thread}.
     */
    private void create() {
        contextReentrantLock.lock();

        try {
            window.setContextCurrent(true);
            onCreate();
        } finally {
            window.setContextCurrent(false);
            contextReentrantLock.unlock();
        }
    }

    /**
     * The updating stage. Called on the main {@code Thread}.
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

            glfwPollEvents();
        }
    }

    /**
     * The rendering stage. Called on the render {@code Thread}.
     */
    private void render() {
        contextReentrantLock.lock();

        // TODO Add fps calculation
        
        try {
            window.setContextCurrent(true);

            while (running) {
                if (!minimized) {
                    onRender(interpolation);
                    window.swapBuffers();
                }
                if (!window.isVSync()) {
                    sync(120); // TODO Add functionality for changing this value
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
        onDestroy();
        window.destroy();
        glfwTerminate();

        // TODO Tidy up threads?
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
