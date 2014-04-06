package cz.martinbayer.parser.logback.pattern;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypedPatternFactory {

	private static HashMap<String, TypedPattern> typedPatterns = new HashMap<>();
	static {
		initPatterns();
	}

	/** This method initializes map with conversion words and particular
	 * patterns. If there are more conversion words possible for pattern they
	 * are all mapped to the instance of the TypedPattern e.g.:<br />
	 * <ul>
	 * <li>map.put("c",loggerPatternInstance)</li>
	 * <li>map.put("lo",loggerPatternInstance)</li>
	 * <li>map.put("logger",loggerPatternInstance)</li>
	 * </ul> */
	private static void initPatterns() {
		for (ConversionTypesEnum type : ConversionTypesEnum.values()) {
			for (String convWord : type.getWords().getConversionWords()) {
				typedPatterns.put(convWord, type.getTypedPattern());
			}
		}
	}

	private static synchronized final TypedPattern getTypedPattern(
			String convWord) {
		return typedPatterns.get(convWord);
	}

	public static String getRegexPattern(String configPattern) {
		String[] partialPatterns = PatternParser
				.getConfigPatternParts(configPattern);
		String[] between = new String[partialPatterns.length - 1];
		String leftSide, rightSide;
		Pattern p;
		Matcher m;
		for (int i = 0; i < between.length; i++) {
			leftSide = "(?<=" + Pattern.quote(partialPatterns[i]) + ")";
			rightSide = "(?=" + Pattern.quote(partialPatterns[i + 1]) + ")";
			p = Pattern.compile(leftSide + "(.*)" + rightSide);
			m = p.matcher(configPattern);
			if (m.find()) {
				between[i] = m.group().replace(")", "").replace("(", "")
						.replace(" ", "\\s").replace("[", "\\[")
						.replace("]", "\\]");
			} else {
				between[i] = null;
			}
		}
		StringBuffer regexPattern = new StringBuffer();
		ConversionWord convWord;
		int i = 0;
		for (String partPattern : partialPatterns) {
			convWord = new ConversionWord(partPattern);
			TypedPattern pattern = typedPatterns.get(convWord.getConvWord());
			if (pattern != null) {
				regexPattern.append(pattern.getRegex(convWord)).append(
						i < between.length ? between[i++] : "");
			}
		}
		return regexPattern.toString();
	}
}
