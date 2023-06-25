package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowDestroyEvent {

	private Window window;

	public WindowDestroyEvent(Window window) {
		this.window = window;
	}

	public Window getWindow() {
		return window;
	}

}
