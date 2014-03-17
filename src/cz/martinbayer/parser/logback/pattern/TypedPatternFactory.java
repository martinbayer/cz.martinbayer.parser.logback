package cz.martinbayer.parser.logback.pattern;

import java.util.HashMap;

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

	public static void getRegexPattern(String s, String configPattern) {
		String[] partialPatterns = PatternParser
				.getConfigPatternParts(configPattern);
		StringBuffer regexPattern = new StringBuffer();
		ConversionWord convWord;
		for (String partPattern : partialPatterns) {
			convWord = new ConversionWord(partPattern);
			TypedPattern pattern = typedPatterns.get(convWord.getConvWord());
			if (pattern != null) {
				System.out.println(pattern.getRegex(convWord));
			}
		}
	}
}
