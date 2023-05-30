package com.codex.nexus.event;

public class WindowMaximizeEvent {
	
	private long handle;
	
	public WindowMaximizeEvent(long handle) {
		this.handle = handle;
	}
	
	public long getHandle() {
		return handle;
	}

}
