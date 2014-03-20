package cz.martinbayer.parser.logback.pattern.logic;

import cz.martinbayer.parser.logback.pattern.PatternParser;
import cz.martinbayer.parser.logback.pattern.TypedPattern;

public class ThreadPattern extends TypedPattern {

	private static final String GROUP_NAME = "thread";

	private static final String MESSAGE_REGEX = ".*";

	@Override
	public String getGroupName() {
		return GROUP_NAME;
	}

	@Override
	protected String initRegex() {
		StringBuffer sb = new StringBuffer();
		sb.append(PatternParser.GROUP_START).append(MESSAGE_REGEX)
				.append(PatternParser.GROUP_END);
		return sb.toString();
	}

	@Override
	protected void initRegexProperty() {
		// none for level property
	}
}
