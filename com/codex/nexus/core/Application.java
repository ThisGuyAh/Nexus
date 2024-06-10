package com.codex.nexus.core;

import com.codex.nexus.event.Event;
import com.codex.nexus.event.EventBus;
import com.codex.nexus.event.WindowDestroyEvent;
import com.codex.nexus.event.WindowMinimizeEvent;

import static com.codex.nexus.utility.Time.*;

/**
 * {@code Application} ...
 *
 * @author Christopher Ruley
 */
public abstract class Application {

    /**
     * The {@code Settings}.
     */
    private Settings settings;

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Whether running.
     */
    private boolean running;

    /**
     * Whether minimized.
     */
    private boolean minimized;

    /**
     * The updates-per-second.
     */
    private int ups;

    /**
     * The frames-per-second.
     */
    private int fps;

    /**
     * Instantiates an {@code Application}.
     */
    protected Application() {
        settings = new Settings();
        window = new Window();
        running = false;
        minimized = false;
        ups = 0;
        fps = 0;
    }

    /**
     * @return the {@code Settings}.
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * @return the {@code Window}.
     */
    public Window getWindow() {
        return window;
    }

    // Is this getter needed?

    /**
     * @return whether running.
     */
    public boolean isRunning() {
        return running;
    }

    // Is this getter needed?

    /**
     * @return whether minimized.
     */
    public boolean isMinimized() {
        return minimized;
    }

    /**
     * @return the updates-per-second.
     */
    public int getUPS() {
        return ups;
    }

    /**
     * @return the frames-per-second.
     */
    public int getFPS() {
        return fps;
    }

    /**
     * The creation stage.
     */
    protected abstract void onCreate();

    /**
     * The input-polling stage.
     */
    protected abstract void onInput();

    /**
     * The updating stage.
     */
    protected abstract void onUpdate();

    /**
     * The rendering stage.
     */
    protected abstract void onRender(double alpha);

    /**
     * The destruction stage.
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
     * Restarts the {@code Application}.
     */
    public final void restart() {
        stop();
        start();
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
        EventBus.getInstance().register(this);
        onCreate();
        window.create();

        final double maxFrameTime = 0.25D;
        final double updateInterval = 1.0D / settings.getUPSLimit();
        double previousTime = getCurrentTimeSeconds();
        double accumulator = 0.0D;
        double counter = 0.0D;
        int upsCounter = 0;
        int fpsCounter = 0;

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
                onInput();

                // Should the fixed delta time be passed to the onUpdate() method?
                onUpdate();

                upsCounter++;
                accumulator -= updateInterval;
            }

            if (!minimized) {
                onRender(accumulator / updateInterval);
            }

            window.update();

            fpsCounter++;

            if (counter >= 1.0D) {
                ups = upsCounter;
                fps = fpsCounter;
                upsCounter = 0;
                fpsCounter = 0;
                counter = 0;
            }
            // TODO glfwSwapInterval is not updated.
            if (!settings.isVSync()) {
                sync(settings.getFPSLimit());
            }
        }

        onDestroy();
        window.destroy();
    }

    /**
     * Listens for a {@code WindowMinimizeEvent}.
     *
     * @param event the event listened for.
     */
    @Event
    public void onEvent(WindowMinimizeEvent event) {
        minimized = event.isMinimized();
    }

    /**
     * Listens for a {@code WindowDestroyEvent}.
     *
     * @param event the event listened for.
     */
    @Event
    public void onEvent(WindowDestroyEvent event) {
        stop();
    }

}
