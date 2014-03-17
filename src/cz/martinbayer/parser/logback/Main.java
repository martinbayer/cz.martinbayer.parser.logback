package cz.martinbayer.parser.logback;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.martinbayer.parser.logback.pattern.TypedPatternFactory;
import cz.martinbayer.parser.logback.pattern.logic.LogicClass;

public class Main {
	private static Logger l = LoggerFactory.getLogger(Main.class);

	public static final String STACKTRACE_REGEX = "(?s)(\\s+\\S+(?:Exception|Error)[^\\n]+(?:\\s++at\\s+[^\\n]+)++)(?:\\s*\\.{3}[^\\n]++)?\\s";
	public static final String LEVEL_REGEX = "OFF|WARN|ERROR|INFO|DEBUG|TRACE|ALL";

	public static void main(String[] args) throws InterruptedException {
		SimpleDateFormat f = new SimpleDateFormat("G");
		System.out.println(f.format(new Date()));
		// new Main().createRandomLogFile();
		testDecode();
	}

	private void createRandomLogFile() throws InterruptedException {
		l.debug("message 1");
		LogicClass logic = new LogicClass();
		logic.doSomething();

	}

	private static void testDecode() {
		byte[] encoded;
		String input = "N/A";
		try {
			encoded = Files.readAllBytes(new File("c://error.log").toPath());
			input = Charset.defaultCharset().decode(ByteBuffer.wrap(encoded))
					.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pattern = "%-20(%d{MM/dd/yy HH:mm:ss.SSS} %-5level) %msg %xEx{full} [%thread] [%file:%line]%n";
		TypedPatternFactory.getRegexPattern(input, pattern);
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
