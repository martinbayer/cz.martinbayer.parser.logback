package cz.martinbayer.parser.logback.pattern.logic;

import cz.martinbayer.parser.logback.pattern.PatternParser;
import cz.martinbayer.parser.logback.pattern.TypedPattern;

public class ExceptionPattern extends TypedPattern {

	private static final String GROUP_NAME = "exception";
	private static final String EXCEPTION_REGEX = "(?s)\\S*(Exception|Error):[^\\n]*\\n((\\s*at[^\\n]*\\n)|(Caused by:[^\\n]*\\n)|(\\s*\\.{3} [0-9]+ common frames omitted\\n))+";

	@Override
	public String getGroupName() {
		return GROUP_NAME;
	}

	@Override
	protected String initRegex() {
		StringBuffer sb = new StringBuffer();
		sb.append(PatternParser.GROUP_START).append(EXCEPTION_REGEX)
				.append(PatternParser.GROUP_END);
		return sb.toString();
	}

	@Override
	protected void initRegexProperty() {
		// none for level property
	}
}
