package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowMaximizeEvent {
	
	private Window window;
	
	public WindowMaximizeEvent(Window window) {
		this.window = window;
	}
	
	public Window getWindow() {
		return window;
	}

}
