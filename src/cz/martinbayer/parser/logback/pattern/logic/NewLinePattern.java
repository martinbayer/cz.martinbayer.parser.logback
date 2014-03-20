package cz.martinbayer.parser.logback.pattern.logic;

import cz.martinbayer.parser.logback.pattern.PatternParser;
import cz.martinbayer.parser.logback.pattern.TypedPattern;

public class NewLinePattern extends TypedPattern {

	private static final String GROUP_NAME = "newline";
	private static final String NEW_LINE_REGEX = "\\n";

	@Override
	public String getGroupName() {
		return GROUP_NAME;
	}

	@Override
	protected String initRegex() {
		StringBuffer sb = new StringBuffer();
		sb.append(PatternParser.GROUP_START).append(NEW_LINE_REGEX)
				.append(PatternParser.GROUP_END);
		return sb.toString();
	}

	@Override
	protected void initRegexProperty() {
		// none for level property
	}
}
