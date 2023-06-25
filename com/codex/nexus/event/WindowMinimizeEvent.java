package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowMinimizeEvent {

	private Window window;

	public WindowMinimizeEvent(Window window) {
		this.window = window;
	}

	public Window getWindow() {
		return window;
	}

}
