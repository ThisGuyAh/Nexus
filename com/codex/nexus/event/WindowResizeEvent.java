package com.codex.nexus.event;

import com.codex.nexus.core.Window;

public class WindowResizeEvent {

	private Window window;
	private int oldWidth;
	private int oldHeight;
	
	public WindowResizeEvent(Window window, int oldWidth, int oldHeight) {
		this.window = window;
		this.oldWidth = oldWidth;
		this.oldHeight = oldHeight;
	}
	
	public Window getWindow() {
		return window;
	}

	public int getOldWidth() {
		return oldWidth;
	}

	public int getOldHeight() {
		return oldHeight;
	}

}
