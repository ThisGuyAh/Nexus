package com.codex.nexus.core;

import com.codex.nexus.event.ApplicationInitializeEvent;
import com.codex.nexus.event.ApplicationRenderEvent;
import com.codex.nexus.event.ApplicationTerminateEvent;
import com.codex.nexus.event.ApplicationUpdateEvent;
import com.codex.nexus.event.Event;
import com.codex.nexus.event.EventBus;
import com.codex.nexus.event.WindowCreateEvent;
import com.codex.nexus.event.WindowDestroyEvent;

public class Application {

    /**
     * Contains the singleton instance of the {@code Application}.
     */
    private static class Singleton {

        /**
         * The singleton instance of the {@code Application}.
         */
        private static final Application INSTANCE = new Application();

    }

    /**
     * Gets the singleton instance of the {@code Application}.
     *
     * @return the singleton instance of the {@code Application}.
     */
    public static Application getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * The {@code EventBus}.
     */
    private EventBus eventBus;

    /**
     * The {@code Window}.
     */
    private Window window;

    /**
     * Whether the {@code Application} is running.
     */
    private boolean running;

    /**
     * Cannot instantiate {@code Application}.
     */
    private Application() {
        eventBus = new EventBus();

        eventBus.register(this);
    }

    /**
     * Gets the {@code EventBus}.
     *
     * @return the {@code EventBus}.
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * Gets the {@code Window}.
     *
     * @return the {@code Window}.
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Starts the {@code Application}.
     */
    public void start() {
        running = true;

        eventBus.publish(new ApplicationInitializeEvent());
        loop();
        eventBus.publish(new ApplicationTerminateEvent());
    }

    /**
     * Stops the {@code Application}.
     */
    public void stop() {
        running = false;

        window.delete();
    }

    /**
     * The main loop of the {@code Application}.
     */
    private void loop() {
        while (running) {
            eventBus.publish(new ApplicationUpdateEvent());
            eventBus.publish(new ApplicationRenderEvent());
            Input.update();
            window.update();
        }
    }

    // May be changed later
    @Event
    public void onEvent(WindowCreateEvent event) {
        if (window == null) {
            window = event.getWindow();
        }
    }

    // May be changed later
    @Event
    public void onEvent(WindowDestroyEvent event) {
        if (window.equals(event.getWindow())) {
            stop();
        }
    }

}
