package com.codex.nexus.event;

public class WindowMinimizeEvent {
	
	private long handle;
	
	public WindowMinimizeEvent(long handle) {
		this.handle = handle;
	}
	
	public long getHandle() {
		return handle;
	}

}
