package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowResizeEvent {
	
	private Window window;
	
	public WindowResizeEvent(Window window) {
		this.window = window;
	}
	
	public Window getWindow() {
		return window;
	}

}
