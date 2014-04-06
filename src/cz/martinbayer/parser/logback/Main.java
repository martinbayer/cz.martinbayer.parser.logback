package cz.martinbayer.parser.logback;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.martinbayer.parser.logback.pattern.PatternParser;
import cz.martinbayer.parser.logback.pattern.TypedPatternFactory;
import cz.martinbayer.parser.logback.pattern.logic.LogicClass;

public class Main {
	private static Logger l = LoggerFactory.getLogger(Main.class);

	private static int previousLastRow;

	public static final String STACKTRACE_REGEX = "(?s)(\\s+\\S+(?:Exception|Error)[^\\n]+(?:\\s++at\\s+[^\\n]+)++)(?:\\s*\\.{3}[^\\n]++)?\\s";
	public static final String LEVEL_REGEX = "OFF|WARN|ERROR|INFO|DEBUG|TRACE|ALL";

	public static void main(String[] args) throws InterruptedException,
			IOException {

		countRows();
		// LogFileSemaphoreWatchedStore semaphore = new
		// LogFileSemaphoreWatchedStore();
		// LogFileReader fileReader = new LogFileReader(semaphore, null);
		// TestingReader testReader = new TestingReader(semaphore);
		// Thread fileReaderThread = new Thread(fileReader);
		// Thread testReaderThread = new Thread(testReader);
		// fileReaderThread.start();
		// testReaderThread.start();
		// testDecode();
		// testDateParsing();
	}

	static CharBuffer buffer = CharBuffer.allocate(8);

	private static void appendToBuffer(String str) {
		int newSize = 0;
		if (buffer.position() + str.length() > buffer.capacity()) {
			newSize = buffer.capacity() * 2;
		} else if (buffer.position() + str.length() < buffer.capacity() / 2) {
			newSize = buffer.capacity() / 2;
		}
		System.out.println("new size:" + newSize);
		if (newSize > 0) {
			CharBuffer temp = CharBuffer.allocate(newSize);
			temp.put(buffer.subSequence(0, newSize));
			buffer = temp;
		}
		buffer.append(str);
		System.out.println(buffer.capacity());

	}

