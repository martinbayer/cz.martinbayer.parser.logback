package cz.martinbayer.parser.logback.pattern.logic;

import cz.martinbayer.parser.logback.pattern.PatternParser;
import cz.martinbayer.parser.logback.pattern.TypedPattern;

public class FilePattern extends TypedPattern {

	private static final String GROUP_NAME = "file";

	private static final String FILE_REGEX = ".*";

	@Override
	public String getGroupName() {
		return GROUP_NAME;
	}

	@Override
	protected String initRegex() {
		StringBuffer sb = new StringBuffer();
		sb.append(PatternParser.GROUP_START).append(FILE_REGEX)
				.append(PatternParser.GROUP_END);
		return sb.toString();
	}

	@Override
	protected void initRegexProperty() {
		// none for level property
	}
}
