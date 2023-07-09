package com.codex.nexus.event;

import com.codex.nexus.input.Key;

public class KeyPressEvent {

    private Key key;
    private boolean repeat;

    public KeyPressEvent(Key key, boolean repeat) {
        this.key = key;
        this.repeat = repeat;
    }

    public Key getKey() {
        return key;
    }

    public boolean isRepeat() {
        return repeat;
    }

    @Override
    public String toString() {
        return "KeyPressEvent: " + key;
    }

}
