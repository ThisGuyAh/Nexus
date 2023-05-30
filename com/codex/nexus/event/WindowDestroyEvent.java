package com.codex.nexus.event;

public class WindowDestroyEvent {

	private long handle;

	public WindowDestroyEvent(long handle) {
		this.handle = handle;
	}

	public long getHandle() {
		return handle;
	}

}
