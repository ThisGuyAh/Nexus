package com.codex.nexus.event;

public class WindowResizeEvent {
	
	private int width;
	private int height;
	private long handle;
	
	public WindowResizeEvent(int width, int height, long handle) {
		this.width = width;
		this.height = height;
		this.handle = handle;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public long getHandle() {
		return handle;
	}

}
