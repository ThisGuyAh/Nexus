package com.codex.nexus.core;

import com.codex.nexus.event.EventBus;

public class Application {

    private static class Inner {

        public static final Application INSTANCE = new Application();

    }

    private EventBus eventBus;

    private Window window;

    private Logical logical;

    private boolean running;

    private Application() {
        eventBus = new EventBus();
        window = new Window();
        running = false;
    }

    public static Application getInstance() {
        return Inner.INSTANCE;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Window getWindow() {
        return window;
    }

    public Logical getLogical() {
        return logical;
    }

    public void start(Logical logical) {
        if (running) {
            return;
        }
        
        this.logical = logical;
        running = true;

        eventBus.register(logical);
        run();
    }

    public void stop() {
        running = false;
    }

    private void run() {
        logical.onInitialize();
        window.initialize();

        // TODO Add functionality to continue running until all windows are closed, and main loop logic.
        while (running && window.isRunning()) {
            logical.onInput();
            logical.onUpdate();
            logical.onRender();
            window.update();
        }

        logical.onDestroy();
        window.destroy();
    }

}
