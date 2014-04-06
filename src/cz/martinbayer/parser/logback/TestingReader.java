package cz.martinbayer.parser.logback;

public class TestingReader implements Runnable {

	private LogFileSemaphoreWatchedStore semaphore;
	private StringBuffer storedBuffer;

	public TestingReader(LogFileSemaphoreWatchedStore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			this.storedBuffer = semaphore.storedBuffer;
			int actualBufferSize = -1;
			while (!semaphore.isStopped()) {
				semaphore.release();
				actualBufferSize = this.storedBuffer.length();

				this.storedBuffer.delete(0, actualBufferSize);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
