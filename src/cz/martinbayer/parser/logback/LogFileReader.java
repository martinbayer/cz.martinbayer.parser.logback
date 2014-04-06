package cz.martinbayer.parser.logback;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class LogFileReader implements Runnable {

	private LogFileSemaphoreWatchedStore semaphore;
	private File[] filesToRead;
	private CharBuffer sb;

	public LogFileReader(LogFileSemaphoreWatchedStore semaphore,
			File[] filesToRead) {
		this.semaphore = semaphore;
		sb = CharBuffer.allocate(8192);
		this.semaphore.store(sb);
		this.filesToRead = filesToRead;
	}

	@Override
	public void run() {
		File file = new File("C:\\client_debug.log");
		try (FileChannel fileChannel = new RandomAccessFile(file, "r")
				.getChannel()) {

			ByteBuffer buffer = ByteBuffer.allocate(8192);
			String encoding = "UTF-8";
			int index = 0;
			while (fileChannel.read(buffer) != -1) {
				buffer.flip();
				CharBuffer s = Charset.forName(encoding).decode(buffer);
				if(s.length()+sb.p)
				sb.pu
				this.semaphore.take();
				buffer.clear();
			}
			System.out.println(sb);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
