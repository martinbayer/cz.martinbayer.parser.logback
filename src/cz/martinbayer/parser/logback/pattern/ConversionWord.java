package cz.martinbayer.parser.logback.pattern;

public class ConversionWord {

	private String convWord;
	private String format;
	private String properties;

	/** @param pattern
	 *            -partial pattern */
	public ConversionWord(String pattern) {
		this.convWord = PatternParser.getConvWord(pattern);
		this.format = PatternParser.getFormat(pattern);
		this.properties = PatternParser.getProperties(pattern);
	}

	public String getConvWord() {
		return convWord;
	}

	public String getFormat() {
		return format;
	}

	public String getProperties() {
		return properties;
	}
}
