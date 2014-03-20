package cz.martinbayer.parser.logback;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
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

	public static final String STACKTRACE_REGEX = "(?s)(\\s+\\S+(?:Exception|Error)[^\\n]+(?:\\s++at\\s+[^\\n]+)++)(?:\\s*\\.{3}[^\\n]++)?\\s";
	public static final String LEVEL_REGEX = "OFF|WARN|ERROR|INFO|DEBUG|TRACE|ALL";

	public static void main(String[] args) throws InterruptedException {
		// String[] regexs = { "x?+", "x*+", "x++", "x{2}+", "x{2,}+", "x{2,5}+"
		// };
		// String input = "xxxxxxx";
		//
		// for (String r : regexs) {
		// Pattern pattern = Pattern.compile(r);
		// Matcher matcher = pattern.matcher(input);
		//
		// //
		// // Find every match and print it
		// //
		// System.out.println("------------------------------");
		// System.out.format("Regex:  %s %n", r);
		// while (matcher.find()) {
		// System.out.format("Text '%s' found at %d to %d.%n",
		// matcher.group(), matcher.start(), matcher.end());
		// }
		// }
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
		String s = TypedPatternFactory.getRegexPattern(input, pattern);
		// test(pattern, input);
		System.out.println(s);
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
