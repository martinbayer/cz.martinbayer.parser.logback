package cz.martinbayer.parser.logback;

import java.nio.CharBuffer;

public class LogFileSemaphoreWatchedStore {

	private boolean signal = false;
	public CharBuffer storedBuffer;
	private boolean stopped = false;;

	public synchronized void take() {
		this.signal = true;
		notify();
	}

	public synchronized void release() throws InterruptedException {
		while (!this.signal) {
			wait();
		}
		this.signal = false;
	}

	public void store(CharBuffer sb) {
		this.storedBuffer = sb;
	}

	public synchronized CharBuffer getStoredBuffer() {
		return this.storedBuffer;
	}

	public boolean isStopped() {
		synchronized (this) {
			return stopped;
		}
	}

	public void stop() {
		synchronized (this) {
			this.stopped = true;
		}
	}

}
