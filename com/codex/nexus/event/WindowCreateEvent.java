package com.codex.nexus.event;

public class WindowCreateEvent {

	private long handle;

	public WindowCreateEvent(long handle) {
		this.handle = handle;
	}

	public long getHandle() {
		return handle;
	}

}
