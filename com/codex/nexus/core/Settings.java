package com.codex.nexus.core;

public class Settings {

    private boolean vSync;

    private int upsLimit;

    private int fpsLimit;

    public Settings() {
        vSync = false;
        upsLimit = 60;
        fpsLimit = 300;
    }

    public boolean isVSync() {
        return vSync;
    }

    public int getUPSLimit() {
        return upsLimit;
    }

    public int getFPSLimit() {
        return fpsLimit;
    }

    public void setVSync(boolean vSync) {
        this.vSync = vSync;
    }

    public void setUPSLimit(int upsLimit) {
        this.upsLimit = upsLimit;
    }

    public void setFPSLimit(int fpsLimit) {
        this.fpsLimit = fpsLimit;
    }

}
