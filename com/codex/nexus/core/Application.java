package com.codex.nexus.core;

import com.codex.nexus.event.EventBus;

import static com.codex.nexus.utility.Time.*;

public class Application {

    private static class Instance {
        public static final Application INSTANCE = new Application();
    }

    private Settings settings;
    private EventBus eventBus;
    private Window window;
    private Logical logical;
    private boolean running;
    private int ups;
    private int fps;

    private Application() {
        settings = new Settings();
        eventBus = new EventBus();
        window = new Window();
        running = false;
        ups = 0;
        fps = 0;
    }

    public static Application getInstance() {
        return Instance.INSTANCE;
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

    public int getUPS() {
        return ups;
    }

    public int getFPS() {
        return fps;
    }

    public void start(Logical logical) {
        if (running) {
            return;
        }

        //TODO add functionality to reset application and assign new Logical object

        running = true;
        this.logical = logical;

        eventBus.register(logical);
        run();
    }

    public void stop() {
        running = false;
    }

    private void run() {
        logical.onCreate();
        window.create();

        final double maxFrameTime = 0.25D;
        final double updateInterval = 1.0D / settings.getUPSLimit();

        double previousTime = getCurrentTimeSeconds();
        double accumulator = 0.0D;
        double counter = 0.0D;
        int upsCounter = 0;
        int fpsCounter = 0;

        while (running && window.isRunning()) {
            double currentTime = getCurrentTimeSeconds();
            double elapsedTime = currentTime - previousTime;

            if (elapsedTime > maxFrameTime) {
                elapsedTime = maxFrameTime;
            }

            previousTime = currentTime;
            accumulator += elapsedTime;
            counter += elapsedTime;

            while (accumulator >= updateInterval) {
                logical.onInput();
                logical.onUpdate(updateInterval);

                upsCounter++;
                accumulator -= updateInterval;
            }

            double alpha = accumulator / updateInterval;

            logical.onRender(alpha);
            window.update();

            fpsCounter++;

            if (counter >= 1.0) {
                ups = upsCounter;
                fps = fpsCounter;
                upsCounter = 0;
                fpsCounter = 0;
                counter = 0;
            }

            if (!settings.isVSync()) {
                sync(settings.getFPSLimit());
            }
        }

        logical.onDestroy();
        window.destroy();
    }

}