	private static void countRows() {
		String pattern = "(?s)(?<datetime>\\w{2}/\\w{2}/\\w{2} \\w{2}:\\w{2}:\\w{2}.\\w{3})"
				+ "\\s(?<level>(OFF|WARN|ERROR|INFO|DEBUG|TRACE|ALL))"
				+ "\\s(?<message>(.*?))"
				+ "\\s(?<exception>((?s)\\S*(Exception|Error):[^\\n\\r]*(\\n|\\r|\\r\\n)((\\s*at[^\\n\\r]*(\\n|\\r|\\r\\n))|(Caused by:[^\\n\\r]*(\\n|\\r|\\r\\n))|(\\s*\\.{3} [0-9]+ common frames omitted(\\n|\\r|\\r\\n)))*))?+\\s\\[(?<thread>(.*?))\\]\\s\\[(?<file>(.*?)):(?<line>(\\d+))\\](?<newline>(\\n|\\r|\\r\\n))";

		File[] files = new File[] { new File(
				"D:\\School\\Mgr\\Diploma thesis\\logback\\logs\\client_bootstrap_fail.log") };
		int count = 0;
		for (File f : files) {
			System.out.println(f.getName());
			String input = "N/A";

			byte[] encoded;
			try {
				encoded = Files.readAllBytes(f.toPath());
				input = Charset.defaultCharset()
						.decode(ByteBuffer.wrap(encoded)).toString();
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(input);
				long time = System.currentTimeMillis();
				while (m.find()) {
					for (int i = 0; i < m.groupCount(); i++) {
						String str = m.group(i);
						if (str != null) {
							System.out.println("group_"
									+ i
									+ ":"
									+ str.substring(0,
											Math.min(100, str.length())));
						}
					}
				}
				System.out.println("time to while:"
						+ (System.currentTimeMillis() - time));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(count);
	}

	private void createRandomLogFile() throws InterruptedException {
		l.debug("message 1");
		LogicClass logic = new LogicClass();
		logic.doSomething();

	}

	private static void testDateParsing() throws IOException {
		File file = new File(
				"D:\\School\\Mgr\\Diploma thesis\\logback\\logs\\client_error.log");
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		FileChannel channel = randomFile.getChannel();
		CharBuffer buffer = CharBuffer.allocate(1024);

		String pattern = "(?<datetime>\\w{2}/\\w{2}/\\w{2} \\w{2}:\\w{2}:\\w{2}.\\w{3})\\s(?<level>(OFF|WARN|ERROR|INFO|DEBUG|TRACE|ALL))\\s(?<message>(.*))\\s(?<exception>((?s)\\S*(Exception|Error):[^\\n\\r]*(\\n|\\r|\\r\\n)((\\s*at[^\\n\\r]*(\\n|\\r|\\r\\n))|(Caused by:[^\\n\\r]*(\\n|\\r|\\r\\n))|(\\s*\\.\\.\\. [0-9]+ common frames omitted(\\n|\\r|\\r\\n)))*))?\\s\\[(?<thread>(.*))\\]\\s\\[(?<file>(.*)):(?<line>(\\d+))\\](?<newline>(\\n|\\r|\\r\\n))";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		while (m.find()) {
			System.out
					.println("-----------------------start----------------------");
			System.out.println("date time: " + m.group("datetime"));
			System.out.println("level: " + m.group("level"));
			System.out.println("message: " + m.group("message"));
			System.out.println("exception: " + m.group("exception"));
			System.out.println("thread: " + m.group("thread"));
			System.out.println("file: " + m.group("file"));
			System.out.println("line: " + m.group("line"));
			System.out
					.println("-------------------------end----------------------");
		}

	}

	private static void print(String pattern, String inputString) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(inputString);
		while (m.find()) {
			System.out
					.println("-----------------------start----------------------");
			System.out.println("date time: " + m.group("datetime"));
			System.out.println("level: " + m.group("level"));
			System.out.println("message: " + m.group("message"));
			System.out.println("exception: " + m.group("exception"));
			System.out.println("thread: " + m.group("thread"));
			System.out.println("file: " + m.group("file"));
			System.out.println("line: " + m.group("line"));
			System.out
					.println("-------------------------end----------------------");

		}
	}

	private static void testDecode() {
		byte[] encoded;
		String input = "N/A";
		try {
			encoded = Files.readAllBytes(new File("c://client_debug.log")
					.toPath());
			input = Charset.defaultCharset().decode(ByteBuffer.wrap(encoded))
					.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pattern = "%-20(%d{MM/dd/yy HH:mm:ss.SSS} %-5level) %msg %xEx{full} [%thread] [%file:%line]%n";
		String s = TypedPatternFactory.getRegexPattern(pattern);
		// test(s, input);
		print(s, input);
		// System.out.println(s);
	}

	private static void test(String configPattern, String input) {
		String[] partialPatterns = PatternParser
				.getConfigPatternParts(configPattern);
		String one = partialPatterns[1];
		String two = partialPatterns[2];
		String oneQuoted = Pattern.quote(one);
		String twoQuoted = Pattern.quote(two);
		oneQuoted = "(?<=" + oneQuoted + ")";
		twoQuoted = "(?=" + twoQuoted + ")";
		Pattern p = Pattern.compile(oneQuoted + "(.*)" + twoQuoted);
		Matcher m = p.matcher(configPattern);
		while (m.find()) {
			System.out.println("begin" + m.group() + "end");
		}
	}

	void findTime(String input, String timePattern) {
		// String regex =
		// "(?<datetime>"+DateTimePattern.patternToRegex(timePattern)+")";
		// Pattern pattern = Pattern.compile(regex);
		// Matcher dateTimeMatcher = pattern.matcher(input);
		// if(dateTimeMatcher.find()){
		// System.out.println(dateTimeMatcher.group());
		// }
	}

	void findLevel(String input) {
		String levelPattern = "(?<level>" + LEVEL_REGEX + ")";
		Pattern pattern = Pattern.compile(levelPattern);
		Matcher levelMatcher = pattern.matcher(input);
		System.out.println(levelMatcher.group("level"));
	}

	void findException(String input) {
		Pattern pattern = Pattern.compile(STACKTRACE_REGEX);
		Matcher matcher = pattern.matcher(input);
		System.out.println(matcher.group());
	}
}
